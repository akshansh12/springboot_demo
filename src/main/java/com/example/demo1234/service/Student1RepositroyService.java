package com.example.demo1234.service;

import com.example.demo1234.model.Student;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface Student1RepositroyService {

    public Student create(Student s);

    public Student find(int i);

    public Student update(Student s);

    public void delete(int i);

    public List<Student> findAll();

    public Student login(Student s1);

}
