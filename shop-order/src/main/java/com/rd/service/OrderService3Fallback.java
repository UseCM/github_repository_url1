package com.rd.service;

import lombok.extern.slf4j.Slf4j;

//完成对Service3的message方法 错误或失败后的处理类

@Slf4j
public class OrderService3Fallback {

    //添加Throwable时候执行的方法
    public static String  fallback(Throwable throwable)
    {
        log.error("{}",throwable);
        return "接口异常了";
    }
}
