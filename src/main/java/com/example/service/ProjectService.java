package com.example.service;

import com.example.dto.ProjectDTO;


import java.util.List;

public interface ProjectService {
    List<ProjectDTO> findAll();
    ProjectDTO findById(int id);
    String save(ProjectDTO projectDTO);
    String deleteById(int id);
}
