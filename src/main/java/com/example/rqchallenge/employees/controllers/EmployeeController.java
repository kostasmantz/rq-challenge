package com.example.rqchallenge.employees.controllers;

import com.example.rqchallenge.employees.dtos.Employee;
import com.example.rqchallenge.employees.services.IEmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class EmployeeController implements IEmployeeController{

   private final IEmployeeService employeeService;

   public EmployeeController(IEmployeeService employeeService) {
      this.employeeService = employeeService;
   }

   @Override
   public ResponseEntity<List<Employee>> getAllEmployees() throws IOException {
      return ResponseEntity.ok(employeeService.getAllEmployees());
   }

   @Override
   public ResponseEntity<List<Employee>> getEmployeesByNameSearch(String searchString) {
      return ResponseEntity.ok(employeeService.getEmployeesByName(searchString));
   }

   @Override
   public ResponseEntity<Employee> getEmployeeById(String id) {
      return ResponseEntity.ok(employeeService.getEmployeeById(id));
   }

   @Override
   public ResponseEntity<Integer> getHighestSalaryOfEmployees() {
      return null;
   }

   @Override
   public ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames() {
      return null;
   }

   @Override
   public ResponseEntity<Employee> createEmployee(Map<String, Object> employeeInput) {
      return null;
   }

   @Override
   public ResponseEntity<String> deleteEmployeeById(String id) {
      return null;
   }
}
