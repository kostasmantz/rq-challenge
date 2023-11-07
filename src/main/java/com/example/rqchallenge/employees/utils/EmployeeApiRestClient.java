package com.example.rqchallenge.employees.utils;

import com.example.rqchallenge.employees.dtos.Employee;
import com.example.rqchallenge.employees.responses.EmployeeApiResponse;
import com.example.rqchallenge.employees.dtos.EmployeeDto;
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
      return restTemplate.getForObject("/employees", EmployeeApiResponse.class);
   }

   @Override
   public EmployeeApiResponse<Employee> getById(String id) {
      return restTemplate.getForObject("/employee/" + id, EmployeeApiResponse.class);
   }

   @Override
   public EmployeeApiResponse<EmployeeDto> createEmployee(Map<String, Object> employeeData) {
      return restTemplate.postForObject("/create", employeeData, EmployeeApiResponse.class);
   }

   @Override
   public EmployeeApiResponse<Void> deleteEmployee(String employeeId) {
      return restTemplate.getForObject("/delete/" + employeeId, EmployeeApiResponse.class);
   }
}
