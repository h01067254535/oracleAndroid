package com.example.oracleandroid.service;

import com.example.oracleandroid.domain.User;
import com.example.oracleandroid.repository.MemoryUserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class UserServiceTest {
    UserService userService;
    MemoryUserRepository userRepository;

    @BeforeEach
    public void beforeEach() {
        userRepository = new MemoryUserRepository();
        userService = new UserService(userRepository);
    }
    @AfterEach
    public void afterEach() {
        userRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //GIVEN
        User user = new User();
        user.setName("hello");

        //WHEN
        Long empNo = userService.join(user);

        //THEN
        User findUser = userService.findOne(empNo).get();
        assertThat(findUser.getName()).isEqualTo(findUser.getName());
    }

    @Test
    public void 중복_회원_예외() {
        User user1 = new User();
        user1.setName("spring1");

        User user2 = new User();
        user2.setName("spring2");


        userService.join(user1);
        assertThrows(IllegalStateException.class, () ->
                userService.join(user1));
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}