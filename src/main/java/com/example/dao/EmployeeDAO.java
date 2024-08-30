package com.example.dao;

import com.example.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();
    Employee findById(Long id);
    void save(Employee employee);
    void delete(Long id);
    String getEmployeeProjects(Long employeeId);
}
