package com.infy.pdfquerydemo.service;

import java.util.List;

public interface VectorStoreService<T, R> {
    public void execute(T in);

    public List<R> search(T q);
}
