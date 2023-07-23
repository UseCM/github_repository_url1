package com.rd.service;

import com.rd.dao.OrderDao;
import com.rd.pojo.Order;
import com.rd.service.impl.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private OrderDao orderDao;
    @Override
    public Order findByOid(Long oid) {
        return orderDao.findById(oid).get();
    } //返回订单信息

    @Override
    public Order save(Order order) {
        return orderDao.save(order);
    } //下单
}
