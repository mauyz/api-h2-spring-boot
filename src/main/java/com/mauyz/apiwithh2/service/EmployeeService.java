package com.mauyz.apiwithh2.service;

import com.mauyz.apiwithh2.model.Employee;
import com.mauyz.apiwithh2.repository.EmployeeRepository;
import com.mauyz.apiwithh2.util.ApiResponse;
import com.mauyz.apiwithh2.util.ResponseBuilder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Data
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ResponseBuilder responseBuilder;

    public EmployeeService(EmployeeRepository employeeRepository, ResponseBuilder responseBuilder) {
        this.employeeRepository = employeeRepository;
        this.responseBuilder = responseBuilder;
    }

    public ResponseEntity<ApiResponse<?>> findEmployee(final Long id) {
        final Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            return responseBuilder.buildResponse(HttpStatus.NOT_FOUND.value(), "Not found");
        }
        return responseBuilder.buildResponse(HttpStatus.OK.value(), "", employee);
    }

    public ResponseEntity<ApiResponse<?>> getEmployees() {
        Iterable<Employee> list = employeeRepository.findAll();
        return responseBuilder.buildResponse(HttpStatus.OK.value(), "", list);
    }

    public ResponseEntity<ApiResponse<?>> deleteEmployee(final Long id) {
        try {
            employeeRepository.deleteById(id);
            return responseBuilder.buildResponse(HttpStatus.OK.value(), "");
        } catch (IllegalArgumentException e) {
            return responseBuilder.buildResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    public ResponseEntity<ApiResponse<?>> saveEmployee(Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        return responseBuilder.buildResponse(HttpStatus.OK.value(), "", savedEmployee);
    }
}
