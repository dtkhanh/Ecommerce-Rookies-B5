package com.example.ecommerce_rookies.services.impl;


import com.example.ecommerce_rookies.exception.account.NotFoundAccount;
import com.example.ecommerce_rookies.modelDTO.OrdersDTO;
import com.example.ecommerce_rookies.modelDTO.ProductDTO;
import com.example.ecommerce_rookies.models.*;
import com.example.ecommerce_rookies.repository.*;
import com.example.ecommerce_rookies.services.OderModelServicel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ecommerce_rookies.modelDTO.OrderModelDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
public class OrderModelServicelmpl implements OderModelServicel {

    @Autowired
    OdersModelRepository odersModelRepository;

    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductServiceImpl productService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountServiceImpl accountService;

    @Autowired
    CartmodelServiceImpl cartmodelService;

    @Autowired
    CartmodelRepository cartmodelRepository;



    @Override
    public OrdersModel addOrdersModel(OrdersModel ordersModel){
        return odersModelRepository.save(ordersModel);
    }


    @Override
    public List<OrderModelDTO> getAllOrdersModel(){
        List<OrderModelDTO> listdto =new ArrayList<>();
        List<OrdersModel> list = odersModelRepository.findAll();
        for(OrdersModel ordersModel : list){
            listdto.add(convertDTO(ordersModel));
        }
        return  listdto;
    }

    @Override
    public OrdersModel getOrdersModelById(Long id){
        return odersModelRepository.getReferenceById(id);
    }

    @Override
    public OrderModelDTO convertDTO(OrdersModel ordersModel){
        OrderModelDTO orderModelDTO = new OrderModelDTO();
        orderModelDTO.setId(ordersModel.getId());
        orderModelDTO.setTotal(ordersModel.getTotal());
        orderModelDTO.setQuantity(ordersModel.getQuantity());
        orderModelDTO.setAccount(String.valueOf(ordersModel.getAccount().getId()));
        List<ProductDTO> list1 = new ArrayList<>();

        orderModelDTO.setListProduct(list1);
        return orderModelDTO;
    }

    @Override
    public OrdersModel checkoutCart(Long id){
        OrdersModel ordersModel = new OrdersModel();
        Cartmodel cartmodel = cartmodelRepository.getReferenceById(id);
        OrdersDTO ordersDTO = cartmodelService.getCartByIdAccount(id);
        Account account = accountRepository.findById(id).get();
        if(account == null)
            throw new NotFoundAccount(id);
        ordersModel.setAccount(account);
        ordersModel.setTotal(ordersDTO.getTotal());
        Set<OrderDetails> orderDetailsSet = cartmodel.getOrderDetails();
        int number =0;
        for(OrderDetails orderDetails:orderDetailsSet){
            number = number + orderDetails.getNumber();
        }
        ordersModel.setQuantity(number);
        List<Product> products = ordersDTO.getProducts();
        String s = "";
        for(Product product:products){
            if(s.equals("")){
                s=s+product.getId();
            }
            else{
                s=s +"/"+product.getId();
            }
        }
        orderDetailsRepository.deleteAllByCartmodel_Id(cartmodel.getId());
        ordersModel.setListProduct(s);
        cartmodel.setTotal(0);
        cartmodel.setOrderdate(null);
        Set<OrderDetails> list = cartmodel.getOrderDetails();
        cartmodel.setOrderDetails(null);
        cartmodelRepository.save(cartmodel);
        odersModelRepository.save(ordersModel);
        return ordersModel;

    }

}
