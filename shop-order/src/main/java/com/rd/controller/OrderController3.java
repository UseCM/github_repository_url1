package com.rd.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.rd.service.OrderService3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

//@RestController
@Slf4j
public class OrderController3 {



    @Autowired
    private OrderService3 orderService3;
    int i = 0;

    @RequestMapping("/order/message1")
    public String message1(){
       // orderService2.message();
        return "高并发测试1";
    }

    @RequestMapping("/order/message2")
    public String message2() {
       // orderService2.message();

        return "高并发测试2";
    }

    @RequestMapping("/order/message3")
    @SentinelResource("message3")
    public String message3(String name,Integer age) {
        // orderService2.message();

        return "高并发测试3" + name + age;
    }

    @RequestMapping("/order/message")
    public String message4(){
        return orderService3.message();
    }
}
