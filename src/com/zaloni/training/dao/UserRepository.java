package com.zaloni.training.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.zaloni.training.entity.User;
import com.zaloni.training.model.Login;

public interface UserRepository extends CrudRepository<User, Long> {
    @Query(value = "SELECT u FROM User u WHERE u.email = :email")
    public List<User> findByEmail(@Param("email") String email);

    @Query(value = "SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
    public List<User> findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

}
