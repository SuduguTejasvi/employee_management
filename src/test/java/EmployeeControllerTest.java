import com.example.controller.EmployeeController;
import com.example.dto.EmployeeDTO;
import com.example.dto.ProjectDTO;
import com.example.entity.Employee;
import com.example.service.EmployeeService;
import com.example.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.*;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;

    @Mock
    private ProjectService projectService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;



    @Test
    void testViewEmployeePage() throws  Exception{
        Set<Integer> projectIds1= new HashSet<>();
        projectIds1.add(1);
        projectIds1.add(2);
        Set<Integer> projectIds2= new HashSet<>();
        projectIds2.add(1);
        projectIds2.add(2);
        Set<Integer> projectIds3= new HashSet<>();
        projectIds3.add(1);
        projectIds3.add(2);
        EmployeeDTO employeeDTO1=new EmployeeDTO(12L,"radhe","krishna","krishna@gmail.com",new Date(),projectIds1);
        EmployeeDTO employeeDTO2=new EmployeeDTO(12L,"radhe","krishna","krishna@gmail.com",new Date(),projectIds2);
        EmployeeDTO employeeDTO3=new EmployeeDTO(12L,"radhe","krishna","krishna@gmail.com",new Date(),projectIds3);
        List<EmployeeDTO> employees=new ArrayList<>();
        employees.add(employeeDTO1);
        employees.add(employeeDTO2);
        employees.add(employeeDTO3);
        when(employeeService.findAll()).thenReturn(employees);
        String viewName = employeeController.viewEmployeePage(model);

        assertEquals("employee/home", viewName);

    }
    @Test
    void testaddPage() throws Exception{
        Set<Integer> projectIds1= new HashSet<>();
        projectIds1.add(1);
        projectIds1.add(2);
        Set<Integer> projectIds2= new HashSet<>();
        projectIds2.add(1);
        projectIds2.add(2);
        Set<Integer> projectIds3= new HashSet<>();
        projectIds3.add(1);
        projectIds3.add(2);
        EmployeeDTO employeeDTO1=new EmployeeDTO(12L,"radhe","krishna","krishna@gmail.com",new Date(),projectIds1);
        EmployeeDTO employeeDTO2=new EmployeeDTO(12L,"radhe","krishna","krishna@gmail.com",new Date(),projectIds2);
        EmployeeDTO employeeDTO3=new EmployeeDTO(12L,"radhe","krishna","krishna@gmail.com",new Date(),projectIds3);
        List<EmployeeDTO> employees=new ArrayList<>();
        employees.add(employeeDTO1);
        employees.add(employeeDTO2);
        employees.add(employeeDTO3);
        when(employeeService.findAll()).thenReturn(employees);
        String result=employeeController.addPage(model);
        assertEquals(result,"employee/add-employee");

    }
    @Test
    void testDeleteEmployeeById() throws Exception {
        Long employeeId = 1L;
        Set<Integer> projectIds= new HashSet<>();
        projectIds.add(1);
        EmployeeDTO employee = new EmployeeDTO(employeeId,"radhe","krishna","krishna@gmail.com",new Date(),projectIds);

        when(employeeService.deleteById(employeeId)).thenReturn("deleted successfully");

        String response = employeeController.deletePage(employeeId);

        assertEquals(response,"redirect:/employees/list");

    }

    @Test
    void testSavePage_success() throws Exception{
        Set<Integer> projectIds= new HashSet<>();
        projectIds.add(1);
        projectIds.add(2);
        EmployeeDTO employeeDTO = new EmployeeDTO(1L, "John", "Doe", "john.doe@example.com", new Date(), projectIds);
        when(bindingResult.hasErrors()).thenReturn(false);
        String viewName = employeeController.savePage(employeeDTO, bindingResult,new ArrayList<>(projectIds), model);
        assertEquals(viewName,"redirect:/employees/list");

    }

    @Test
    void testSavePage_errors() throws Exception{
        Set<Integer> projectIds= new HashSet<>();
        projectIds.add(1);
        projectIds.add(2);
        EmployeeDTO employeeDTO = new EmployeeDTO(1L, "John", "Doe", "john.doe@example.com", new Date(), projectIds);
        when(bindingResult.hasErrors()).thenReturn(true);
        String viewName = employeeController.savePage(employeeDTO, bindingResult,new ArrayList<>(projectIds), model);
        assertEquals(viewName,"employee/add-employee");

    }
    @Test
    void testUpdatePage() {
        Long employeeId = 11L;
        Set<Integer> projectIds= new HashSet<>();
        projectIds.add(1);
        EmployeeDTO employeeDTO = new EmployeeDTO(1L, "John", "Doe", "john.doe@example.com", new Date(), projectIds);
        ProjectDTO projectDTO1= new ProjectDTO(1, "Project A",new Date(),new Date());
        ProjectDTO projectDTO2= new ProjectDTO(2, "Project B",new Date(),new Date());
        List<ProjectDTO> projectDTOS=new ArrayList<>();
        projectDTOS.add(projectDTO1);
        projectDTOS.add(projectDTO2);
        when(employeeService.findById(employeeId)).thenReturn(employeeDTO);
        when(projectService.findAll()).thenReturn(projectDTOS);

        String viewName = employeeController.updatePage(employeeId, model);
        assertEquals(viewName,"employee/add-employee");


    }


}
