package com.rts.mygateway;

import com.alibaba.fastjson2.JSONObject;
import com.rts.common.ResultCode;
import com.rts.common.ResultJson;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/5/31 19:22
 **/
@Component
public class MyGatewayFilterFactory extends AbstractGatewayFilterFactory<MyGatewayFilterFactory.Config> {

    public MyGatewayFilterFactory() {
        super(MyGatewayFilterFactory.Config.class);
    }

    @Override
    public GatewayFilter apply(MyGatewayFilterFactory.Config config) {

        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                ServerHttpRequest request = exchange.getRequest();
                System.out.println("进入了自定义网关过滤器 MyGatewayFilterFactory, status: " + config.getStatus());
                if (request.getQueryParams().containsKey("RTS")) {
                    return chain.filter(exchange);
                } else {
//                    exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
//                    return exchange.getResponse().setComplete();
                    return error(exchange.getResponse(),ResultJson.custom(ResultCode.RC400.getCode(), ResultCode.RC400.getMessage(), "null"));
                }

            }
        };
    }

    private Mono<Void> error(ServerHttpResponse response, ResultJson resultJson) {
        response.setStatusCode(HttpStatus.OK);
//        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getHeaders().set("Content-Type", "application/json;charset=utf-8");
//        response.getHeaders().setContentType(MediaType.parseMediaType("application/json;charset=utf-8"));
        response.getHeaders().setZonedDateTime("Date", ZonedDateTime.now());
        JSONObject jsonObject = new JSONObject();
        byte[] bytes = new byte[0];
        try {
            bytes = jsonObject.toJSONString(resultJson).getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        DataBuffer buffer = response.bufferFactory().wrap(bytes);
        return response.writeWith(Flux.just(buffer));
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("status");
    }

    public static class Config {
        @Getter@Setter
        private String status;//设定一个状态值/标志位，它等于多时，匹配才可以访问
    }
}
