package com.example.rqchallenge.employees.utils;

import com.example.rqchallenge.employees.dtos.Employee;
import com.example.rqchallenge.employees.responses.EmployeeApiResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class EmployeeApiRestClient implements EmployeeApiClient {

   private final RestTemplate restTemplate;

   public EmployeeApiRestClient(RestTemplate restTemplate) {
      this.restTemplate = restTemplate;
   }

   public EmployeeApiResponse<List<Employee>> getEmployees() {
      return restTemplate.getForObject("https://dummy.restapiexample.com/api/v1/employees", EmployeeApiResponse.class);
   }

   @Override
   public EmployeeApiResponse<Employee> getById(String id) {
      return restTemplate.getForObject("https://dummy.restapiexample.com/api/v1/employee/" + id, EmployeeApiResponse.class);
   }
}
