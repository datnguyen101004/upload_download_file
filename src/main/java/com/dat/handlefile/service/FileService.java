package com.dat.handlefile.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    String uploadFile(MultipartFile multipartFile) throws IOException;
    byte[] downloadFile(String name) throws Exception;
}
