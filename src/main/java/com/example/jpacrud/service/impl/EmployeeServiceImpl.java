package com.example.jpacrud.service.impl;

import com.example.jpacrud.entity.Employee;
import com.example.jpacrud.repository.EmployeeRepository;
import com.example.jpacrud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> updateEmployee(Long id, Employee employee) {
        return employeeRepository.findById(id).map(existingEmployee -> {
            employee.setId(id);
            return employeeRepository.save(employee);
        });
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
