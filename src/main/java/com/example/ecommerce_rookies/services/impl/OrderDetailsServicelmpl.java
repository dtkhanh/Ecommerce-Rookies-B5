package com.example.ecommerce_rookies.services.impl;

import com.example.ecommerce_rookies.exception.product.NotFoundProductByCategory;
import com.example.ecommerce_rookies.modelDTO.OrderDetailsDTO;
import com.example.ecommerce_rookies.modelDTO.OrdersDTO;
import com.example.ecommerce_rookies.models.Account;
import com.example.ecommerce_rookies.models.OrderDetails;
import com.example.ecommerce_rookies.models.Cartmodel;
import com.example.ecommerce_rookies.models.Product;
import com.example.ecommerce_rookies.repository.AccountRepository;
import com.example.ecommerce_rookies.repository.OrderDetailsRepository;
import com.example.ecommerce_rookies.repository.CartmodelRepository;
import com.example.ecommerce_rookies.repository.ProductRepository;
import com.example.ecommerce_rookies.services.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OrderDetailsServicelmpl implements OrderDetailsService {

    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Autowired

    AccountRepository accountRepository;

    @Autowired
    CartmodelRepository cartmodelRepository;

    @Autowired
    ProductRepository productRepository;


    @Override
    public OrderDetails addOrderDetails(OrderDetails orderDetails){
        return orderDetailsRepository.save(orderDetails);
    }
    @Override
    public List<OrderDetails> getAllOdersDetails(){
        return orderDetailsRepository.findAll();
    }

    @Override
    public OrderDetails getOdersDetails(Long id){
        return orderDetailsRepository.getById(id);
    }

    @Override
    public OrderDetails convertOrderDetails(Long id_account, OrderDetailsDTO orderDetailsDTO){
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setNumber(orderDetailsDTO.getNumber());
        Optional<Cartmodel> orders = cartmodelRepository.findById(id_account);
        orderDetails.setCartmodel(orders.get());
        Optional<Product> product = productRepository.findById(Long.valueOf(orderDetailsDTO.getId_product()));
        if(product.isEmpty())
            throw new NotFoundProductByCategory.NotFoundProduct(Long.valueOf(orderDetailsDTO.getId_product()));
        orderDetails.setPrice(product.get().getPrice()* orderDetailsDTO.getNumber());
        orderDetails.setProduct(product.get());

        return orderDetails;
    }

    @Override
    public ResponseEntity<OrderDetails> addProductByCart(Long id_account, OrderDetailsDTO orderDetailsDTO){
        Optional<Account> account = accountRepository.findById(id_account);
        Set<Cartmodel> cartmodels= account.get().getCartmodels();
        Long id_cart = null;
        for(Cartmodel cartmodel : cartmodels){
            id_cart=cartmodel.getId();
        }
        Cartmodel cartmodel= cartmodelRepository.getReferenceById(id_cart);
        if(cartmodel == null)
            ResponseEntity.ok().body(String.format("No value cart!"));
        if(cartmodel.getOrderDetails() != null) {
            Set<OrderDetails> orderDe = cartmodel.getOrderDetails();
            for (OrderDetails orderDetail : orderDe) {
                if (orderDetail.getProduct().getId() == Long.parseLong(orderDetailsDTO.getId_product())) {
                    orderDetail.setNumber(orderDetailsDTO.getNumber() + orderDetail.getNumber());
                    orderDetailsRepository.save(orderDetail);
                    Set<OrderDetails> orderDe1 = cartmodel.getOrderDetails();
                    float total = 0;
                    for (OrderDetails orderDetail1 : orderDe) {
                        total = total + (orderDetail1.getPrice() *orderDetail1.getNumber());
                    }
                    cartmodel.setTotal(total);
                    cartmodelRepository.save(cartmodel);
                    return ResponseEntity.ok().body(orderDetail);
                }
            }
        }
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setNumber(orderDetailsDTO.getNumber());
        orderDetails.setCartmodel(cartmodel);
        Optional<Product> product = productRepository.findById(Long.valueOf(orderDetailsDTO.getId_product()));
        if(product.isEmpty())
            throw new NotFoundProductByCategory.NotFoundProduct(Long.valueOf(orderDetailsDTO.getId_product()));
        orderDetails.setPrice(product.get().getPrice()* orderDetailsDTO.getNumber());
        orderDetails.setProduct(product.get());
        float total = 0;
        Set<OrderDetails> orderDe = cartmodel.getOrderDetails();
        for (OrderDetails orderDetail : orderDe) {
           total = total + (orderDetail.getPrice() *orderDetail.getNumber());
        }
        cartmodel.setTotal(total);
        cartmodelRepository.save(cartmodel);
        orderDetailsRepository.save(orderDetails);
        return ResponseEntity.ok().body(orderDetails);
    }

}
