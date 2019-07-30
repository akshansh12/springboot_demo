package com.example.demo1234.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.annotation.Generated;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "games")
@EntityListeners(AuditingEntityListener.class)
public class Games implements Serializable {




    @Id
    @Column(name="game_no")
    private Integer game_no;
    @Column(name = "name")
    private String name;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY,
//            cascade = {
//                    CascadeType.PERSIST,
//                    CascadeType.MERGE
//            },
            mappedBy = "games")
    private Set<Student> students = new HashSet<>();

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Games(){

    }
    public void addStudent(Student s1){
        this.students.add(s1);


    }

    public Integer getGame_no() {
        return game_no;
    }

    public String getName() {
        return name;
    }

//    public Integer getId() {
//        return id;
//    }

    public void setGame_no(Integer game_no) {
        this.game_no = game_no;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public void setId(Integer roll_no) {
//        this.id = roll_no;
//    }
}
