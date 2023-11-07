package com.example.rqchallenge.employees.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeApiResponse<T> {

   private String status;
   private T data;
   private String message;

   public boolean isSuccessful() {
      return "success".equals(status);
   }

}
