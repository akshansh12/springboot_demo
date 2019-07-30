package com.example.demo1234.service.Impl;

import com.example.demo1234.RedisUtil;
import com.example.demo1234.model.Student;
import com.example.demo1234.service.StudentCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Configuration
public class StudentCacheManagerImpl implements StudentCacheManager {
    public static final String TABLE_STUDENT = "TABLE_STUDENT";
    public static final String STUDENT = "STUDENT_";
    private RedisUtil<Student> redisUtilStudent;
    @Autowired
    public StudentCacheManagerImpl(RedisUtil<Student> redisUtilStudent) {
        this.redisUtilStudent = redisUtilStudent;
    }
    @Override
    public void cacheStudnetDetails(Student students){
        redisUtilStudent.putMap(TABLE_STUDENT,"STUDENT_"+students.getRoll_no(),students);
        redisUtilStudent.setExpire(TABLE_STUDENT,1,TimeUnit.DAYS);
    }

    @Override
    public boolean checkEmpty(){
        return redisUtilStudent.empty();
    }
}
