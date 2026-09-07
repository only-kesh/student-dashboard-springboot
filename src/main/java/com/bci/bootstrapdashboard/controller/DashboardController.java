package com.bci.bootstrapdashboard.controller;

import com.bci.bootstrapdashboard.model.Student;
import com.bci.bootstrapdashboard.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class DashboardController {

    private final StudentService studentService;

    public DashboardController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping({"/", "/dashboard"})
    public String dashboard(
            @RequestParam(name = "keyword", required = false, defaultValue = "")
            String keyword,
            Model model) {

        model.addAttribute("pageTitle", "Student Dashboard");
        model.addAttribute("courseName", "Enterprise Application Development");
        model.addAttribute("keyword", keyword);
        model.addAttribute("students", studentService.findAll(keyword));
        model.addAttribute("totalStudents", studentService.getTotalStudents());
        model.addAttribute("activeStudents", studentService.countActiveStudents());
        model.addAttribute("averageProgress", studentService.calculateAverageProgress());

        return "index";
    }

    @GetMapping("/students/{id}")
    public String studentDetails(@PathVariable Long id, Model model) {
        Student student = studentService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Student not found"));

        model.addAttribute("pageTitle", "Student Details");
        model.addAttribute("student", student);
        return "student-details";
    }
}
