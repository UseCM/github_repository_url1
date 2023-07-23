package com.rd.service.impl;

import com.rd.pojo.Order;

public interface IOrderService {

    Order findByOid(Long oid);
    Order save(Order order);
}
