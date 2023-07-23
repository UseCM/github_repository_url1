package com.rd.service.fallback;

import com.rd.pojo.Product;
import com.rd.service.impl.ProductService;
import org.springframework.stereotype.Service;

//对ProductService 中无法获取service-product服务时的情况进行处理

@Service
public class FallbackProduct implements ProductService {

    //对ProductService 中findByPid服务出错时的情况进行处理
    @Override
    public Product findByPid(Integer pid) {
        Product product = new Product();
        product.setPid(-1);
        product.setPname("远程调用失败，容错逻辑");
        product.setPprice(0);
        product.setStock(0);
        return product;
    }
}
