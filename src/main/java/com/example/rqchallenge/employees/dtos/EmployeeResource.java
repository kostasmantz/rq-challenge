package com.example.rqchallenge.employees.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EmployeeResource {

   Integer id;
   String name;
   String salary;
   String age;

}
