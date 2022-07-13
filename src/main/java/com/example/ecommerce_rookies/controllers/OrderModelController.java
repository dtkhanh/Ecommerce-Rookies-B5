package com.example.ecommerce_rookies.controllers;

import com.example.ecommerce_rookies.models.OrdersModel;
import com.example.ecommerce_rookies.repository.OdersModelRepository;
import com.example.ecommerce_rookies.services.OderModelServicel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/checkout")
public class OrderModelController {

    @Autowired
    OderModelServicel oderModelServicel;

    @Autowired
    OdersModelRepository odersModelRepository;

    @PostMapping("/{id_account}")
//    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> productaddOrders(@PathVariable Long id_account){
        OrdersModel ordersModel = oderModelServicel.checkoutCart(id_account);
        return ResponseEntity.ok().body(oderModelServicel.convertDTO(ordersModel));
    }
    @GetMapping("")
    public ResponseEntity<?> getCheckout(){
        return ResponseEntity.ok().body(oderModelServicel.getAllOrdersModel());
    }
}
