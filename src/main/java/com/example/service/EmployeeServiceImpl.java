package com.example.service;

import com.example.dao.EmployeeDAO;
import com.example.dto.EmployeeDTO;
import com.example.dto.ProjectDTO;
import com.example.entity.Employee;
import com.example.entity.Projects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private ProjectService projectService;

    @Override
    @Transactional
    public List<EmployeeDTO> findAll() {
        List<Employee> employees = employeeDAO.findAll();
        return employees.stream()
                .map(this::convertToDTOWithProjects)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public EmployeeDTO findById(Long id) {
        Employee employee = employeeDAO.findById(id);
        return convertToDTOWithProjects(employee);
    }

    @Override
    @Transactional
    public String save(EmployeeDTO employeeDTO) {
        Employee employee = convertToEntity(employeeDTO);
        employeeDAO.save(employee);
        return "Saved successfully";
    }

    @Override
    @Transactional
    public String deleteById(Long id) {
        employeeDAO.delete(id);
        return "deleted successfully";
    }

    @Override
    @Transactional
    public String getEmployeeProjects(Long employeeId) {
        return employeeDAO.getEmployeeProjects(employeeId);
    }

    public EmployeeDTO convertToDTOWithProjects(Employee employee) {
        if (employee == null) {
            return null;
        }

        EmployeeDTO dto = new EmployeeDTO(
                employee.getEmployeeId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getHireDate(),
                employee.getProjects().stream().map(Projects::getProjectId).collect(Collectors.toSet())
        );


        dto.setProjectResult(getEmployeeProjects(employee.getEmployeeId()));

        return dto;
    }



    public Employee convertToEntity(EmployeeDTO employeeDTO) {
        if (employeeDTO == null) {
            return null;
        }
        Employee employee = new Employee();
        employee.setEmployeeId(employeeDTO.getEmployeeId());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setHireDate(employeeDTO.getHireDate());

        Set<Projects> projects = employeeDTO.getProjectIds().stream()
                .map(projectService::findById)
                .map(this::convertToEntity)
                .collect(Collectors.toSet());

        employee.setProjects(projects);
        return employee;
    }

    public Projects convertToEntity(ProjectDTO projectDTO) {
        if (projectDTO == null) {
            return null;
        }
        return new Projects(
                projectDTO.getProjectId(),
                projectDTO.getProjectName(),
                projectDTO.getStartDate(),
                projectDTO.getEndDate()
        );
    }
}
