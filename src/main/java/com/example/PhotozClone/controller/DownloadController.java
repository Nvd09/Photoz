package com.example.PhotozClone.controller;

import com.example.PhotozClone.service.Photozservice;
import com.example.PhotozClone.model.Photo;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class DownloadController {
    private final Photozservice photozService;

    public DownloadController(Photozservice photozService) {
        this.photozService = photozService;
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable Integer id){
        Photo photo = this.photozService.get(id);
        if(photo==null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        byte[] data = photo.getData();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(photo.getContentType()));
        ContentDisposition build = ContentDisposition
                .builder("inline")
                .filename(photo.getFileName())
                .build();

        headers.setContentDisposition(build);
        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }

}
