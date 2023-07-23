package com.rd.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "shop_order")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oid;//订单编号
    private Integer uid;//用户id
    private String username;//用户名称
    private Integer pid;//商品id
    private String  pname;//商品名称
    private Double pprice;//商品价格
    private Integer number;//商品数量

}
