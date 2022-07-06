package com.example.ecommerce_rookies.services;

import com.example.ecommerce_rookies.modelDTO.OrdersDTO;
import org.springframework.http.ResponseEntity;

public interface CartmodelService {
    OrdersDTO convertByDTO(long id);

    OrdersDTO getCartByIdAccount(Long id_account);
}
