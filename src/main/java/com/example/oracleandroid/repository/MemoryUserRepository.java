package com.example.oracleandroid.repository;

import com.example.oracleandroid.domain.User;

import java.util.*;


public class MemoryUserRepository implements UserRepository {
    private static Map<Long, User> store = new HashMap<>();
    private static long sequence = 0L;



    @Override
    public User save(User user) {
        user.setEmpNo(++sequence);
        store.put(user.getEmpNo(), user);
        return user;
    }

    @Override
    public Optional<User> findById(Long empNo) {

        return Optional.ofNullable(store.get(empNo));
    }

    @Override
    public Optional<User> findByName(String name) {
        return store.values().stream()
                .filter(user -> user.getName().equals(name))
                .findAny();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }

    public void delete() {
    }
    public void clearStore() {
        store.clear();
    }
}
