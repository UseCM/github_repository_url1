package com.rd.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.RequestOriginParser;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

//用于设置授权规则(流控应用)

@Component
public class RequestOriginParserDefinition implements RequestOriginParser {
    @Override
    public String parseOrigin(HttpServletRequest httpServletRequest) {

        String serviceName = httpServletRequest.getParameter("serviceName");
        return serviceName;
    }
}
