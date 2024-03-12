package com.mauyz.apiwithh2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mauyz.apiwithh2.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long>{

}
