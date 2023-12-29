package com.example.oracleandroid.config;

import com.example.oracleandroid.repository.*;
import com.example.oracleandroid.service.UserService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    //    private DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
    @Bean
    public UserService userService() {
        return new UserService(userRepository());
    }

    @Bean
    public UserRepository userRepository() {
        //return new MemoryUserRepository();
        //return new JdbcUserRepository(dataSource);
        //return new JdbcTempleteUserRepository(dataSource);
        return new JpaUserRepository(em);
    }
}
