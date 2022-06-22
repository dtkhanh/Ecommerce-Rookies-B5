package com.example.ecommerce_rookies.Controllers;

import com.example.ecommerce_rookies.Database.ResponseObject;
import com.example.ecommerce_rookies.Models.Product;
import com.example.ecommerce_rookies.Repository.ProductRepository;
import com.example.ecommerce_rookies.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/products")
public class ProductController {

}
