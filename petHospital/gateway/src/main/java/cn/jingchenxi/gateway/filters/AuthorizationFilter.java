package cn.jingchenxi.gateway.filters;

import cn.jingchenxi.gateway.utils.JwtTokenUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author : jingchenxi
 * @since : 2023/2/3
 **/
@Order(0)
@Component
public class AuthorizationFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1、获取请求头
        ServerHttpRequest request = exchange.getRequest();
        MultiValueMap<String, String> params = request.getQueryParams();
        String uri = request.getURI().toString();
        //放行相关接口
        if (uri.contains("login") || uri.contains("register")) {
            return chain.filter(exchange);
        }
        //2、获取其中的Authorization参数的值
        String authorization = params.getFirst("Authorization");
        //3、做出判断
        if (JwtTokenUtil.checkSign(authorization)) {
            return chain.filter(exchange);
        } else {
            System.out.println("缺少token");
            return exchange.getResponse().setComplete();
        }
    }
}
