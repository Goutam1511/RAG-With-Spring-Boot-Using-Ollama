package com.infy.pdfquerydemo.service;

import java.util.List;

public interface ChatService<T, D> {
    T chat(T message, List<D> relatedDocs);
}
