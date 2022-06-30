package com.example.ecommerce_rookies.services;

import com.example.ecommerce_rookies.modelDTO.RattingDTO;
import com.example.ecommerce_rookies.models.ProductComment;

import java.util.List;

public interface ProductCommentService {
    List<ProductComment> getProductCommentList();

    List<RattingDTO> convertListDTO(List<ProductComment> prductcmList);

    RattingDTO convertDTO(ProductComment productComment);
}
