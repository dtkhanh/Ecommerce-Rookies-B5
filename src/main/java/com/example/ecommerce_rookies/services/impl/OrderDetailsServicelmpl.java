package com.example.ecommerce_rookies.services.impl;

import com.example.ecommerce_rookies.exception.account.NotFoundAccount;
import com.example.ecommerce_rookies.exception.cart.NotFoundCart;
import com.example.ecommerce_rookies.exception.product.NotFoundProductByCategory;
import com.example.ecommerce_rookies.modelDTO.OrderDetailsDTO;
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

    private final   OrderDetailsRepository orderDetailsRepository;

    private final AccountRepository accountRepository;

    private final CartmodelRepository cartmodelRepository;

    private final ProductRepository productRepository;

    @Autowired
    public OrderDetailsServicelmpl(OrderDetailsRepository orderDetailsRepository, AccountRepository accountRepository, CartmodelRepository cartmodelRepository, ProductRepository productRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
        this.accountRepository = accountRepository;
        this.cartmodelRepository = cartmodelRepository;
        this.productRepository = productRepository;
    }


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
    public List<OrderDetailsDTO> getOrderDetailByTdAccountIdProduct(Long Id_Account){
        List<OrderDetailsDTO> list1 = new ArrayList<>();

        List<OrderDetails> list = orderDetailsRepository.findAll();
        for(OrderDetails orderDetails :list){
            if(orderDetails.getCartmodel().getId() == Id_Account){
                OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();
                orderDetailsDTO.setId_product(String.valueOf(orderDetails.getProduct().getId()));
                orderDetailsDTO.setNumber(orderDetails.getNumber());
                list1.add(orderDetailsDTO);
            }
        }
        return list1;
    }

    @Override
    public ResponseEntity<OrderDetails> addProductByCart(Long id_account, OrderDetailsDTO orderDetailsDTO){
        Optional<Account> account = accountRepository.findById(id_account);
        if(account.isEmpty()) {
            throw  new NotFoundAccount(id_account);
        }
        Set<Cartmodel> cartmodels= account.get().getCartmodels();
        Long id_cart = null;
        for(Cartmodel cartmodel : cartmodels){
            id_cart=cartmodel.getId();
        }
        Optional<Cartmodel> cartmodel = cartmodelRepository.findById(id_cart);
        if(cartmodel.isEmpty()) {
           throw  new NotFoundCart(id_cart);
        }

        if(cartmodel.get().getOrderDetails() != null) {
            Set<OrderDetails> orderDe = cartmodel.get().getOrderDetails();
            for (OrderDetails orderDetail : orderDe) {
                if (orderDetail.getProduct().getId() == Long.parseLong(orderDetailsDTO.getId_product())) {
                    orderDetail.setNumber(orderDetailsDTO.getNumber() + orderDetail.getNumber());
                    orderDetailsRepository.save(orderDetail);
                    Set<OrderDetails> orderDe1 = cartmodel.get().getOrderDetails();
                    float total = 0;
                    for (OrderDetails orderDetail1 : orderDe1) {
                        total = total + (orderDetail1.getPrice() *orderDetail1.getNumber());
                    }
                    cartmodel.get().setTotal(total);
                    cartmodelRepository.save(cartmodel.get());
                    return ResponseEntity.ok().body(orderDetail);
                }
            }
        }
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setNumber(orderDetailsDTO.getNumber());
        orderDetails.setCartmodel(cartmodel.get());
        Optional<Product> product = productRepository.findById(Long.valueOf(orderDetailsDTO.getId_product()));
        if(product.isEmpty())
            throw new NotFoundProductByCategory.NotFoundProduct(Long.valueOf(orderDetailsDTO.getId_product()));
        orderDetails.setPrice(product.get().getPrice()* orderDetailsDTO.getNumber());
        orderDetails.setProduct(product.get());
        orderDetailsRepository.save(orderDetails);
        cartmodelRepository.save(cartmodel.get());
        float total =0 ;
        total = cartmodel.get().getTotal();
        total = total + product.get().getPrice();
        cartmodel.get().setTotal(total);
        cartmodelRepository.save(cartmodel.get());
        return ResponseEntity.ok().body(orderDetails);
    }

}
