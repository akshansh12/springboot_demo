package com.example.demo1234.service.Impl;

import com.example.demo1234.model.Student;
import com.example.demo1234.repository.StudentRepository;
import com.example.demo1234.service.StudentRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class IStudentRepositoryService implements StudentRepositoryService {
    @Autowired
    StudentRepository usersRepository;

    @Override
    public Student find(Integer i) {
        Student s1= usersRepository.getOne(i);
        return s1;
    }
}
