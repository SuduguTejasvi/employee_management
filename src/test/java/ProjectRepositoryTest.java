import com.example.dao.ProjectDAOImpl;
import com.example.entity.Projects;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProjectRepositoryTest {
    @InjectMocks
    private ProjectDAOImpl projectDAO;

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    private Projects project;

    @BeforeEach
    void setUp(){
        project=new Projects(1,"Project A",new Date(),new Date());
        when(sessionFactory.getCurrentSession()).thenReturn(session);
    }

    @Test
    void testFindAll() {
        List<Projects> projectsList = new ArrayList<>();
        projectsList.add(project);

        when(session.createQuery("from Projects")).thenReturn(mock(org.hibernate.query.Query.class));
        when(session.createQuery("from Projects").list()).thenReturn(projectsList);

        List<Projects> result = projectDAO.findALl();

        assertEquals(1, result.size());
    }
    @Test
    void testFindById() {
        when(session.get(Projects.class, 1)).thenReturn(project);

        Projects result = projectDAO.findAllById(1);

        assertEquals(result,project);
    }
    @Test
    void testSave() {

        projectDAO.save(project);

        verify(session, times(1)).saveOrUpdate(project);
    }
    @Test
    void testDelete() {
        when(session.get(Projects.class, 1)).thenReturn(project);
        doNothing().when(session).delete(project);

        projectDAO.delete(1);

        verify(session, times(1)).get(Projects.class, 1);
        verify(session, times(1)).delete(project);
    }

}
