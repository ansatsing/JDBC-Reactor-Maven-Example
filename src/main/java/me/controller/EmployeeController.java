package me.controller;

import me.domain.Employee;
import me.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController  {
    @Autowired
    EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.GET, value = "/test")
    public String testConnection(){
        return "OK";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public Flux<Employee> getAll(){
        return employeeService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public Mono<Employee> save(@RequestBody Employee employee){
        return employeeService.createOne(employee);
    }


}
