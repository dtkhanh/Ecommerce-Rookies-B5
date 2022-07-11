package com.example.ecommerce_rookies.services;

import com.example.ecommerce_rookies.modelDTO.RattingDTO;
import com.example.ecommerce_rookies.models.ProductComment;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductCommentService {
    List<ProductComment> getProductCommentList();

    List<RattingDTO> convertListDTO(List<ProductComment> prductcmList);

    ResponseEntity<?> listRatingProduct(Long id);

    RattingDTO convertDTO(ProductComment productComment);
}
