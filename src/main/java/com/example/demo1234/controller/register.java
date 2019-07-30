package com.example.demo1234.controller;


import com.example.demo1234.model.Student;
import com.example.demo1234.repository.Student1Repository;
import com.example.demo1234.repository.StudentRepository;
import com.example.demo1234.service.Student1RepositroyService;
import com.example.demo1234.service.StudentRepositoryService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
@RequestMapping("/register")
public class register {
    @Autowired
    StudentRepository usersRepository;
    @Autowired
    StudentRepositoryService srs;

    @Autowired
    Student1Repository student1Repository;

    @Autowired
    Student1RepositroyService student1RepositoryService;







    @ResponseBody
    @RequestMapping(value = "", method=RequestMethod.POST)
    public Student register(@RequestBody Student s1)  {

    return student1RepositoryService.create(s1);


    }

    @ResponseBody
    @RequestMapping(value="/login",method = RequestMethod.GET)
    public boolean login(@RequestBody Student s1, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){

        Student s12= student1RepositoryService.login(s1);
        if(s12==null)
            return false;
        httpServletResponse.addCookie(new Cookie("Student_"+s12.getRoll_no(),s12.getUuid()));
        return true;
    }

    @ResponseBody
    @RequestMapping(value ="/update", method = RequestMethod.POST)
    public Student update(@RequestBody Student s1){
        return student1RepositoryService.update(s1);
    }

    @ResponseBody
    @RequestMapping(value = "", method=RequestMethod.GET)
    public List<Student> findAll()  {

        return student1Repository.findAll();


    }

    @ResponseBody
    @RequestMapping(value="/delete" ,method = RequestMethod.POST)
    public void delete(@RequestParam(name = "roll") int i){
        student1RepositoryService.delete(i);

    }
}
