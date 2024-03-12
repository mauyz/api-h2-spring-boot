package com.mauyz.apiwithh2.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mauyz.apiwithh2.model.Employee;
import com.mauyz.apiwithh2.repository.EmployeeRepository;

import lombok.Data;
import lombok.NonNull;

@Data
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Optional<Employee> findEmployee(@NonNull final Long id) {
        return employeeRepository.findById(id);
    }

    public Iterable<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public void deleteEmployee(@NonNull final Long id) {
        employeeRepository.deleteById(id);
    }

    public Employee saveEmployee(@NonNull Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        return savedEmployee;
    }
}
