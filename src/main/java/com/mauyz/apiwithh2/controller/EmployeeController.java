package com.mauyz.apiwithh2.controller;

import com.mauyz.apiwithh2.model.Employee;
import com.mauyz.apiwithh2.service.EmployeeService;
import com.mauyz.apiwithh2.util.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/employees")
    public ResponseEntity<ApiResponse<?>> getEmployees() {
        return employeeService.getEmployees();
    }

    @PostMapping("/employees")
    public ResponseEntity<ApiResponse<?>> saveEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<ApiResponse<?>> deleteEmployee(@PathVariable("id") Long id) {
        return employeeService.deleteEmployee(id);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ResponseEntity<?> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception
    ) {
        var errors = new HashMap<String, String>();
        exception.getBindingResult()
                .getAllErrors()
                .forEach(error -> {
                    var fieldName = ((FieldError)error).getField();
                    var errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });
        return  new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
