package com.example.rqchallenge.employees.utils;

import com.example.rqchallenge.employees.dtos.Employee;
import com.example.rqchallenge.employees.responses.EmployeeApiResponse;

import java.util.List;

public interface EmployeeApiClient {

   EmployeeApiResponse<List<Employee>> getEmployees();

   EmployeeApiResponse<Employee> getById(String id);
}
