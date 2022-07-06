package com.example.ecommerce_rookies.repository;

import com.example.ecommerce_rookies.models.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface GalleryRepository extends JpaRepository<Gallery, Long> {
    @Transactional
    @Modifying
    @Query("delete from Gallery g where g.product.id = ?1")
    void deleteGalleryByProduct_Id(Long id);
}
