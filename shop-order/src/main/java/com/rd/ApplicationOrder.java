package com.rd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EntityScan("com.rd.pojo")
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ApplicationOrder {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationOrder.class,args);
    }


    //RestTemplate 提供高度封装的接口，可以让我们非常方便地进行 Rest API 调用。
    @Bean
    @LoadBalanced  //ribbon启用负载均衡
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }


}
