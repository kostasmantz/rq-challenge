package com.example.rqchallenge.employees.utils;

import com.example.rqchallenge.employees.dtos.Employee;
import com.example.rqchallenge.employees.responses.EmployeeApiResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Primary
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

   private <T> EmployeeApiResponse<T> getMockData(String filePath, TypeReference<EmployeeApiResponse<T>> typeReference) {
      InputStream inputStream;
      try {
         inputStream = new ClassPathResource(filePath).getInputStream();
         String data = new BufferedReader(new InputStreamReader(inputStream))
            .lines().collect(Collectors.joining("\n"));
         return new ObjectMapper().readValue(data, typeReference);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }
}
