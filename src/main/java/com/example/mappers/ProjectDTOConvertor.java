package com.example.mappers;

import com.example.dto.ProjectDTO;
import com.example.entity.Projects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.modelmapper.ModelMapper;

@Controller
public class ProjectDTOConvertor {

    @Autowired
    private ModelMapper modelMapper;

    public Projects convertDTOToEntity(ProjectDTO projectDTO){

        return modelMapper.map(projectDTO,Projects.class);

    }
    public ProjectDTO convertEntityToDTO(Projects projects){

        return modelMapper.map(projects,ProjectDTO.class);

    }
}
