package com.example.ecommerce_rookies.services.impl;


import com.example.ecommerce_rookies.modelDTO.RattingDTO;
import com.example.ecommerce_rookies.models.Product;
import com.example.ecommerce_rookies.models.ProductComment;
import com.example.ecommerce_rookies.repository.ProductCommentRepository;
import com.example.ecommerce_rookies.repository.ProductRepository;
import com.example.ecommerce_rookies.services.ProductCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ProductCommentServiceImpl  implements ProductCommentService {

    @Autowired
    ProductCommentRepository productCommentRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<ProductComment> getProductCommentList() { return productCommentRepository.findAll(); }



    @Override
    public List<RattingDTO> convertListDTO(List<ProductComment> prductcmList){
        List<RattingDTO> list = new ArrayList<>();
        for (ProductComment productComment : prductcmList) {
            list.add(convertDTO(productComment));
        }
        return list;
    }

    @Override
    public ResponseEntity<?> listRatingProduct(Long id){
        Product product= productRepository.getReferenceById(id);
        Set<ProductComment> list = product.getProductComments();
        List<RattingDTO> rattingDTOS = new ArrayList<>();
        if(!list.isEmpty()) {
            for (ProductComment productComment : list) {
                rattingDTOS.add(convertDTO(productComment));
            }
        }
        return ResponseEntity.ok().body(rattingDTOS);
    }


    @Override
    public RattingDTO convertDTO(ProductComment productComment){
        RattingDTO rattingDTO =  new RattingDTO();
        rattingDTO.setId(productComment.getId());
        rattingDTO.setName(productComment.getAccount().getUsername());
        rattingDTO.setRating(productComment.getRating());
        rattingDTO.setComment(productComment.getComment());
        rattingDTO.setProduct(productComment.getProduct().getTitle() +" id: " +productComment.getProduct().getId());
        return rattingDTO;
    }
}
