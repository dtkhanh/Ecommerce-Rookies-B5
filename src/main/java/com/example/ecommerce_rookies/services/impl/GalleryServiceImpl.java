package com.example.ecommerce_rookies.services.impl;

import com.example.ecommerce_rookies.models.Gallery;
import com.example.ecommerce_rookies.repository.GalleryRepository;
import com.example.ecommerce_rookies.services.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GalleryServiceImpl implements GalleryService {

    @Autowired
    GalleryRepository galleryRepository;

    public Optional<Gallery> GetGalleryById(Long id){
        return galleryRepository.findById(id);
    }
}
