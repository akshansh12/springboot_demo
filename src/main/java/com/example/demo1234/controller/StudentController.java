package com.example.demo1234.controller;

import com.example.demo1234.RedisUtil;
import com.example.demo1234.model.Games;
import com.example.demo1234.model.Student;
import com.example.demo1234.repository.GamesRepository;
import com.example.demo1234.repository.StudentRepository;
import com.example.demo1234.service.StudentCacheService;
import com.example.demo1234.service.StudentRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    StudentRepository usersRepository;

    @Autowired
    GamesRepository gamesRepository;

    @Autowired
    StudentCacheService studentCacheService;

    @Autowired
    StudentRepositoryService studentRepositoryService;

    @Autowired
    RedisUtil<Student> studentRedisUtil;


    @GetMapping(value = "",produces = "application/json")
    public List<Student> getAll() {
        List<Student> ar=usersRepository.findAll();
        for(int i=0;i<ar.size();i++){
            studentRedisUtil.putMap("TABLE_STUDENT","STUDENT_"+ar.get(i).getRoll_no(),ar.get(i));
            studentRedisUtil.setExpire("TABLE_STUDENT",10, TimeUnit.MINUTES);
        }
        //studentCacheService.cacheStudentdetails();
        return ar;
    }



    @PostMapping(value = "",produces = "application/json")
    public List<Student> addStudent(@RequestBody Student g){
        usersRepository.save(g);
        return usersRepository.findAll();
    }

    @ResponseBody
    @RequestMapping(value="/search")
    public Student search(@RequestParam(name = "str1") String str1){
        List<Student> ar=usersRepository.findAll();
        for(int i=0;i<ar.size();i++){
            if(ar.get(i).getName().equals(str1))
                return ar.get(i);
        }
        return null;

    }

    @ResponseBody
    @RequestMapping(value="/rollno")
    public Student searchRoll_no(@RequestParam(name = "str1") Integer str1){

        //return studentRepositoryService.find(str1);
        List<Student> ar=usersRepository.findAll();
        return usersRepository.getOne(str1);
//        for(int i=0;i<ar.size();i++){
//            if(ar.get(i).getRoll_no()==str1)
//                return ar.get(i);
//        }
//        return null;

    }

    @ResponseBody
    @RequestMapping(value="/{id}/games",method = RequestMethod.POST)
    public Student addGamesOfStudent(@PathVariable(value = "id") Integer str1, @RequestBody Games g1){
        List<Student> ar=usersRepository.findAll();
        Games g2=gamesRepository.getOne(g1.getGame_no());
        Student s11=usersRepository.getOne(str1);
        s11.addGame(g2);
        g2.addStudent(s11);
        usersRepository.save(s11);gamesRepository.save(g2);
        return s11;
//        for(int i=0;i<ar.size();i++){
//            if(ar.get(i).getRoll_no()==str1){
//                ar.get(i).addGame(g1);
//                g1.addStudent(ar.get(i));
//                usersRepository.save(ar.get(i));
//                gamesRepository.save(g1);
//                 return ar.get(i);
//            }
//        }
//    return null;
    }

    @ResponseBody
    @RequestMapping(value = "/{id}/games",method =RequestMethod.GET)
    public Set<Games> getGamesOfStudent(@PathVariable(value = "id") Integer id){
        return usersRepository.getOne(id).getGames();
    }

}
