package com.example.PhotozClone.service;

import com.example.PhotozClone.model.Photo;
import com.example.PhotozClone.repository.PhotozRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class Photozservice {

    private final PhotozRepository photozRepository;

    public Photozservice(PhotozRepository photozRepository) {
        this.photozRepository = photozRepository;
    }

    public Iterable<Photo> values() {
        return this.photozRepository.findAll();
    }

    public Photo get(Integer id) {
        return this.photozRepository.findById(id).orElse(null);
    }

    public void remove(Integer id) {
        this.photozRepository.deleteById(id);
    }

    public Photo create(String fileName, String contentType, byte[] data) {
        Photo photo = new Photo();
        photo.setData(data);
        photo.setFileName(fileName);
        photo.setContentType(contentType);
        this.photozRepository.save(photo);
        return photo;
    }
}
