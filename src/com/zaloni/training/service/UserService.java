package com.zaloni.training.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zaloni.training.dao.UserRepository;
import com.zaloni.training.entity.User;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository repo;
    
    public User findById(int id) {
        Optional<User> user = repo.findById(new Long(id));
        return user.isPresent() ? user.get() : null;
    }

    public User findByEmail(String email) {
        List<User> userList = repo.findByEmail(email);
        return userList.get(0);
    }
}
