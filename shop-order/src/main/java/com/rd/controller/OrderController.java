package com.rd.controller;

import com.rd.pojo.Order;
import com.rd.pojo.Product;
import com.rd.pojo.User;
import com.rd.service.impl.IOrderService;
import com.rd.service.impl.ProductService;
import com.rd.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;

@RestController //@Controller和@ResponseBody注解
@Slf4j
public class OrderController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @GetMapping("/order/{oid}")
    public Order order(@PathVariable("oid") Long oid) {
        return orderService.findByOid(oid);
    }

    @GetMapping("/order/save/{pid}/{uid}")
    public Order save(@PathVariable("pid") Integer pid, @PathVariable("uid") Integer uid) {

        //自定义负载均衡
      /*  List<ServiceInstance> list = discoveryClient.getInstances("service-product");
        int number = (int) (Math.random() * 2);
        ServiceInstance serviceInstance = list.get(number);
        String url = serviceInstance.getHost() + ":" + serviceInstance.getPort();
        log.info(number + ":" + url);

      String url ="service-product";

        //调用商品微服务
        // RestTemplate restTemplate = new RestTemplate();
        Product product = restTemplate.getForObject("http://" + url + "/product/" + pid,Product.class);

        //自定义负载均衡
       serviceInstance = discoveryClient.getInstances("service-user").get(0);
        url = serviceInstance.getHost() + ":" + serviceInstance.getPort();

        url ="service-user";

        User user = restTemplate.getForObject("http://" + url + "/user/" + uid,User.class);*/

        Product product = productService.findByPid(pid);
        User user = userService.findByUid(uid);


        if (product.getPid() == -1) {  //对FallbackProduct类测试
            Order order = new Order();

            order.setPname("下单失败");
            return order;
        }
        Order order = new Order();
        order.setPid(product.getPid());
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setUid(user.getUid());
        order.setUsername(user.getUsername());
        order.setNumber(2);
        orderService.save(order);


        return order;
    }

}
