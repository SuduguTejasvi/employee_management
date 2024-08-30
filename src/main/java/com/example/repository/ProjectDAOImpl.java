package com.example.repository;

import com.example.entity.Projects;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ProjectDAOImpl implements ProjectDAO{

    @Autowired
    SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Projects> findALl() {
        Session session=sessionFactory.getCurrentSession();
        return session.createQuery("from Projects").list();
    }

    @Override
    public Projects findAllById(int id) {
        Session session=sessionFactory.getCurrentSession();
        Projects p=session.get(Projects.class,id);
        return p;
    }

    @Override
    public void save(Projects projects) {
        Session session=sessionFactory.getCurrentSession();
        session.saveOrUpdate(projects);
    }

    @Override
    public void delete(int id) {
        Session session=sessionFactory.getCurrentSession();
        Projects p=session.get(Projects.class,id);
        session.delete(p);
    }
}
