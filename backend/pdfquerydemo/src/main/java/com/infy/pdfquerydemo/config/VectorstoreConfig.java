package com.infy.pdfquerydemo.config;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VectorstoreConfig {

    @Bean
    VectorStore vectorStore(EmbeddingModel model) {
        return new SimpleVectorStore(model);
    }
}