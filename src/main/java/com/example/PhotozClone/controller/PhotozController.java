package com.example.PhotozClone.controller;

import com.example.PhotozClone.service.Photozservice;
import com.example.PhotozClone.model.Photo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
public class PhotozController {

    private final Photozservice PhotozService;

    public PhotozController(Photozservice photozService) {
        PhotozService = photozService;
    }

    //private List<Photo> db = List.of(new Photo(1, "home.jpg"));

    @GetMapping("/")
    public String Hello(){
        return "Hello World!";
    }
    @GetMapping("/photoz")
    public Iterable<Photo> getAllPhotos(){
        return this.PhotozService.values();
    }

    @GetMapping("/photoz/{id}")
    public Photo getPhoto(@PathVariable Integer id){
        Photo photo = this.PhotozService.get(id);
        if(photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return photo;
    }

    @DeleteMapping("/photoz/{id}")
    public void deletePhoto(@PathVariable Integer id){
        this.PhotozService.remove(id);
    }

    @PostMapping("/photoz")
    public Photo addPhoto(@RequestPart("data") MultipartFile file) throws IOException {
        return this.PhotozService.create(file.getOriginalFilename(),file.getContentType(), file.getBytes());
    }
}
