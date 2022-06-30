package com.example.ecommerce_rookies.controllers;


import com.example.ecommerce_rookies.models.Gallery;
import com.example.ecommerce_rookies.repository.GalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/auth/gallery")
public class GalleryController {

    @Autowired
    GalleryRepository galleryRepository;

    @GetMapping("")
    public ResponseEntity<?> GetGallerys(){
        return ResponseEntity.ok().body(galleryRepository.findAll());
    }
}
