package com.example.oracleandroid.service;

import com.example.oracleandroid.domain.User;
import com.example.oracleandroid.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long join(User user) {
        validateDuplicateMember(user);

        userRepository.save(user);
        return user.getEmpNo();
    }
    public void delete() {
        userRepository.delete();
    }

    private void validateDuplicateMember(User user) {
        userRepository.findByName(user.getName())
                .ifPresent(m-> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
    /**
     * 전체 회원 조회
     */
    public List<User> findMembers() {
        return userRepository.findAll();

    }
    public Optional <User> findOne(Long userId) {
        return userRepository.findById(userId);
    }
}
