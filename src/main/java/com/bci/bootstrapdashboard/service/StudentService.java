package com.bci.bootstrapdashboard.service;

import com.bci.bootstrapdashboard.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class StudentService {

    private final List<Student> students = List.of(
            new Student(1L, "Nimal Perera", "nimal@bci.lk",
                    "Software Engineering", 85, "Active"),
            new Student(2L, "Amaya Fernando", "amaya@bci.lk",
                    "Computer Science", 72, "Active"),
            new Student(3L, "Kasun Silva", "kasun@bci.lk",
                    "Information Technology", 64, "Active"),
            new Student(4L, "Dinithi Jayasinghe", "dinithi@bci.lk",
                    "Artificial Intelligence", 91, "Active"),
            new Student(5L, "Ravindu Senanayake", "ravindu@bci.lk",
                    "Cyber Security", 48, "Inactive")
    );

    public List<Student> findAll(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return students;
        }

        String normalizedKeyword = keyword.trim().toLowerCase(Locale.ROOT);

        return students.stream()
                .filter(student ->
                        student.getName().toLowerCase(Locale.ROOT)
                                .contains(normalizedKeyword)
                                || student.getProgramme().toLowerCase(Locale.ROOT)
                                .contains(normalizedKeyword)
                                || student.getStatus().toLowerCase(Locale.ROOT)
                                .contains(normalizedKeyword))
                .toList();
    }

    public Optional<Student> findById(Long id) {
        return students.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst();
    }

    public long countActiveStudents() {
        return students.stream()
                .filter(student -> "Active".equalsIgnoreCase(student.getStatus()))
                .count();
    }

    public int calculateAverageProgress() {
        return (int) Math.round(students.stream()
                .mapToInt(Student::getProgress)
                .average()
                .orElse(0));
    }

    public int getTotalStudents() {
        return students.size();
    }
}
