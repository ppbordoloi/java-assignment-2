package com.zaloni.training.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zaloni.training.dao.UserRepository;

@Service
@Transactional
public class LoginService {

    @Autowired
    UserRepository repo;

    public boolean isValidCredential(String email, String password) {
        //int count = repo.search(email, password).size();
        System.out.println("e:"+email+", p:"+ password);
        int count = repo.findByEmailAndPassword(email, password).size();
        return count == 1;
    }
}
