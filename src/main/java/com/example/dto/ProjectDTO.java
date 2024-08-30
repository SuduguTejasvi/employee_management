package com.example.dto;

import java.util.Date;
import java.util.Set;

public class ProjectDTO {

    private int projectId;
    private String projectName;
    private Date startDate;
    private Date endDate;
    private Set<Integer> employeeIds;


    public ProjectDTO() {
    }


    public ProjectDTO(int projectId, String projectName, Date startDate, Date endDate) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Set<Integer> getEmployeeIds() {
        return employeeIds;
    }

    public void setEmployeeIds(Set<Integer> employeeIds) {
        this.employeeIds = employeeIds;
    }


    @Override
    public String toString() {
        return "ProjectsDTO{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", employeeIds=" + employeeIds +
                '}';
    }
}