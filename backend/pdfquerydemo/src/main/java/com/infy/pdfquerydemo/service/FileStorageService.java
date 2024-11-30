package com.infy.pdfquerydemo.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FileStorageService {
    public List<File> listPDFFiles();

    public String storeFile(MultipartFile pdfFile) throws IOException;
}
