package com.employees.webapp.controller;

import com.employees.webapp.model.Employee;
import com.employees.webapp.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping("/")
    public String employees(Model model) {
        System.out.println("EmployeeController *** employees(Model)  ");

        Iterable<Employee> listEmployee = service.getEmployees();

        model.addAttribute("employees", listEmployee);

        return "home";
    }}
