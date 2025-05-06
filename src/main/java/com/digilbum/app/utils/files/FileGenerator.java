package com.digilbum.app.utils.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FileGenerator {
    private final String BASE_PATH;

    public void writeMultiPartFile(MultipartFile pic, String fileName) throws IOException, FileNotFoundException {
        Path path = Paths.get(BASE_PATH, fileName);

        File picfile = path.toFile();
        if (!picfile.createNewFile()) {
            throw new IOException("file already exist" + fileName);
        }

        try (FileOutputStream fileOutputStream = new FileOutputStream(picfile)) {
            fileOutputStream.write(pic.getBytes());
        }
    }

}
