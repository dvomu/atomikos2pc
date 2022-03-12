package com.dvomu.controller;

import com.dvomu.pojo.OrderInfo;
import com.dvomu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class BeController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/insert")
    public void insert(){

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setAddress("456");
        orderInfo.setCreateTime(new Date());
        orderInfo.setMoney(1.00);
        orderInfo.setId(3);
        orderService.addOrder(orderInfo);
    }

}

