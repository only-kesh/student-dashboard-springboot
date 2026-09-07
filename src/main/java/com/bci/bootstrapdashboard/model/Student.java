package com.bci.bootstrapdashboard.model;

public class Student {

    private final Long id;
    private final String name;
    private final String email;
    private final String programme;
    private final int progress;
    private final String status;

    public Student(Long id, String name, String email,
                   String programme, int progress, String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.programme = programme;
        this.progress = progress;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getProgramme() {
        return programme;
    }

    public int getProgress() {
        return progress;
    }

    public String getStatus() {
        return status;
    }
}
