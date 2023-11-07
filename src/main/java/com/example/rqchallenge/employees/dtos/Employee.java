package com.example.rqchallenge.employees.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {

   Long id;
   @JsonProperty("employee_name")
   String employeeName;
   @JsonProperty("employee_salary")
   Long employeeSalary;
   @JsonProperty("employee_age")
   Integer employeeAge;
   @JsonProperty("profile_image")
   String profileImage;

}
