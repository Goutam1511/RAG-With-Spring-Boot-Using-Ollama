package com.infy.pdfquerydemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class PdfquerydemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PdfquerydemoApplication.class, args);
	}

}
