package com.example.teacherreminder.model;

import java.io.Serializable;

public class CourseListener implements Serializable {

    private Long id;
    private String name;
    private String login;
    private String email;
    private String info;

    public CourseListener(Long id, String name, String login, String email, String info) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.info = info;
    }

    public CourseListener() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public CourseListener(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
