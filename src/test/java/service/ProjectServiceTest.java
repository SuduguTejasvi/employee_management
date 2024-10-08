package service;

import com.example.repository.ProjectDAO;
import com.example.dto.ProjectDTO;
import com.example.entity.Projects;
import com.example.service.ProjectServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {
    @InjectMocks
    public ProjectServiceImpl projectService;

    @Mock
    public ProjectDAO projectDAO;

    public Projects project;
    public ProjectDTO projectDTO;

    @BeforeEach
    public void setUp(){
        project = new Projects(1, "Project A", new Date(), new Date());
        projectDTO = new ProjectDTO(1, "Project A", new Date(), new Date());
    }

    @Test
    public void testFindAll() {
        List<Projects> projectsList = new ArrayList<>();
        projectsList.add(project);

        when(projectDAO.findALl()).thenReturn(projectsList);

        List<ProjectDTO> result = projectService.findAll();

        assertEquals(1, result.size());
        assertEquals(projectDTO.getProjectId(), result.get(0).getProjectId());
        assertEquals(projectDTO.getProjectName(), result.get(0).getProjectName());
    }

    @Test
    public void testFindById() {
        when(projectDAO.findAllById(anyInt())).thenReturn(project);

        ProjectDTO result = projectService.findById(1);

        assertEquals(projectDTO.getProjectId(), result.getProjectId());
        assertEquals(projectDTO.getProjectName(), result.getProjectName());
    }

    @Test
    public void testSave() {

        String result = projectService.save(projectDTO);

        assertEquals("Saved successfully", result);
    }

    @Test
    public void testDeleteById() {

        String result = projectService.deleteById(1);

        assertEquals("Deleted successfully", result);
    }
    @Test
    public void testConvertToEntity() {
        Projects result = projectService.convertToEntity(projectDTO);
        assertEquals(project.getProjectId(), result.getProjectId());
        assertEquals(project.getProjectName(), result.getProjectName());
    }
    @Test
    public void testConvertToDTO() {
        ProjectDTO result = projectService.convertToDTO(project);

        assertEquals(projectDTO.getProjectId(), result.getProjectId());
        assertEquals(projectDTO.getProjectName(), result.getProjectName());
    }
}
