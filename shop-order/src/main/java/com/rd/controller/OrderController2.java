package com.rd.controller;

import com.rd.pojo.Order;
import com.rd.pojo.Product;
import com.rd.pojo.User;
import com.rd.service.impl.IOrderService;
import com.rd.service.impl.ProductService;
import com.rd.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

//@RestController
@Slf4j
public class OrderController2{

    @Autowired
    private IOrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @GetMapping("/order/{oid}")
    public Order order(@PathVariable("oid") Long oid){
        return orderService.findByOid(oid);
    }

    @GetMapping("/order/save/{pid}/{uid}")
    public Order save(@PathVariable("pid") Integer pid,@PathVariable("uid") Integer uid){

        Order order = new Order();

        Product product = productService.findByPid(pid);
        User user = userService.findByUid(uid);

        try {
            Thread.sleep(101);
        } catch (InterruptedException e) { //手动抛出异常，进行压力测试
            e.printStackTrace();
        }

        order.setNumber(2);
        order.setPid(product.getPid());
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setUid(user.getUid());
        order.setUsername(user.getUsername());

       // orderService.save(order);

        return order;
    }

    @RequestMapping("/order/message")
    public String message(){
        return "高并发测试";
    }

}
