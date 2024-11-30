package com.infy.pdfquerydemo.controller;

import com.infy.pdfquerydemo.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueryController {

    @Autowired
    QueryService queryService;
    @GetMapping("/query")
    public String query(@RequestParam String query) {
        return queryService.query(query);
    }
}
