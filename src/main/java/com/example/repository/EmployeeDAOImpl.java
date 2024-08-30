package com.example.repository;

import com.example.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Employee> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Employee", Employee.class).list();
    }

    @Override
    public Employee findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Employee.class, id);
    }

    @Override
    public void save(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(employee);
    }


    @Override
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Employee employee = session.get(Employee.class, id);
        if (employee != null) {
            session.delete(employee);
        }
    }

    public String getEmployeeProjects(Long employeeId) {
        Session session = sessionFactory.getCurrentSession();
        String projectNames = "";

        try {

            NativeQuery<String> query = session.createNativeQuery("CALL get_projects(:employeeId)");
            query.setParameter("employeeId", employeeId);

            projectNames = query.getSingleResult();
        } catch (NoResultException e) {

            projectNames = "No projects found";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return projectNames;
    }
}
