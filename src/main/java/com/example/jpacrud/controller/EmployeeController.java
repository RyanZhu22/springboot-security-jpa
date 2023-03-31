package com.example.jpacrud.controller;

import com.example.jpacrud.entity.Employee;
import com.example.jpacrud.service.EmployeeService;
import com.example.jpacrud.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.jpacrud.enums.ResponseStatus;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Create
    @PostMapping
    public ResponseEntity<Result<Employee>> createEmployee(@RequestBody Employee employee) {
        Employee createdEmployee = employeeService.saveEmployee(employee);
        return createResponseEntity(ResponseStatus.CREATED, HttpStatus.CREATED, createdEmployee);
    }

    // Read (single)
    @GetMapping("/{id}")
    public ResponseEntity<Result<Employee>> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        return employee.map(value -> createResponseEntity(ResponseStatus.SUCCESS, HttpStatus.OK, value))
                .orElseGet(() -> createResponseEntity(ResponseStatus.NOT_FOUND, HttpStatus.NOT_FOUND, null));
    }

    // Read (all)
    @GetMapping
    public ResponseEntity<Result<List<Employee>>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return createResponseEntity(ResponseStatus.SUCCESS, HttpStatus.OK, employees);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Result<Employee>> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        Optional<Employee> updatedEmployee = employeeService.updateEmployee(id, employee);
        return updatedEmployee.map(value -> createResponseEntity(ResponseStatus.SUCCESS, HttpStatus.OK, value))
                .orElseGet(() -> createResponseEntity(ResponseStatus.NOT_FOUND, HttpStatus.NOT_FOUND, null));
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Result<Void>> deleteEmployee(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        if (employee.isPresent()) {
            employeeService.deleteEmployee(id);
            return createResponseEntity(ResponseStatus.SUCCESS, HttpStatus.NO_CONTENT, null);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    private <T> ResponseEntity<Result<T>> createResponseEntity(ResponseStatus status, HttpStatus httpStatus, T data) {
        Result<T> result = new Result<>(status, data);
        return new ResponseEntity<>(result, httpStatus);
    }
}



