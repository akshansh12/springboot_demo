package com.example.demo1234.controller;

import com.example.demo1234.model.Games;
import com.example.demo1234.model.Student;
import com.example.demo1234.repository.GamesRepository;
import com.example.demo1234.repository.StudentRepository;
import com.example.demo1234.service.StudentCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(value="/games")
public class GameController {
    @Autowired
    GamesRepository gamesRepository;
    @Autowired
    StudentRepository usersRepository;

    @Autowired
    StudentCacheService studentCacheService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET,produces = "application/json")
    public List<Games> getAll(){

        return gamesRepository.findAll();
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST,produces = "application/json")
    public List<Games> addGame(@RequestBody Games g){
        gamesRepository.save(g);
        return gamesRepository.findAll();
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET,value="/search",produces = "application/json")
    public Games sdfsdsvs(@RequestParam(name = "str") String str1){
        List<Games> ar=gamesRepository.findAll();
        for(int i=0;i<ar.size();i++){
            if(ar.get(i).getName().equals(str1))
                return ar.get(i);
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET,value="/{id}/student",produces = "application/json")
    public Set<Student> getStudentfromGames(@PathVariable(value="id") int id){
        List<Games> ar=gamesRepository.findAll();
        for(int i=0;i<ar.size();i++)
        {
            if(ar.get(i).getGame_no()==id)
                return ar.get(i).getStudents();
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value="/{id}/student",method = RequestMethod.POST,produces = "application/json")
    public Games addStudentOfGames(@PathVariable(value = "id") int str1, @RequestBody Student g1){
        List<Games> ar=gamesRepository.findAll();
        Games g2=gamesRepository.getOne(str1);
        Student s2=usersRepository.getOne(g1.getRoll_no());
        s2.addGame(g2);
        g2.addStudent(s2);
        gamesRepository.save(g2);usersRepository.save(s2);
        return g2;
//        for(int i=0;i<ar.size();i++){
//            if(ar.get(i).getGame_no()==str1){
//                ar.get(i).addStudent(g1);
//                g1.addGame(ar.get(i));
//                gamesRepository.save(ar.get(i));
//                usersRepository.save(g1);
//                return ar.get(i);
//            }
//        }
//        return null;
    }
}
