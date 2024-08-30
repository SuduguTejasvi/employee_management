package com.example.service;

import com.example.dao.ProjectDAO;
import com.example.dto.ProjectDTO;
import com.example.entity.Projects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    ProjectDAO projectDAO;

    @Override
    @Transactional
    public List<ProjectDTO> findAll() {
        List<Projects> projects=projectDAO.findALl();
        return projects.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProjectDTO findById(int id) {
        Projects project=projectDAO.findAllById(id);
        return convertToDTO(project);
    }

    @Override
    @Transactional
    public String save(ProjectDTO projectDTO) {
        projectDAO.save(convertToEntity(projectDTO));
        return "Saved successfully";
    }

    @Override
    @Transactional
    public String deleteById(int id) {
        projectDAO.delete(id);
        return "Deleted successfully";
    }

    public Projects convertToEntity(ProjectDTO projectDTO){
        return new Projects(
                projectDTO.getProjectId(),
                projectDTO.getProjectName(),
                projectDTO.getStartDate(),
                projectDTO.getEndDate()
        );
    }
    public ProjectDTO convertToDTO(Projects projects){
        return new ProjectDTO(
                projects.getProjectId(),
                projects.getProjectName(),
                projects.getStartDate(),
                projects.getEndDate()
        );
    }
}
