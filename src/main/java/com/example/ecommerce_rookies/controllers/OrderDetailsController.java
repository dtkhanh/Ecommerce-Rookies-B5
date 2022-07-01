package com.example.ecommerce_rookies.controllers;

import com.example.ecommerce_rookies.exception.account.NotFoundAccount;
import com.example.ecommerce_rookies.exception.product.NotFoundProductByCategory;
import com.example.ecommerce_rookies.modelDTO.OrderDetailsDTO;
import com.example.ecommerce_rookies.models.Account;
import com.example.ecommerce_rookies.models.OrderDetails;
import com.example.ecommerce_rookies.models.Orders;
import com.example.ecommerce_rookies.models.Product;
import com.example.ecommerce_rookies.repository.AccountRepository;
import com.example.ecommerce_rookies.repository.OrderDetailsRepository;
import com.example.ecommerce_rookies.repository.OrdersRepository;
import com.example.ecommerce_rookies.repository.ProductRepository;
import com.example.ecommerce_rookies.services.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/auth/orderDetails")
public class OrderDetailsController {

    @Autowired
    OrderDetailsService orderDetailsService;

    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    AccountRepository accountRepository;



    @PostMapping("/{id_account}/add-cart")
    public ResponseEntity<?> productaddOrders(@PathVariable Long id_account,  @RequestBody OrderDetailsDTO orderDetailsDTO){
        Optional<Orders> orders = ordersRepository.findById(id_account);
        Set<OrderDetails> orderDe = orders.get().getOrderDetails();
        for(OrderDetails orderDetail : orderDe){
            if(orderDetail.getProduct().getId() == Long.parseLong(orderDetailsDTO.getId_product())){
                orderDetail.setNumber(orderDetailsDTO.getNumber() + orderDetail.getNumber());
                orderDetailsService.addOrderDetails(orderDetail);
                return ResponseEntity.ok().body(orderDetail);
            }
        }
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setNumber(orderDetailsDTO.getNumber());
        orderDetails.setOrders(orders.get());
        Optional<Product> product = productRepository.findById(Long.valueOf(orderDetailsDTO.getId_product()));
        if(product.isEmpty())
            throw new NotFoundProductByCategory.NotFoundProduct(Long.valueOf(orderDetailsDTO.getId_product()));
        orderDetails.setPrice(product.get().getPrice()* orderDetailsDTO.getNumber());
        orderDetails.setProduct(product.get());
        orderDetailsService.addOrderDetails(orderDetails);
        return ResponseEntity.ok().body(orderDetails);
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
