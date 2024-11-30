package com.infy.pdfquerydemo.service;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class VectorStoreServiceImpl implements VectorStoreService<String, Document> {

    @Autowired
    VectorStore vectorStore;

    @Override
    //@Async
    public void execute(String in) {
        File pdfFile = new File(in);
        FileSystemResource resource = new FileSystemResource(pdfFile);
        TikaDocumentReader tikaDocumentReader = new TikaDocumentReader(resource);
        TokenTextSplitter tokenTextSplitter = new TokenTextSplitter();
        vectorStore.add(tokenTextSplitter.apply(tikaDocumentReader.get()));
    }

    @Override
    public List<Document> search(String q) {
        return vectorStore.similaritySearch(q);
    }
}
