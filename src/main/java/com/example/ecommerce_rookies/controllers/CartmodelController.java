package com.example.ecommerce_rookies.controllers;


import com.example.ecommerce_rookies.modelDTO.OrdersDTO;
import com.example.ecommerce_rookies.models.Account;
import com.example.ecommerce_rookies.models.OrderDetails;
import com.example.ecommerce_rookies.models.Cartmodel;
import com.example.ecommerce_rookies.repository.AccountRepository;
import com.example.ecommerce_rookies.repository.OrderDetailsRepository;
import com.example.ecommerce_rookies.repository.CartmodelRepository;
import com.example.ecommerce_rookies.services.CartmodelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/api/cart")
public class CartmodelController {
    @Autowired
    CartmodelRepository cartmodelRepository;

    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CartmodelService cartmodelService;

    @GetMapping("")
    public ResponseEntity<?> getOrdersall(){
        return ResponseEntity.ok().body(cartmodelRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrdersById(@PathVariable Long id){
        OrdersDTO ordersDTO = cartmodelService.convertByDTO(id);
        return ResponseEntity.ok().body(ordersDTO);
    }
    @GetMapping("/account/{id_account}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getCartByIdAccount(@PathVariable Long id_account){
        return ResponseEntity.ok().body(cartmodelService.getCartByIdAccount(id_account));
    }

    @DeleteMapping("/{id_account}/{id_product}")
    public ResponseEntity<?> DeleteProductInOrders(@PathVariable Long id_account , @PathVariable Long id_product){
        Optional<Account> account = accountRepository.findById(id_account);
        Set<Cartmodel> cartmodels= account.get().getCartmodels();
        Long id_cart = null;
        for(Cartmodel cartmodel : cartmodels){
            id_cart=cartmodel.getId();
        }
        Optional<Cartmodel> cartmodel = cartmodelRepository.findById(id_cart);
        Set<OrderDetails> orderDetails = cartmodel.get().getOrderDetails();
        for(OrderDetails orderDetails1 : orderDetails){
            if(orderDetails1.getCartmodel().getId() == id_cart){
                if(orderDetails1.getProduct().getId()== id_product) {
                    orderDetailsRepository.deleteAllById(orderDetails1.getId());
                }
            }
        }

        return ResponseEntity.ok().body(cartmodelService.convertByDTO(  cartmodel.get().getId()));
    }
}
