package com.example.controller;

import com.example.dto.ProjectDTO;
import com.example.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addCustomFormatter(new DateFormatter("yyyy-MM-dd"));
    }

    @Autowired
    private ProjectService projectService;

    @RequestMapping("/list")
    public String viewProjectPage(Model model) {
        List<ProjectDTO> projects = projectService.findAll();
        model.addAttribute("projects", projects);
        return "project/home";
    }

    @RequestMapping("/add")
    public String addPage(Model model) {
        ProjectDTO projectDTO = new ProjectDTO();
        model.addAttribute("projects", projectDTO);
        return "project/add-project";
    }

    @RequestMapping("/save")
    public String savePage(@ModelAttribute("projects") ProjectDTO projectDTO) {
        projectService.save(projectDTO);
        return "redirect:/projects/list";
    }

    @RequestMapping("/delete")
    public String deletePage(@RequestParam("projectId") int projectId) {
        projectService.deleteById(projectId);
        return "redirect:/projects/list";
    }

    @RequestMapping("/update")
    public String updatePage(@RequestParam("projectId") int projectId, Model model) {
        ProjectDTO projectDTO = projectService.findById(projectId);
        model.addAttribute("projects", projectDTO);
        return "project/add-project";
    }
}
