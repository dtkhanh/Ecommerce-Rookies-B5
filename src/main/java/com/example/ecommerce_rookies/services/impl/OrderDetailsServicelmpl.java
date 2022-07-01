package com.example.ecommerce_rookies.services.impl;

import com.example.ecommerce_rookies.exception.product.NotFoundProductByCategory;
import com.example.ecommerce_rookies.modelDTO.OrderDetailsDTO;
import com.example.ecommerce_rookies.models.OrderDetails;
import com.example.ecommerce_rookies.models.Orders;
import com.example.ecommerce_rookies.models.Product;
import com.example.ecommerce_rookies.repository.OrderDetailsRepository;
import com.example.ecommerce_rookies.repository.OrdersRepository;
import com.example.ecommerce_rookies.repository.ProductRepository;
import com.example.ecommerce_rookies.services.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailsServicelmpl implements OrderDetailsService {

    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Autowired
    OrdersRepository ordersRepository;

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
        Optional<Orders> orders = ordersRepository.findById(id_account);
        orderDetails.setOrders(orders.get());
        Optional<Product> product = productRepository.findById(Long.valueOf(orderDetailsDTO.getId_product()));
        if(product.isEmpty())
            throw new NotFoundProductByCategory.NotFoundProduct(Long.valueOf(orderDetailsDTO.getId_product()));
        orderDetails.setPrice(product.get().getPrice()* orderDetailsDTO.getNumber());
        orderDetails.setProduct(product.get());
        return orderDetails;
    }
}
