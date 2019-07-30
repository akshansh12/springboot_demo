package com.example.demo1234.repository;

import com.example.demo1234.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public interface StudentRepository extends JpaRepository<Student,Integer> {


}
