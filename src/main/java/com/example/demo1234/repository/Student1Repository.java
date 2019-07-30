package com.example.demo1234.repository;

import com.example.demo1234.model.Student;

import java.util.List;

public interface Student1Repository {

    public List<Student> findAll();

    public Student create(Student s);

    public Student find(int s);

    public void update(Student s);

    public void delete(int i);

}
