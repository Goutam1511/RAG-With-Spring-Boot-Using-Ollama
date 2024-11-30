package com.infy.pdfquerydemo.service;

import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryServiceImpl implements QueryService {

    @Autowired
    VectorStoreService<String, Document> vectorStoreService;

    @Autowired
    ChatService<String, Document> chatService;

    public String query(String query) {
        List<Document> relatedDocs = vectorStoreService.search(query);
        return chatService.chat(query, relatedDocs);
    }
}
