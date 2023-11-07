package com.example.rqchallenge.employees.utils;

import com.example.rqchallenge.employees.dtos.Employee;
import com.example.rqchallenge.employees.responses.EmployeeApiResponse;
import com.example.rqchallenge.employees.dtos.EmployeeResource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

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

   @Override
   public EmployeeApiResponse<EmployeeResource> createEmployee(Map<String, Object> employeeData) {
      return restTemplate.postForObject("https://dummy.restapiexample.com/api/v1/create", employeeData, EmployeeApiResponse.class);
   }

   @Override
   public EmployeeApiResponse<Void> deleteEmployee(String employeeId) {
      return restTemplate.getForObject("https://dummy.restapiexample.com/api/v1/create" + employeeId, EmployeeApiResponse.class);
   }
}
