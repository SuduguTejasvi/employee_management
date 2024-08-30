package com.example.repository;

import com.example.entity.Projects;

import java.util.List;

public interface ProjectDAO {

    List<Projects> findALl();

    Projects findAllById(int id);

    void save(Projects projects);

    void delete(int id);
}
