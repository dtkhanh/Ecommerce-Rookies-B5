package com.example.ecommerce_rookies.services.impl;

import com.example.ecommerce_rookies.modelDTO.OrdersDTO;
import com.example.ecommerce_rookies.models.Account;
import com.example.ecommerce_rookies.models.OrderDetails;
import com.example.ecommerce_rookies.models.Orders;
import com.example.ecommerce_rookies.repository.AccountRepository;
import com.example.ecommerce_rookies.repository.OrdersRepository;
import com.example.ecommerce_rookies.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    AccountRepository accountRepository;


    @Override
    public OrdersDTO convertByDTO(long id){
        Optional<Account> account = accountRepository.findById(id);
        Optional< Orders> orders = ordersRepository.findById(id);
        OrdersDTO ordersDTO = new OrdersDTO();
        ordersDTO.setId(id);
        ordersDTO.setOrderdate(orders.get().getOrderdate());
        ordersDTO.setId_account(account.get().getId());
        ordersDTO.setName_account(account.get().getUsername());
        Set<OrderDetails> orderDetails = orders.get().getOrderDetails();
        List<String> nameproduct = new ArrayList<>();
        for(OrderDetails orderDetails1 : orderDetails){
            nameproduct.add(orderDetails1.getProduct().getTitle() + " id: " + orderDetails1.getProduct().getId());
        }
        ordersDTO.setProduct(nameproduct);
        return ordersDTO;

//        private float total;
//
//        private LocalDate orderdate;
//
//        private Long id_account;
//
//        private String name_account;
//
//        private List<String> product;
    }
}
