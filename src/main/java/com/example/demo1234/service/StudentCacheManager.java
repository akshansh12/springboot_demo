package com.example.demo1234.service;


import com.example.demo1234.model.Student;

public interface StudentCacheManager {
    void cacheStudnetDetails(Student students);

    boolean checkEmpty();

}
