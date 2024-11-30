package com.infy.pdfquerydemo.service;

import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileStorageServiceImpl implements FileStorageService {
    private String fileStorageLocation;
    private static final String EXTENSION = ".pdf";
    @Autowired
    VectorStoreService<String, Document> vectorStoreService;

    public FileStorageServiceImpl(@Value("${file.storage.location}") String fileStorageLocation) {
        this.fileStorageLocation = fileStorageLocation;
    }

    public List<File> listPDFFiles() {
        File directory = new File(fileStorageLocation);
        return Arrays.stream(directory.listFiles())
                .filter(file -> file.isFile() && file.getName().endsWith(EXTENSION))
                .collect(Collectors.toList());
    }

    public String storeFile(MultipartFile file) throws IOException {
        Path filePath = Paths.get(fileStorageLocation, file.getOriginalFilename());
        Files.write(filePath, file.getBytes());
        vectorStoreService.execute(filePath.toString());
        return filePath.toString();
    }
}
