package com.example.demo1234.service.Impl;

import com.example.demo1234.model.Student;
import com.example.demo1234.repository.Student1Repository;
import com.example.demo1234.service.Student1RepositroyService;
import com.example.demo1234.service.StudentRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.netty.http.Cookies;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class IStudent1RepositoryService implements Student1RepositroyService {
    @Autowired
    Student1Repository student1Repository;



    @Override
    public Student create(Student s1) {
        if(student1Repository.find(s1.getRoll_no())!=null)
            return null;
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        s1.setUuid(randomUUIDString);
        return student1Repository.create(s1);
    }

    @Override
    public Student find(int i) {
        return student1Repository.find(i);
    }

    @Override
    public Student update(Student s){
        if(find(s.getRoll_no())==null)
            return null;
        student1Repository.update(s);
        return s;

    }

    @Override
    public void delete(int i){
        student1Repository.delete(i);
    }

    @Override
    public List<Student> findAll(){
        return student1Repository.findAll();
    }


    @Override
    public Student login(Student s1){
        if(find(s1.getRoll_no())==null){
            return null;
        }
        Student s=find(s1.getRoll_no());
        if(s1.getPassword().equals(s.getPassword()))
            return s;
        return null;
    }


}
