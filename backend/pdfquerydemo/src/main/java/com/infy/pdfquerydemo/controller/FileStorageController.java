package com.infy.pdfquerydemo.controller;

import com.infy.pdfquerydemo.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
public class FileStorageController {
    @Autowired
    private final FileStorageService fileStorageService;
    Logger LOG = Logger.getLogger(String.valueOf(FileStorageController.class));

    public FileStorageController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("/list")
    public List<String> listPDFFiles() {
        return fileStorageService.listPDFFiles().stream()
                .map(File::getName)
                .collect(Collectors.toList());
    }

    @PostMapping("/upload")
    public ResponseEntity<?>
    uploadPDFFile(@RequestParam("file") MultipartFile file) {
        try {
            return ResponseEntity.ok(fileStorageService.storeFile(file));
        } catch (IOException e) {
            LOG.fine(e.getMessage());
            return ResponseEntity.badRequest().body("Error uploading PDF file: " + e.getMessage() + " \n");
        }
    }
}
