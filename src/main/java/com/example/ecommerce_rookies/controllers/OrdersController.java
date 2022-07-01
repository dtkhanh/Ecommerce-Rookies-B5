package com.example.ecommerce_rookies.controllers;


import com.example.ecommerce_rookies.modelDTO.OrdersDTO;
import com.example.ecommerce_rookies.models.OrderDetails;
import com.example.ecommerce_rookies.models.Orders;
import com.example.ecommerce_rookies.repository.OrderDetailsRepository;
import com.example.ecommerce_rookies.repository.OrdersRepository;
import com.example.ecommerce_rookies.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/auth/cart")
public class OrdersController {
    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Autowired
    OrdersService ordersService;

    @GetMapping("")
    public ResponseEntity<?> getOrdersall(){
        return ResponseEntity.ok().body(ordersRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrdersById(@PathVariable Long id){
        OrdersDTO ordersDTO = ordersService.convertByDTO(id);
        return ResponseEntity.ok().body(ordersDTO);
    }

    @DeleteMapping("/{id_account}/{id_product}/delete")
    public ResponseEntity<?> DeleteProductInOrders(@PathVariable Long id_account , @PathVariable Long id_product){
        Optional<Orders> orders = ordersRepository.findById(id_account);
        Set<OrderDetails> orderDetails = orders.get().getOrderDetails();
        for(OrderDetails orderDetails1 : orderDetails){
            if(orderDetails1.getOrders().getId() == id_account){
                if(orderDetails1.getProduct().getId()== id_product) {
                    orderDetailsRepository.deleteAllById(orderDetails1.getId());
                }
            }
        }
      ;
        return ResponseEntity.ok().body(ordersService.convertByDTO(  ordersRepository.save(orders.get()).getId()));
    }
}
