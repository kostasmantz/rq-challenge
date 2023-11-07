package com.example.rqchallenge.employees.services;

import com.example.rqchallenge.employees.dtos.Employee;

import java.util.List;

public interface IEmployeeService {

   List<Employee> getAllEmployees();

   List<Employee> getEmployeesByName(String name);

   Employee getEmployeeById(String id);
}
