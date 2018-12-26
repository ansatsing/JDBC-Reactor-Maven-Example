package me.service;

import me.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@Component("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    @Qualifier("jdbcScheduler")
    private Scheduler jdbcScheduler;

    @Override
    public Mono<Employee> findById(int id) {
        Mono<Employee> employee = Mono.defer(()->Mono.just(this.employeeRepository.findById(id))).subscribeOn(jdbcScheduler);

        //Alternative
        //Mono<Employee> employee = Mono.fromCallable(()->Mono.just(this.employeeRepository.findById(id))).subscribeOn(jdbcScheduler);
        return employee;
    }

    @Override
    public Mono<Employee> findByName(String name) {
        Mono<Employee> employee = Mono.defer(()->Mono.just(this.employeeRepository.findByName(name))).subscribeOn(jdbcScheduler);
        return employee;
    }

    @Override
    public Flux<Employee> findAll() {
        Flux<Employee> listEmployee = Flux.defer(()->Flux.fromIterable(this.employeeRepository.findAll()));
        return listEmployee.subscribeOn(jdbcScheduler);
    }

    @Override
    public Mono<Employee> createOne(Employee employee) {
        return Mono.fromCallable(()-> transactionTemplate.execute(status -> employeeRepository.save(employee))).subscribeOn(jdbcScheduler);
    }
}
