package com.example.demo1234.service.Impl;

import com.example.demo1234.model.Student;
import com.example.demo1234.repository.StudentRepository;
import com.example.demo1234.service.StudentCacheManager;
import com.example.demo1234.service.StudentCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
class StudentServiceImpl implements StudentCacheService {
    private StudentCacheManager studentCacheManager;

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    public StudentServiceImpl(StudentCacheManager redisCacheManager)    {
        this.studentCacheManager = studentCacheManager;
    }


    @Override
    public void cacheStudentdetails() {
        if(studentCacheManager.checkEmpty()) {// If cache is empty the put the data
            List<Student> students= studentRepository.findAll();
            students.forEach(stud->studentCacheManager.cacheStudnetDetails(stud));
        }
    }
}