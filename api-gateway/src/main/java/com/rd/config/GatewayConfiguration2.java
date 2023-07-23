package com.rd.config;

import com.alibaba.csp.sentinel.adapter.gateway.common.SentinelGatewayConstants;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPathPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.exception.SentinelGatewayBlockExceptionHandler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.*;

@Configuration
public class GatewayConfiguration2 {

    private final List<ViewResolver> viewResolvers;
    private final ServerCodecConfigurer serverCodecConfigurer;

    public GatewayConfiguration2(ObjectProvider<List<ViewResolver>> viewResolversProvider,
                                 ServerCodecConfigurer serverCodecConfigurer) {
        this.viewResolvers = viewResolversProvider.getIfAvailable(Collections::emptyList);
        this.serverCodecConfigurer = serverCodecConfigurer;
    }

    // 配置限流的异常处理器
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler() {
        // Register the block exception handler for Spring Cloud Gateway.
        return new SentinelGatewayBlockExceptionHandler(viewResolvers, serverCodecConfigurer);
    }

    // 初始化一个限流的过滤器
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public GlobalFilter sentinelGatewayFilter() {
        return new SentinelGatewayFilter();
    }

    /**
     * 配置初始化的限流參數
     */
    @PostConstruct
    public void initGatewayRules() {
        Set<GatewayFlowRule> rules = new HashSet<GatewayFlowRule>();
        rules.add(
                new GatewayFlowRule("product_api_001")    // 资源名称，可以是sentinel的API定义的分组
                        .setCount(1)        // 限流阈值
                        .setIntervalSec(5)   // 统计时间窗口（即时间段内达到阈值实行限流），单位：秒。默认是1秒
        );

        rules.add(
                new GatewayFlowRule("product_api_002")    // 资源名称，可以是sentinel的API定义的分组
                        .setCount(1)        // 限流阈值
                        .setIntervalSec(5)   // 统计时间窗口（即时间段内达到阈值实行限流），单位：秒。默认是1秒
        );

        GatewayRuleManager.loadRules(rules);
    }

    // 自定义API分组
    @PostConstruct
    private void initCustomizedApis() {
        Set<ApiDefinition> definitions = new HashSet<>();

        Set predicateItemSet1 = new HashSet();
        ApiDefinition api1 = new ApiDefinition("product_api_001")

                .setPredicateItems(new HashSet<ApiPredicateItem>() {{

                    // 以/product-serv/product/api1 开头的请求

                    add(new ApiPathPredicateItem().setPattern("/product-serv/product/api001/**").

                            setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_PREFIX));

                }});

        Set predicateItemSet2 = new HashSet();
        predicateItemSet2.add(new ApiPathPredicateItem()
                .setPattern("/product-serv/product/api002/demo1")
                .setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_EXACT));  // 精确匹配
        ApiDefinition api2 = new ApiDefinition("product_api_002")
                .setPredicateItems(predicateItemSet2);

        definitions.add(api1);
        definitions.add(api2);

        GatewayApiDefinitionManager.loadApiDefinitions(definitions);
    }

    /**
     * 自定义限流异常页面，限流返回的信息
     */
    @PostConstruct
    public void initBlockHandlers() {
        BlockRequestHandler blockRequestHandler = new BlockRequestHandler() {
            @Override
            public Mono<ServerResponse> handleRequest(ServerWebExchange serverWebExchange, Throwable throwable) {
                Map map = new HashMap();
                map.put("code", 0);
                map.put("message", "接口被限流了");

                return ServerResponse.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(BodyInserters.fromObject(map));
            }
        };

        GatewayCallbackManager.setBlockHandler(blockRequestHandler);
    }
}



