package com.infoshareacademy.searchengine.repository;

import com.infoshareacademy.searchengine.domain.Gender;
import com.infoshareacademy.searchengine.domain.Phone;
import com.infoshareacademy.searchengine.domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UsersRepository {
    @PersistenceContext(unitName = "pUnit")
    private EntityManager entityManager;

    public boolean addUser(User user){
        entityManager.persist(user);
     return true;
    }

//    public User getUserById(int id){
//       // return entityManager.createNamedQuery("getUserById").setParameter("id",id).getFirstResult();
//
//    }




}
