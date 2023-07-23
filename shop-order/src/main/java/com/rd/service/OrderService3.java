package com.rd.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderService3 {
    int i = 0;
    @SentinelResource(
            value="message" ,//被保护资源名
         /*   blockHandler ="blockHandler",  //指发生blockException时候要执行的方法
            fallback ="fallback"      //表示发生Throwable时候要执行的方法*/
            blockHandlerClass = OrderService3BlockHandler.class,
            blockHandler = "blockHandler",
            fallbackClass = OrderService3Fallback.class,
            fallback = "fallback"
    )  //通过@SentinelResource控制资源以及如何配置控制策略。
    public String message() {  //被保护资源
        i++;
        if (i % 3 == 0) {
            throw new RuntimeException();//手动制造异常并抛出
        }
        return "message";
    }

}
