import com.example.controller.ProjectController;
import com.example.dto.ProjectDTO;
import com.example.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ProjectControllerTest {

    @Mock
    private ProjectService projectService;

    @InjectMocks
    private ProjectController projectController;

    @Mock
    private Model model;

    @Test
    public void testViewProjectPage() throws Exception{
        ProjectDTO projectDTO1= new ProjectDTO(1, "Project A",new Date(),new Date());
        ProjectDTO projectDTO2= new ProjectDTO(2, "Project B",new Date(),new Date());
        List<ProjectDTO> projectDTOS=new ArrayList<>();
        projectDTOS.add(projectDTO1);
        projectDTOS.add(projectDTO2);
        when(projectService.findAll()).thenReturn(projectDTOS);
        String viewName=projectController.viewProjectPage(model);
        assertEquals(viewName,"project/home");

    }
    @Test
    public void testAddPage() throws Exception{
        String viewName = projectController.addPage(model);
        assertEquals("project/add-project", viewName);
    }
    @Test
    public void testSavePage() throws Exception{
        ProjectDTO projectDTO1= new ProjectDTO(1, "Project A",new Date(),new Date());
        when(projectService.save(projectDTO1)).thenReturn("Saved successfully");
        String viewPage=projectController.savePage(projectDTO1);
        assertEquals(viewPage,"redirect:/projects/list");
    }
    @Test
    public void testDeletePage() throws Exception{
        int projectId=1;
        ProjectDTO projectDTO1= new ProjectDTO(1, "Project A",new Date(),new Date());
        when(projectService.deleteById(projectId)).thenReturn("Deleted successfully");
        String viewName= projectController.deletePage(projectId);
        assertEquals("redirect:/projects/list", viewName);
    }
    @Test
    public void testUpdatePage() throws Exception{
        int projectId=1;
        ProjectDTO projectDTO1= new ProjectDTO(1, "Project A",new Date(),new Date());
        when(projectService.findById(projectId)).thenReturn(projectDTO1);
        String viewName= projectController.updatePage(projectId,model);
        assertEquals("project/add-project", viewName);
    }


}
