package com.example.rqchallenge.employees.utils;

import com.example.rqchallenge.employees.responses.EmployeesResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class EmployeeApiRestClient implements EmployeeApiClient {

   private final RestTemplate restTemplate;

   public EmployeeApiRestClient(RestTemplate restTemplate) {
      this.restTemplate = restTemplate;
   }

   public EmployeesResponse getEmployees() {
      return restTemplate.getForObject("https://dummy.restapiexample.com/api/v1/employees", EmployeesResponse.class);
   }
}
