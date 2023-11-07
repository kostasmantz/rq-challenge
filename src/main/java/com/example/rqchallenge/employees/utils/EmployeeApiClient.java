package com.example.rqchallenge.employees.utils;

import com.example.rqchallenge.employees.dtos.Employee;
import com.example.rqchallenge.employees.responses.EmployeeApiResponse;
import com.example.rqchallenge.employees.dtos.EmployeeResource;

import java.util.List;
import java.util.Map;

public interface EmployeeApiClient {

   EmployeeApiResponse<List<Employee>> getEmployees();

   EmployeeApiResponse<Employee> getById(String id);

   EmployeeApiResponse<EmployeeResource> createEmployee(Map<String, Object> employeeData);

   EmployeeApiResponse<Void> deleteEmployee(String employeeId);
}
