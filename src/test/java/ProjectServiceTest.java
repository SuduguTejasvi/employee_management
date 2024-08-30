import com.example.dao.ProjectDAO;
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
    private ProjectServiceImpl projectService;

    @Mock
    private ProjectDAO projectDAO;

    private Projects project;
    private ProjectDTO projectDTO;

    @BeforeEach
    void setUp(){
        project = new Projects(1, "Project A", new Date(), new Date());
        projectDTO = new ProjectDTO(1, "Project A", new Date(), new Date());
    }

    @Test
    void testFindAll() {
        List<Projects> projectsList = new ArrayList<>();
        projectsList.add(project);

        when(projectDAO.findALl()).thenReturn(projectsList);

        List<ProjectDTO> result = projectService.findAll();

        assertEquals(1, result.size());
        assertEquals(projectDTO.getProjectId(), result.get(0).getProjectId());
        assertEquals(projectDTO.getProjectName(), result.get(0).getProjectName());
    }

    @Test
    void testFindById() {
        when(projectDAO.findAllById(anyInt())).thenReturn(project);

        ProjectDTO result = projectService.findById(1);

        assertEquals(projectDTO.getProjectId(), result.getProjectId());
        assertEquals(projectDTO.getProjectName(), result.getProjectName());
    }

    @Test
    void testSave() {

        String result = projectService.save(projectDTO);

        assertEquals("Saved successfully", result);
    }

    @Test
    void testDeleteById() {

        String result = projectService.deleteById(1);

        assertEquals("Deleted successfully", result);
    }
    @Test
    void testConvertToEntity() {
        Projects result = projectService.convertToEntity(projectDTO);
        assertEquals(project.getProjectId(), result.getProjectId());
        assertEquals(project.getProjectName(), result.getProjectName());
    }
    @Test
    void testConvertToDTO() {
        ProjectDTO result = projectService.convertToDTO(project);

        assertEquals(projectDTO.getProjectId(), result.getProjectId());
        assertEquals(projectDTO.getProjectName(), result.getProjectName());
    }
}
