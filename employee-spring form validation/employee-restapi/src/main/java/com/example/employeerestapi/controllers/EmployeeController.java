package com.example.employeerestapi.controllers;

import com.example.employeerestapi.models.Employee;
import com.example.employeerestapi.services.EmployeeServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeServices employeeServices;

    @Autowired
    public EmployeeController(EmployeeServices employeeServices) {
        this.employeeServices = employeeServices;
    }

    @GetMapping
    public String listEmployees(@RequestParam(value = "searchId", required = false) Integer searchId, Model model) {
        if (searchId != null) {
            Employee emp = employeeServices.getById(searchId);
            if (emp != null) {
                model.addAttribute("employees", List.of(emp));
            } else {
                model.addAttribute("employees", List.of());
                model.addAttribute("searchError", "Employee with ID " + searchId + " not found.");
            }
        } else {
            model.addAttribute("employees", employeeServices.getAll());
        }
        return "employee-list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            return "employee-form";
        }
        if (employee.getId() > 0) {
            employeeServices.update(employee.getId(), employee);
        } else {
            employeeServices.create(employee);
        }
        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Employee employee = employeeServices.getById(id);
        if (employee == null) {
            return "redirect:/employees";
        }
        model.addAttribute("employee", employee);
        return "employee-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") int id) {
        employeeServices.delete(id);
        return "redirect:/employees";
    }
}
