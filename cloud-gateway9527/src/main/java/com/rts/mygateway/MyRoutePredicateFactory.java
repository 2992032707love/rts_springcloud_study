package com.rts.mygateway;

import jakarta.validation.constraints.NotNull;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * 需求说明：自定义配置会员等级userTpye，按照钻/金/银和yml配置的会员等级，以适配是否可以访问
 */
@Component
public class MyRoutePredicateFactory extends AbstractRoutePredicateFactory<MyRoutePredicateFactory.Config> {

    public static final String USER_LEVEL = "userLevel";

    public MyRoutePredicateFactory() {
        super(MyRoutePredicateFactory.Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList(USER_LEVEL);
    }

    @Override
    public Predicate<ServerWebExchange> apply(MyRoutePredicateFactory.Config config) {
        return new Predicate<ServerWebExchange>() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                String userLevel = serverWebExchange.getRequest().getQueryParams().getFirst("userLevel");
                if (userLevel == null) {
                    return false;
                }
                if (userLevel.equalsIgnoreCase(config.getUserLevel())) {
                    return true;
                }
                return false;
            }

        };
    }
    @Validated
    public static class Config {

        @NotNull
        private String userLevel;

        public Config() {

        }

        public String getUserLevel() {
            return userLevel;
        }

        public void setUserLevel(String userLevel) {
            this.userLevel = userLevel;
        }
    }
}
