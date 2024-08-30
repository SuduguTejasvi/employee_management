package com.example.controller;

import com.example.dto.EmployeeDTO;
import com.example.dto.ProjectDTO;
import com.example.service.EmployeeService;
import com.example.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/employees")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProjectService projectService;


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addCustomFormatter(new DateFormatter("yyyy-MM-dd"));
    }

    @RequestMapping("/list")
    public String viewEmployeePage(Model model) {
        List<EmployeeDTO> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        log.info("successfully displayed employees list"+employees);
        return "employee/home";
    }

    @RequestMapping("/add")
    public String addPage(Model model) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        List<ProjectDTO> projects = projectService.findAll();
        model.addAttribute("employee", employeeDTO);
        model.addAttribute("projects", projects);
        log.info("successfully added employee");
        return "employee/add-employee";
    }

    @PostMapping("/save")
    public String savePage(@Valid @ModelAttribute("employee") EmployeeDTO employeeDTO,
                           BindingResult result,
                           @RequestParam(value = "projectIds", required = false) List<Integer> projectIds,
                           Model model) {
        if (result.hasErrors()) {
            List<ProjectDTO> projects = projectService.findAll();
            model.addAttribute("projects", projects);
            log.error("Failed to add a employee");
            return "employee/add-employee";
        }
        if (projectIds != null && !projectIds.isEmpty()) {
            employeeDTO.setProjectIds(new HashSet<>(projectIds));
        } else {
            employeeDTO.setProjectIds(new HashSet<>());
        }
        log.info("successfully added employee");
        employeeService.save(employeeDTO);
        return "redirect:/employees/list";
    }


    @RequestMapping("/delete")
    public String deletePage(@RequestParam("employeeId") Long employeeId) {
        employeeService.deleteById(employeeId);
        log.info("successfully deleted employee");
        return "redirect:/employees/list";
    }

    @RequestMapping("/update")
    public String updatePage(@RequestParam("employeeId") Long employeeId, Model model) {
        EmployeeDTO employeeDTO = employeeService.findById(employeeId);
        List<ProjectDTO> projects = projectService.findAll();

        Set<Integer> employeeProjectIds = employeeDTO.getProjectIds();

        model.addAttribute("employee", employeeDTO);
        model.addAttribute("projects", projects);
        model.addAttribute("employeeProjectIds", employeeProjectIds);
        log.info("successfully updated employee");

        return "employee/add-employee";
    }
}
