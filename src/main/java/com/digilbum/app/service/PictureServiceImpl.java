package com.digilbum.app.service;

import com.digilbum.app.dto.PictureDto;
import com.digilbum.app.models.Album;
import com.digilbum.app.models.Picture;
import com.digilbum.app.repositorys.AlbumRepository;
import com.digilbum.app.repositorys.PictureRepository;
import com.digilbum.app.utils.files.FileGenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.context.annotation.Lazy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Service
public class PictureServiceImpl implements IPictureService {

    Logger logger = LoggerFactory.getLogger(getClass());

    private final String FOLDER_PATH;
    private final String BASE_PATH;

    private final String WEB_PATH;

    private final PictureRepository pictureRepository;
    private final FileGenerator FILE_GENERATOR;

    final AlbumRepository albumRepository;

    private final IAlbumService albumService;

    public PictureServiceImpl(
            PictureRepository pictureRepository,
            AlbumRepository albumRepository,
            @Lazy IAlbumService albumService,
            @Value("${pictures.server.url}") String hostPictureServer,
            @Value("${pictures.folder.path}") String folderPath) {
        this.pictureRepository = pictureRepository;
        this.albumRepository = albumRepository;
        this.albumService = albumService;
        this.FOLDER_PATH = folderPath;
        this.BASE_PATH = System.getProperty("user.home") + this.FOLDER_PATH;
        this.WEB_PATH = hostPictureServer;
        this.FILE_GENERATOR = new FileGenerator(this.BASE_PATH);
    }

    @Override
    public List<PictureDto> loadForAlbum(Integer albumId) {
        return addWebPathForDto(
                pictureRepository.findByAlbumId(albumId));
    }

    @Override
    public List<PictureDto> writeAndSave(List<MultipartFile> pictures, Integer albumId) throws IOException {
        Album album = albumService.getAtomicAlbum(this, albumId);
        List<Picture> newPictures = new ArrayList<>();

        for (MultipartFile pic : pictures) {
            String fileName = generatePictureName(album, pic.getOriginalFilename());

            FILE_GENERATOR.writeMultiPartFile(pic, fileName);

            Picture newPic = new Picture();
            newPic.setAlbum(album);
            newPic.setPathFile(fileName);

            newPictures.add(newPic);
            pictureRepository.save(newPic);
        }

        return newPictures.stream().map(this::toDto).toList();
    }

    private PictureDto toDto(Picture picture) {
        return new PictureDto(picture.getId(), picture.getPathFile(), picture.getAlbum().getId());
    }

    /**
     * pour save un fichier, il faut construire le path du fichier
     * quand on recoit une picture volatile, le pathFile contient le nom du fichier
     * pour construire cet url on va utiliser le nom de l'album :
     * /home/digilbum/pictures/
     * puis plus tard :
     * PATH/pictures/familly/event/album/pictureName
     */
    private String generatePictureName(Album album, String pictureOriginalFilename) throws NullPointerException {
        StringBuilder pictuteNameBuilder = new StringBuilder();
        pictuteNameBuilder.append(album.getName().replace(" ", ""))
                .append(Calendar.getInstance().getTimeInMillis())
                .append(".")
                .append(getTypePictureFile(Objects.requireNonNull(pictureOriginalFilename)));
        return pictuteNameBuilder.toString();
    }

    private String getTypePictureFile(String originalFileName) {
        String[] pictureType = originalFileName.split("\\.");
        return pictureType[1];
    }

    private List<PictureDto> addWebPathForDto(List<Picture> pictures) {
        final List<PictureDto> finalDto = new ArrayList<>();
        for (Picture picture : pictures) {
            finalDto.add(new PictureDto(
                    picture.getId(),
                    WEB_PATH + FOLDER_PATH + picture.getPathFile(),
                    picture.getAlbum().getId()));
        }
        return finalDto;
    }

    @Override
    public void deletePictures(Album album) {
        pictureRepository.deleteAll(album.getPictures());
    }
}
