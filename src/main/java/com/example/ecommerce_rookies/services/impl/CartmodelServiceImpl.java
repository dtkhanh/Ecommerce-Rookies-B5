package com.example.ecommerce_rookies.services.impl;

import com.example.ecommerce_rookies.modelDTO.OrdersDTO;
import com.example.ecommerce_rookies.models.Account;
import com.example.ecommerce_rookies.models.OrderDetails;
import com.example.ecommerce_rookies.models.Cartmodel;
import com.example.ecommerce_rookies.models.Product;
import com.example.ecommerce_rookies.repository.AccountRepository;
import com.example.ecommerce_rookies.repository.CartmodelRepository;
import com.example.ecommerce_rookies.services.CartmodelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CartmodelServiceImpl implements CartmodelService {
    @Autowired
    CartmodelRepository cartmodelRepository;

    @Autowired
    AccountRepository accountRepository;


    @Override
    public OrdersDTO convertByDTO(long id){
        Optional<Account> account = accountRepository.findById(id);
        Optional<Cartmodel> orders = cartmodelRepository.findById(id);
        OrdersDTO ordersDTO = new OrdersDTO();
        ordersDTO.setId(id);
        ordersDTO.setOrderdate(orders.get().getOrderdate());
        ordersDTO.setId_account(account.get().getId());
        ordersDTO.setName_account(account.get().getUsername());
        Set<OrderDetails> orderDetails = orders.get().getOrderDetails();
        List<Product> nameproduct = new ArrayList<>();
        for(OrderDetails orderDetails1 : orderDetails){
            nameproduct.add(orderDetails1.getProduct());
        }
        ordersDTO.setProducts(nameproduct);
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
    @Override
    public OrdersDTO getCartByIdAccount(Long id_account){
        Set<Cartmodel> cartmodels = accountRepository.findById(id_account).get().getCartmodels();
        if(cartmodels == null)
            ResponseEntity.ok().body(String.format("No value cart!"));
        Long id_cart = null;
        for(Cartmodel cartmodel : cartmodels){
            id_cart=cartmodel.getId();
        }
        Cartmodel cartmodel= cartmodelRepository.getReferenceById(id_cart);
        if(cartmodel == null)
            ResponseEntity.ok().body(String.format("No value cart!"));
        List<Product> list = new ArrayList<>();
        for(OrderDetails orderDetails : cartmodel.getOrderDetails()){
            list.add(orderDetails.getProduct());
        }
        OrdersDTO cartDTO = new OrdersDTO(id_cart,cartmodel.getTotal(),cartmodel.getOrderdate(),id_account,accountRepository.findById(id_account).get().getUsername(),list);
        return  cartDTO;



    }
}
