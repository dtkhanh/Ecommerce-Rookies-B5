package com.example.ecommerce_rookies.services;

import com.example.ecommerce_rookies.modelDTO.OrderModelDTO;
import com.example.ecommerce_rookies.models.OrdersModel;

import java.util.List;

public interface OderModelServicel {
    OrdersModel addOrdersModel(OrdersModel ordersModel);

    List<OrderModelDTO>  getAllOrdersModel();

    OrdersModel getOrdersModelById(Long id);

    OrderModelDTO convertDTO(OrdersModel ordersModel);

    OrdersModel checkoutCart(Long id);
}
