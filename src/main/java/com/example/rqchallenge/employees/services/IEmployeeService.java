package com.example.rqchallenge.employees.services;

import com.example.rqchallenge.employees.dtos.Employee;

import java.util.List;
import java.util.Map;

public interface IEmployeeService {

   List<Employee> getAllEmployees();

   List<Employee> getEmployeesByName(String name);

   Employee getEmployeeById(String id);

   Integer getHighestSalary();

   List<String> getHighestEarningEmployees();

   String createEmployee(Map<String, Object> employeeInput);

   String deleteEmployee(String id);
}
