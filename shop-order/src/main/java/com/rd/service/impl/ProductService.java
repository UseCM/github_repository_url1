package com.rd.service.impl;

import com.rd.pojo.Product;
import com.rd.service.fallback.FallbackProduct;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//声明式的伪Http客户端它使得调用远程服务就像调用本地服务一样简单，Nacos下使用Fegin默认就实现了负载均衡的效果
//码出高效 java开发手册+阿里巴巴java开发手册

@Service
@FeignClient(value = "service-product",fallback = FallbackProduct.class)//获取service-product服务的名字，并声明 获取失败 后的处理类
public interface ProductService {

    //指定调用提供者的哪个方法
    //@FeignClient+@GetMapping 就是一个完整的请求路径 http://serviceproduct/

    @GetMapping("/product/{pid}")
    Product findByPid(@PathVariable("pid") Integer pid);
}
