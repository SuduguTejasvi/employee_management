package com.example.service;

import com.example.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> findAll();
    EmployeeDTO findById(Long id);
    String save(EmployeeDTO employeeDTO);
    String deleteById(Long id);
    String getEmployeeProjects(Long employeeId);
}
