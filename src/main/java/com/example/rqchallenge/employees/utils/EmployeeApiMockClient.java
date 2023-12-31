package com.example.rqchallenge.employees.utils;

import com.example.rqchallenge.employees.dtos.Employee;
import com.example.rqchallenge.employees.responses.EmployeeApiResponse;
import com.example.rqchallenge.employees.dtos.EmployeeDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Primary
@Slf4j
public class EmployeeApiMockClient implements EmployeeApiClient {

   @Override
   public EmployeeApiResponse<List<Employee>> getEmployees() {
      return getMockData("mockapi/employees_response.json", new TypeReference<>() {
      });
   }

   @Override
   public EmployeeApiResponse<Employee> getById(String id) {
      return getMockData("mockapi/employee_by_id_response.json", new TypeReference<>() {
      });
   }

   @Override
   public EmployeeApiResponse<EmployeeDto> createEmployee(Map<String, Object> employeeData) {
      return getMockData("mockapi/employee_creation_response.json", new TypeReference<>() {
      });
   }

   @Override
   public EmployeeApiResponse<Void> deleteEmployee(String employeeId) {
      return getMockData("mockapi/employee_deletion_response.json", new TypeReference<>() {
      });
   }

   private <T> EmployeeApiResponse<T> getMockData(String filePath, TypeReference<EmployeeApiResponse<T>> typeReference) {
      InputStream inputStream;
      try {
         inputStream = new ClassPathResource(filePath).getInputStream();
         String data = new BufferedReader(new InputStreamReader(inputStream))
            .lines().collect(Collectors.joining("\n"));
         return new ObjectMapper().readValue(data, typeReference);
      } catch (IOException e) {
         log.error("Failed to read data from file {}", filePath);
         throw new RuntimeException(e);
      }
   }
}
