package com.example.demo1234.repository.Impl;

import com.example.demo1234.model.Student;
import com.example.demo1234.repository.Student1Repository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class IStudent1Repository implements Student1Repository {

    @Autowired
    private EntityManager entityManager;
    @Override
    public List<Student> findAll() {
        Session session=entityManager.unwrap(Session.class);
        Query<Student> query=session.createQuery("from Student", Student.class);
        List<Student> st=query.getResultList();
        return st;
    }

    @Override
    public Student create(Student s) {

        Session session =entityManager.unwrap(Session.class);
//        Query<Student> query=session.createQuery("insert into Student value(:roll_no,:branch,:name,:password)",Student.class);
//        query.setParameter("roll_no",s.getRoll_no());
//        query.setParameter("branch",s.getBranch());
//        query.setParameter("name",s.getName());
//        query.setParameter("password",s.getPassword());
            session.save(s);
        return s;
    }

    @Override
    public Student find(int s){
        Session session=entityManager.unwrap(Session.class);
//        Query<Student> query=session.createQuery("from Student where roll_no= : roll_no", Student.class);
//        query.setParameter("roll_no",s.getRoll_no());
//        Student st=query.getSingleResult();
        return session.get(Student.class,s);

    }

    @Override
    public void update(Student s) {
        Session session=entityManager.unwrap(Session.class);
        Student s1=find(s.getRoll_no());
        s1.setRoll_no(s.getRoll_no());
        s1.setPassword(s.getPassword());
        s1.setBranch(s.getBranch());
        s1.setName(s.getName());

         session.update(s1);
    }

    @Override
    public void delete(int i){
        Student s12=find(i);
        Session session=entityManager.unwrap(Session.class);
        session.delete(s12);

    }


}