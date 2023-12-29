package com.example.oracleandroid.repository;
import com.example.oracleandroid.domain.User;

import java.util.List;
import java.util.Optional;


public interface UserRepository{
    User save(User user);
    Optional<User> findById(Long empNo);
    Optional<User> findByName(String name);
    List<User> findAll();
    void delete();

}