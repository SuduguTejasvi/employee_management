package repository;

import com.example.repository.EmployeeDAOImpl;
import com.example.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;

import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeRepositoryTest {
    @InjectMocks
    public EmployeeDAOImpl employeeDAO;

    @Mock
    public SessionFactory sessionFactory;

    @Mock
    public NativeQuery<String> nativeQuery;

    @Mock
    public Session session;

    public Employee employee;

    @BeforeEach
    public void setUp() {
        employee = new Employee("John","Doe", "john.doe@example.com",new Date());
        when(sessionFactory.getCurrentSession()).thenReturn(session);
    }

    @Test
    public void testSave() {
        employeeDAO.save(employee);
        verify(session, times(1)).merge(employee);
    }
    @Test
    public void testFindAll() {
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);

        when(session.createQuery("from Employee", Employee.class)).thenReturn(mock(org.hibernate.query.Query.class));
        when(session.createQuery("from Employee", Employee.class).list()).thenReturn(employees);

        List<Employee> result = employeeDAO.findAll();

        assertEquals(1, result.size());
    }
    @Test
    public void testFindById() {
        when(session.get(Employee.class, 1L)).thenReturn(employee);

        Employee result = employeeDAO.findById(1L);

        assertEquals(result,employee);

    }
    @Test
    public void testDelete() {
        when(session.get(Employee.class, 1L)).thenReturn(employee);

        employeeDAO.delete(1L);
        ;
        verify(session, times(1)).delete(employee);
    }
    @Test
    public void testGetEmployeeProjects() {
        when(session.createNativeQuery("CALL get_projects(:employeeId)")).thenReturn(nativeQuery);
        when(nativeQuery.setParameter("employeeId", 1L)).thenReturn(nativeQuery);
        when(nativeQuery.getSingleResult()).thenReturn("Project A, Project B");

        String result = employeeDAO.getEmployeeProjects(1L);

        assertEquals("Project A, Project B", result);
    }

}
