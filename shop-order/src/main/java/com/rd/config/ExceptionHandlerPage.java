package com.rd.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class ExceptionHandlerPage implements UrlBlockHandler {

    @Override
    public void blocked(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws IOException {

        httpServletResponse.setContentType("application/json;charset=utf-8");
        ResponseData data = null;
        if (e instanceof FlowException) {
            data = new ResponseData(-1,"接口被限流了");
        } else if (e instanceof DegradeException) {
            data = new ResponseData(-2,"接口被降级了");
        }else if (e instanceof ParamFlowException) {
            data = new ResponseData(-3,"参数限流异常");
        }else if (e instanceof AuthorityException) {
            data = new ResponseData(-4,"授权异常");
        }else if (e instanceof SystemBlockException) {
            data = new ResponseData(-5,"接口被降级了...");
        }
        httpServletResponse.getWriter().write(JSON.toJSONString(data));
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class ResponseData{

    private int code;
    private String message;

}