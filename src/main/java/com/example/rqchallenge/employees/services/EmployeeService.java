package com.example.rqchallenge.employees.services;

import com.example.rqchallenge.employees.dtos.Employee;
import com.example.rqchallenge.employees.responses.EmployeeApiResponse;
import com.example.rqchallenge.employees.utils.EmployeeApiClient;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService implements IEmployeeService {

   private final EmployeeApiClient employeeApiClient;

   public EmployeeService(EmployeeApiClient employeeApiClient) {
      this.employeeApiClient = employeeApiClient;
   }

   @Override
   public List<Employee> getAllEmployees() {
      return Optional.of(employeeApiClient.getEmployees())
         .filter(EmployeeApiResponse::isSuccessful)
         .map(EmployeeApiResponse::getData)
         .orElseThrow(() -> new RuntimeException(""));
   }

   @Override
   public List<Employee> getEmployeesByName(String name) {
      return Optional.of(employeeApiClient.getEmployees())
         .filter(EmployeeApiResponse::isSuccessful)
         .map(EmployeeApiResponse::getData)
         .map(employees -> getEmployeesMatchingName(name, employees))
         .orElseThrow(() -> new RuntimeException(""));
   }

   @Override
   public Employee getEmployeeById(String id) {
      return Optional.of(employeeApiClient.getById(id))
         .filter(EmployeeApiResponse::isSuccessful)
         .map(EmployeeApiResponse::getData)
         .orElseThrow(() -> new RuntimeException(""));
   }

   @Override
   public Integer getHighestSalary() {
      return getAllEmployees().stream()
         .max(Comparator.comparing(Employee::getEmployeeSalary))
         .map(Employee::getEmployeeSalary)
         .orElseThrow(() -> new RuntimeException());
   }

   @Override
   public List<String> getHighestEarningEmployees() {
      return getAllEmployees().stream()
         .sorted(Comparator.comparing(Employee::getEmployeeSalary).reversed())
         .limit(10)
         .map(Employee::getEmployeeName)
         .collect(Collectors.toList());
   }

   @Override
   public String createEmployee(Map<String, Object> employeeInput) {
      return Optional.of(employeeApiClient.createEmployee(employeeInput))
         .filter(EmployeeApiResponse::isSuccessful)
         .map(EmployeeApiResponse::getStatus)
         .orElseThrow(() -> new RuntimeException());
   }

   @Override
   public String deleteEmployee(String id) {
      return Optional.of(getEmployeeById(id))
         .filter(employee -> employeeApiClient.deleteEmployee(id).isSuccessful())
         .map(Employee::getEmployeeName)
         .orElseThrow(() -> new RuntimeException());
   }

   private List<Employee> getEmployeesMatchingName(String name, List<Employee> employees) {
      return employees.stream().filter(employee -> employee.getEmployeeName().contains(name)).collect(Collectors.toList());
   }
}
