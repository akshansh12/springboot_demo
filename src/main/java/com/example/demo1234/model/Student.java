package com.example.demo1234.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Student")
@EntityListeners(AuditingEntityListener.class)
public class Student implements Serializable {

    @Id
    @Column(name = "roll_no")
    private Integer roll_no;
    @Column
    private String uuid;
    @Column(name = "name")
    private String name;
    @Column(name = "branch")
    private String branch;
    @Column(name="password")
    private String password;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
          )
    @JsonBackReference
    @JoinTable(name = "Student_map",
            joinColumns = { @JoinColumn(name = "roll_no") },
            inverseJoinColumns = { @JoinColumn(name = "game_no") })
    private Set<Games> games = new HashSet<>();

    public Set<Games> getGames() {
        return games;
    }

    public String getPassword() {
        return password;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public void setGames(Set<Games> games) {
        this.games = games;
    }

    public Student() {

    }

    public void addGame(Games g1){
        this.games.add(g1);
    }

//    public Integer getId() {
//        return id;
//    }

    public Integer getRoll_no() {
        return roll_no;
    }

    public void setRoll_no(Integer id) {
        this.roll_no = id;
    }

    public String getName() {
        return name;
    }

//    public void setId(Integer id) {
//        this.id = id;
//    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String teamName) {
        this.branch = teamName;
    }

}

