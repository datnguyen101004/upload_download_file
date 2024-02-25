package com.dat.handlefile.controller;

import com.dat.handlefile.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class FileController {
    private final FileService fileService;

    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestParam("image") MultipartFile multipartFile) throws IOException {
        return ResponseEntity.ok(fileService.uploadFile(multipartFile));
    }

    @GetMapping("/{name}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String name) throws Exception{
        return ResponseEntity.ok().contentType(MediaType.valueOf("image/png")).body(fileService.downloadFile(name));
    }
}
