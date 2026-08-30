package com.vika.airesumeanalyzer.service;

import java.io.IOException;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ResumeFileService {

    private final Tika tika = new Tika();

    public String extractText(MultipartFile file) throws IOException, TikaException {
        return tika.parseToString(file.getInputStream());
    }
}