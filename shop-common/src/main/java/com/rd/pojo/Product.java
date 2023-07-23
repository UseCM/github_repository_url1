package com.rd.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "shop_product")//声明是一个实体类
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pid;//商品编号
    private String pname;//商品名称
    private double pprice;//商品价格
    private Integer stock;//商品数量
}
