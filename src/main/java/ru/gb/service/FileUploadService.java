package ru.gb.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploadService {

    public static void uploadFile(String uploadDir, String fileName, MultipartFile uploadFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        System.out.println(uploadPath);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try {
            InputStream inputStream = uploadFile.getInputStream();
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException(ioe.getMessage());
        }

    }

}
