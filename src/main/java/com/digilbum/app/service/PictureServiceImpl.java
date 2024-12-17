package com.digilbum.app.service;

import com.digilbum.app.dto.PictureDto;
import com.digilbum.app.models.Album;
import com.digilbum.app.models.Picture;
import com.digilbum.app.repositorys.AlbumRepository;
import com.digilbum.app.repositorys.PictureRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class PictureServiceImpl implements IPictureService {

    Logger logger = LoggerFactory.getLogger(getClass());

    private final String FOLDER_PATH;
    private final String BASE_PATH;

    private final String WEB_PATH;

    private final PictureRepository pictureRepository;


    private final AlbumRepository albumRepository;
    public PictureServiceImpl(
            PictureRepository pictureRepository,
            AlbumRepository albumRepository,
            @Value("${pictures.server.url}")
            String hostPictureServer,
            @Value("${pictures.folder.path}")
            String folderPath) {
        this.pictureRepository = pictureRepository;
        this.albumRepository = albumRepository;
        this.FOLDER_PATH = folderPath;
        this.BASE_PATH = System.getProperty("user.home") + this.FOLDER_PATH;
        this.WEB_PATH = hostPictureServer;
    }

    @Override
    public List<PictureDto> loadPicturesForAlbum(Integer albumId) {
        return addWebPathForPicturesDto(
                pictureRepository.findByAlbumId(albumId)
        );
    }

    @Override
    public List<PictureDto> writeAndSavePictures(List<MultipartFile> pictures, Integer albumId) throws IOException {
        logger.info("call : writeAndSavePictures");

        AtomicReference<Album> album = new AtomicReference<>();
        albumRepository.findById(albumId).ifPresent(album::set);

        List<Picture> newPictures = new ArrayList<>();

            for (MultipartFile pic : pictures) {
                String fileName = generatePictureName(album.get(), pic.getOriginalFilename());
                logger.info("BASE_PATH : " + BASE_PATH);
                Path path = Paths.get(BASE_PATH, fileName);

                File picfile = path.toFile();
                if (!picfile.createNewFile()) {
                    throw new IOException("file already exist"+ fileName);
                }

                try ( FileOutputStream fileOutputStream = new FileOutputStream(picfile)) {
                    fileOutputStream.write(pic.getBytes());
                }
                Picture newPic = new Picture();
                newPic.setAlbum(album.get());
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
                .append( ".")
                .append(getTypePictureFile(Objects.requireNonNull(pictureOriginalFilename)))
        ;
        return pictuteNameBuilder.toString() ;
    }

    private String getTypePictureFile(String originalFileName) {
        String[] pictureType = originalFileName.split("\\.");
        return pictureType[1];
    }

    private List<PictureDto> addWebPathForPicturesDto(List<Picture> pictures) {
        final List<PictureDto> finalDto = new ArrayList<>();
            for (Picture picture : pictures) {
                finalDto.add( new PictureDto(
                        picture.getId(),
                        WEB_PATH +FOLDER_PATH+ picture.getPathFile(),
                        picture.getAlbum().getId()));
            }
        return finalDto;
    }

    @Override
    public void deletePictures(Album album) {
        pictureRepository.deleteAll(album.getPictures());
    }
}
