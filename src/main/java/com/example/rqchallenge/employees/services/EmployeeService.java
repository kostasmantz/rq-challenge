package com.example.rqchallenge.employees.services;

import com.example.rqchallenge.employees.dtos.Employee;
import com.example.rqchallenge.employees.responses.EmployeesResponse;
import com.example.rqchallenge.employees.utils.EmployeeApiClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService {

   private final EmployeeApiClient employeeApiClient;

   public EmployeeService(EmployeeApiClient employeeApiClient) {
      this.employeeApiClient = employeeApiClient;
   }

   @Override
   public List<Employee> getAllEmployees() {
      return Optional.of(employeeApiClient.getEmployees())
         .filter(EmployeesResponse::isSuccessful)
         .map(EmployeesResponse::getData)
         .orElseThrow(() -> new RuntimeException(""));
   }
}
