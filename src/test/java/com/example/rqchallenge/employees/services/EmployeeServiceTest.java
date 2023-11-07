package com.example.rqchallenge.employees.services;

import com.example.rqchallenge.employees.dtos.Employee;
import com.example.rqchallenge.employees.dtos.EmployeeDto;
import com.example.rqchallenge.employees.responses.EmployeeApiResponse;
import com.example.rqchallenge.employees.utils.EmployeeApiClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

   @Mock
   private EmployeeApiClient mockApiClient;

   @InjectMocks
   private EmployeeService employeeService;

   @Test
   void getAllEmployees_forSuccessfulApiCall_shouldReturnListOfEmployees() {
      //given
      when(mockApiClient.getEmployees()).thenReturn(getMockEmployeesList());

      //when
      List<Employee> employees = employeeService.getAllEmployees();

      //then
      assertFalse(employees.isEmpty());
      assertTrue(employees.stream().anyMatch(e -> e.getId() == 1L));
      assertTrue(employees.stream().anyMatch(e -> e.getEmployeeName().equals("B")));
   }

   @Test
   void getAllEmployees_forUnSuccessfulApiCall_shouldThrowException() {
      //given
      when(mockApiClient.getEmployees()).thenReturn(new EmployeeApiResponse<>("Error", List.of(), ""));

      //then
      assertThrows(RuntimeException.class, () -> employeeService.getAllEmployees());
   }

   @Test
   void getEmployeesByName_forSuccessfulApiCall_shouldReturnListOfEmployeesMatchingTheGivenName() {
      //given
      when(mockApiClient.getEmployees()).thenReturn(getMockEmployeesList());
      String employeeName = "B";

      //when
      List<Employee> employees = employeeService.getEmployeesByName(employeeName);

      //then
      assertFalse(employees.isEmpty());
      assertEquals(2, employees.size());
      assertTrue(employees.stream().allMatch(e -> e.getEmployeeName().equals(employeeName)));
   }

   @Test
   void getEmployeesByName_forUnSuccessfulApiCall_shouldThrowException() {
      //given
      when(mockApiClient.getEmployees()).thenReturn(new EmployeeApiResponse<>("Error", List.of(), ""));
      String employeeName = "B";

      //then
      assertThrows(RuntimeException.class, () -> employeeService.getEmployeesByName(employeeName));
   }

   @Test
   void getEmployeeById_forSuccessfulApiCall_shouldReturnEmployee() {
      //given
      EmployeeApiResponse<Employee> response = new EmployeeApiResponse<>("success",
         new Employee(1L, "A", 500, 32, ""),
         "");
      when(mockApiClient.getById(anyString())).thenReturn(response);

      //when
      Employee employee = employeeService.getEmployeeById("1");

      //then
      assertNotNull(employee);
      assertEquals(1L, employee.getId());
      assertEquals("A", employee.getEmployeeName());
   }

   @Test
   void getHighestSalary_forSuccessfulApiCall_shouldReturnHighestSalary() {
      //given
      when(mockApiClient.getEmployees()).thenReturn(getMockEmployeesList());

      //when
      Integer highestSalary = employeeService.getHighestSalary();

      //then
      assertEquals(150, highestSalary);
   }

   @Test
   void getHighestSalary_forUnSuccessfulApiCall_shouldThrowException() {
      //given
      when(mockApiClient.getEmployees()).thenReturn(new EmployeeApiResponse<>("Error", List.of(), ""));

      //then
      assertThrows(RuntimeException.class, () -> employeeService.getHighestSalary());
   }

   @Test
   void getHighestEarningEmployees_forSuccessfulApiCall_shouldReturnHighestEarningEmployees() {
      //given
      when(mockApiClient.getEmployees()).thenReturn(getMockEmployeesList());

      //when
      List<String> employeeNames = employeeService.getHighestEarningEmployees();

      //then
      assertFalse(employeeNames.isEmpty());
      assertEquals(4, employeeNames.size());
      assertTrue(employeeNames.stream().anyMatch(name -> name.equals("B")));
   }

   @Test
   void createEmployee_forSuccessfulCreation_shouldReturnEmployeeData() {
      //given
      EmployeeApiResponse<EmployeeDto> mockResponse = new EmployeeApiResponse<>("success",
         new EmployeeDto(1, "A", "150", "32"),
         "");
      when(mockApiClient.createEmployee(any())).thenReturn(mockResponse);

      //when
      String status = employeeService.createEmployee(Map.of("id", 1));

      //then
      assertEquals("success", status);
   }

   @Test
   void createEmployee_forUnsuccessfulCreation_shouldThrowException() {
      //given
      EmployeeApiResponse<EmployeeDto> mockResponse = new EmployeeApiResponse<>("error",
         new EmployeeDto(1, "A", "150", "32"),
         "");
      when(mockApiClient.createEmployee(any())).thenReturn(mockResponse);

      //then
      assertThrows(RuntimeException.class, () -> employeeService.createEmployee(Map.of("id", 1)));
   }

   private EmployeeApiResponse<List<Employee>> getMockEmployeesList() {
      var apiResponse = new EmployeeApiResponse<List<Employee>>();
      apiResponse.setStatus("success");
      apiResponse.setData(List.of(
         new Employee(1L, "A", 50, 50, ""),
         new Employee(2L, "B", 100, 40, ""),
         new Employee(3L, "C", 150, 30, ""),
         new Employee(4L, "B", 100, 40, "")
      ));

      return apiResponse;
   }
}