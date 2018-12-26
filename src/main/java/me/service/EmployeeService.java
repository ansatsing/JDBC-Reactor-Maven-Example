package me.service;

import me.domain.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {
    Mono<Employee> findById(int id);

    Mono<Employee> findByName(String name);

    Flux<Employee> findAll();

    Mono<Employee> createOne(Employee employee);
}
