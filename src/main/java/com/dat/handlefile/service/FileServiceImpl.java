package com.dat.handlefile.service;

import com.dat.handlefile.entity.Image;
import com.dat.handlefile.repository.ImageRepository;
import com.dat.handlefile.util.ImageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FileServiceImpl implements FileService{
    private final ImageRepository imageRepository;

    public String uploadFile(MultipartFile multipartFile) throws IOException {
        Image image = imageRepository.save(Image.builder()
                        .name(multipartFile.getOriginalFilename())
                        .type(multipartFile.getContentType())
                        .image(ImageUtil.compressImage(multipartFile.getBytes()))
                .build());
        if (image != null){
            return "File save successfully link : http://localhost:8081/image/" + multipartFile.getOriginalFilename();
        }
        return "Error occur";
    }

    public byte[] downloadFile(String name) throws Exception {
        Image image = imageRepository.findByName(name).orElseThrow(()->new Exception("File is not exist"));
        return ImageUtil.decompressImage(image.getImage());
    }
}
