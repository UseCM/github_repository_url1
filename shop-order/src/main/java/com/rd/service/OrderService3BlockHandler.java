package com.rd.service;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;

//完成Service3的message方法 限流或降级后的处理类

@Slf4j
public class OrderService3BlockHandler {

    //添加BlockException时候进入的方法
    public static String blockHandler(BlockException ex)
    {
        log.error("{}",ex);
        return "接口被限流或者降级了";
    }

}
