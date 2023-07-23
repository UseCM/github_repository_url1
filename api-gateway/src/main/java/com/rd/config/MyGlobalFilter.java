package com.rd.config;

import com.alibaba.nacos.client.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

//@Component
public class MyGlobalFilter  implements GlobalFilter, Ordered {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("全局过滤器");
        String token = exchange.getRequest().getQueryParams().getFirst("token");//获取第一个名为token的请求头
        System.out.println(token);
        //无权限
        if (!StringUtils.equals(token,"admin")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);//如果不是返回状态码401
            return exchange.getResponse().setComplete();
        }
        //有权限
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        //值越小，越优先执行
        return 0;
    }
}
