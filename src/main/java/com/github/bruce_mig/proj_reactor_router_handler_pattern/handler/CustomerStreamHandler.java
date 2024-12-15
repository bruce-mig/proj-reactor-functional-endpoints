package com.github.bruce_mig.proj_reactor_router_handler_pattern.handler;

import com.github.bruce_mig.proj_reactor_router_handler_pattern.dao.CustomerDao;
import com.github.bruce_mig.proj_reactor_router_handler_pattern.dto.Customer;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerStreamHandler {

    private final CustomerDao dao;

    public CustomerStreamHandler(CustomerDao dao) {
        this.dao = dao;
    }

    public Mono<ServerResponse> getCustomers(ServerRequest request) {
        Flux<Customer> customersStream = dao.getCustomersStream();
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(customersStream, Customer.class);
    }
}
