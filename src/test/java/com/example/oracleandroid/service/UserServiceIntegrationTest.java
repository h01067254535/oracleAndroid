package com.example.oracleandroid.service;

import com.example.oracleandroid.domain.User;
import com.example.oracleandroid.repository.MemoryUserRepository;
import com.example.oracleandroid.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class UserServiceIntegrationTest {
    @Autowired UserService userService;
    @Autowired UserRepository userRepository;


    @Test
    public void 회원가입() throws Exception {
//Given
        User User = new User();
        User.setName("hel2lo");
//When
        Long saveId = userService.join(User);
        //Then
        User findUser = userService.findOne(saveId).get();
        assertThat(User.getName()).isEqualTo(findUser.getName());
    }
        @Test
        public void 중복_회원_예외() throws Exception {
//Given
            User User1 = new User();
            User1.setName("spring");
            User User2 = new User();
            User2.setName("spring");
//When
            userService.join(User1);
            IllegalStateException e = assertThrows(IllegalStateException.class,
                    () -> userService.join(User2));//예외가 발생해야 한다.
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }


}