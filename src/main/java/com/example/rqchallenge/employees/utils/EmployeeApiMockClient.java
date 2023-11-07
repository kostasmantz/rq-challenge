package com.example.rqchallenge.employees.utils;

import com.example.rqchallenge.employees.responses.EmployeesResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Component
@Primary
public class EmployeeApiMockClient implements EmployeeApiClient {



   @Override
   public EmployeesResponse getEmployees() {
      InputStream inputStream;
      try {
         inputStream = new ClassPathResource("mockapi/employees_mock_response.json").getInputStream();
         String data = new BufferedReader(new InputStreamReader(inputStream))
            .lines().collect(Collectors.joining("\n"));
         return new ObjectMapper().readValue(data, EmployeesResponse.class);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

}
