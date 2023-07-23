package com.rd.service.impl;

import com.rd.pojo.Product;
import com.rd.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("service-user")
public interface UserService {
    @GetMapping("/user/{uid}")
    User findByUid(@PathVariable("uid") Integer uid);
}
