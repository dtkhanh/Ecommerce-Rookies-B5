package com.example.ecommerce_rookies.controllers;

import com.example.ecommerce_rookies.exception.product.NotFoundProductByCategory;
import com.example.ecommerce_rookies.modelDTO.OrderDetailsDTO;
import com.example.ecommerce_rookies.models.*;
import com.example.ecommerce_rookies.repository.AccountRepository;
import com.example.ecommerce_rookies.repository.OrderDetailsRepository;
import com.example.ecommerce_rookies.repository.CartmodelRepository;
import com.example.ecommerce_rookies.repository.ProductRepository;
import com.example.ecommerce_rookies.services.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/orderDetails")
public class OrderDetailsController {

    @Autowired
    OrderDetailsService orderDetailsService;

    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Autowired
    CartmodelRepository cartmodelRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    AccountRepository accountRepository;



    @PostMapping("/{id_account}")
    public ResponseEntity<?> productaddOrders(@PathVariable Long id_account,  @RequestBody OrderDetailsDTO orderDetailsDTO){
        orderDetailsService.addProductByCart(id_account,orderDetailsDTO);
        return ResponseEntity.ok().body(String.format("Update Successfully"));
    }
    @GetMapping("")
    public ResponseEntity<?> getAllOrderDetails(){
        return ResponseEntity.ok().body(orderDetailsService.getAllOdersDetails());
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderDetailsbyId(@PathVariable Long id){
        Optional<OrderDetails> orderDetails = orderDetailsRepository.findById(id);
        return ResponseEntity.ok().body(orderDetails.get().getProduct());
    }
}
