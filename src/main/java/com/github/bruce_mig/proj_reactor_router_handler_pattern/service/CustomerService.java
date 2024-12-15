package com.github.bruce_mig.proj_reactor_router_handler_pattern.service;

import com.github.bruce_mig.proj_reactor_router_handler_pattern.dao.CustomerDao;
import com.github.bruce_mig.proj_reactor_router_handler_pattern.dto.Customer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerDao dao;

    public CustomerService(CustomerDao dao) {
        this.dao = dao;
    }

    public List<Customer> loadAllCustomers() {
        long start = System.currentTimeMillis();
        List<Customer> customers = dao.getCustomers();
        long end = System.currentTimeMillis();
        System.out.println("Load all customers in " + (end - start) + " ms");
        return customers;
    }

    public Flux<Customer> loadAllCustomersStream() {
        long start = System.currentTimeMillis();
        Flux<Customer> customers = dao.getCustomersStream();
        long end = System.currentTimeMillis();
        System.out.println("Load all customers in " + (end - start) + " ms");
        return customers;
    }
}
