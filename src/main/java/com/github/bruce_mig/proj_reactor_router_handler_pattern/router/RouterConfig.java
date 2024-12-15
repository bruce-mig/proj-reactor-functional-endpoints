package com.github.bruce_mig.proj_reactor_router_handler_pattern.router;

import com.github.bruce_mig.proj_reactor_router_handler_pattern.handler.CustomerHandler;
import com.github.bruce_mig.proj_reactor_router_handler_pattern.handler.CustomerStreamHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    private final CustomerHandler handler;
    private final CustomerStreamHandler streamHandler;

    public RouterConfig(CustomerHandler handler, CustomerStreamHandler streamHandler) {
        this.handler = handler;
        this.streamHandler = streamHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions.route()
                .GET("/router/customers", handler::loadCustomers)
                .GET("/router/customers/stream",streamHandler::getCustomers)
                .GET("/router/customers/{input}",handler::findCustomerById)
                .POST("/router/customers/save", handler::saveCustomer)
                .build();
    }
}
