package service;

import com.example.repository.EmployeeDAO;
import com.example.dto.EmployeeDTO;
import com.example.dto.ProjectDTO;
import com.example.entity.Employee;
import com.example.entity.Projects;
import com.example.service.EmployeeServiceImpl;
import com.example.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @InjectMocks
    public EmployeeServiceImpl employeeService;

    @Mock
    public EmployeeDAO employeeDAO;

    @Mock
    public ProjectService projectService;

    public Employee employee;
    public EmployeeDTO employeeDTO;
    public ProjectDTO projectDTO;
    public Projects project;

    @BeforeEach
    public void setUp() {
        project = new Projects(1, "Project A", new Date(), new Date());
        employee = new Employee("John", "Doe", "john.doe@example.com", new Date());
        employee.setEmployeeId(1L);

        projectDTO = new ProjectDTO(1, "Project A", new Date(), new Date());
        employeeDTO = new EmployeeDTO(1L, "John", "Doe", "john.doe@example.com", new Date(), new HashSet<>(Collections.singletonList(1)));
    }

    @Test
    public void testFindAll() {
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);

        when(employeeDAO.findAll()).thenReturn(employees);

        when(employeeDAO.getEmployeeProjects(anyLong())).thenReturn("Project A");

        List<EmployeeDTO> result = employeeService.findAll();

        assertEquals(1, result.size());
        assertEquals(employeeDTO.getEmployeeId(), result.get(0).getEmployeeId());
        assertEquals("Project A", result.get(0).getProjectResult());
    }

    @Test
    public void testFindById() {
        when(employeeDAO.findById(anyLong())).thenReturn(employee);
        when(employeeDAO.getEmployeeProjects(anyLong())).thenReturn("Project A");

        EmployeeDTO result = employeeService.findById(1L);

        assertEquals(employeeDTO.getEmployeeId(), result.getEmployeeId());
        assertEquals("Project A", result.getProjectResult());
    }

    @Test
    public void testSave() {
        when(projectService.findById(anyInt())).thenReturn(projectDTO);
        doNothing().when(employeeDAO).save(any(Employee.class));

        String result = employeeService.save(employeeDTO);

        assertEquals("Saved successfully", result);
    }

    @Test
    public void testDeleteById() {
        doNothing().when(employeeDAO).delete(anyLong());

        String result = employeeService.deleteById(1L);

        assertEquals("deleted successfully", result);
    }

    @Test
    public void testGetEmployeeProjects() {
        when(employeeDAO.getEmployeeProjects(anyLong())).thenReturn("Project A");

        String result = employeeService.getEmployeeProjects(1L);

        assertEquals("Project A", result);
    }

    @Test
    public void testConvertToDTOWithProjects() {
        when(employeeDAO.getEmployeeProjects(anyLong())).thenReturn("Project A");

        EmployeeDTO result = employeeService.convertToDTOWithProjects(employee);

        assertEquals(employeeDTO.getEmployeeId(), result.getEmployeeId());
        assertEquals("Project A", result.getProjectResult());
    }

    @Test
    public void testConvertToEntity() {
        when(projectService.findById(anyInt())).thenReturn(projectDTO);

        Employee result = employeeService.convertToEntity(employeeDTO);

        assertEquals(employeeDTO.getEmployeeId(), result.getEmployeeId());
        assertEquals(1, result.getProjects().size());
    }
}
