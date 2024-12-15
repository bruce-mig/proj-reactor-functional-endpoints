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
public class CustomerHandler {

    private final CustomerDao dao;

    public CustomerHandler(CustomerDao dao) {
        this.dao = dao;
    }


    public Mono<ServerResponse> loadCustomers(ServerRequest request) {
        Flux<Customer> customerList = dao.getCustomerList();
        return ServerResponse.ok().body(customerList, Customer.class);
    }

    public Mono<ServerResponse> findCustomerById(ServerRequest request) {
        int customerId = Integer.parseInt(request.pathVariable("input"));
//        dao.getCustomerList().filter(c -> c.getId() == customerId).take(1).single();
        Mono<Customer> customerMono = dao.getCustomerList().filter(c -> c.getId() == customerId).next();
        return ServerResponse.ok().body(customerMono, Customer.class);

    }

    public Mono<ServerResponse> saveCustomer(ServerRequest request) {
        Mono<Customer> customerMono = request.bodyToMono(Customer.class);
        Mono<String> saveResponse = customerMono.map(dto -> dto.getId() + ":" + dto.getName());
        return ServerResponse.ok().body(saveResponse, String.class);
    }

}
