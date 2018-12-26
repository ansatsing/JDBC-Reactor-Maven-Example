package me.service;

import me.domain.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    Employee findById(int id);

    Employee findByName(String name);
}
