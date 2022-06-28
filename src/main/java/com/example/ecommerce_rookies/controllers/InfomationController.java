package com.example.ecommerce_rookies.controllers;


import com.example.ecommerce_rookies.models.Infomation;
import com.example.ecommerce_rookies.repository.InfomationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/auth/infomation")
public class InfomationController {
    @Autowired
    private InfomationRepository infomationRepository;

    @GetMapping("")
    public List<Infomation> GetListInfomations(){
        return infomationRepository.findAll();
    }
}
