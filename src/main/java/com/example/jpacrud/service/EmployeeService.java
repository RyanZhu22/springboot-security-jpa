package com.example.jpacrud.service;

import com.example.jpacrud.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);
    Optional<Employee> getEmployeeById(Long id);
    List<Employee> getAllEmployees();
    Optional<Employee> updateEmployee(Long id, Employee employee);
    void deleteEmployee(Long id);
}
