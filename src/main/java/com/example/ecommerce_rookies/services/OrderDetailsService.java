package com.example.ecommerce_rookies.services;

import com.example.ecommerce_rookies.modelDTO.OrderDetailsDTO;
import com.example.ecommerce_rookies.models.OrderDetails;

import java.util.List;

public interface OrderDetailsService {
    OrderDetails addOrderDetails(OrderDetails orderDetails);

    List<OrderDetails> getAllOdersDetails();

    OrderDetails getOdersDetails(Long id);

    OrderDetails convertOrderDetails(Long id_account, OrderDetailsDTO orderDetailsDTO);
}
