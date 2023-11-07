package com.example.rqchallenge.employees.responses;

import com.example.rqchallenge.employees.dtos.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeesResponse {

   private String status;
   private List<Employee> data;
   private String message;

   public boolean isSuccessful() {
      return "success".equals(status);
   }

}
