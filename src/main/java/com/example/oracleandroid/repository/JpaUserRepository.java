package com.example.oracleandroid.repository;

import com.example.oracleandroid.domain.User;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaUserRepository implements UserRepository {

    private final EntityManager em;

    public JpaUserRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public User save(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public Optional<User> findById(Long empNo) {
        User user = em.find(User.class, empNo);
        return Optional.ofNullable(user);

    }

    @Override
    public Optional<User> findByName(String name) {
        List<User> result = em.createQuery("select m from user_temp m where m.name = :name", User.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("select m from user_temp m", User.class).getResultList();
    }

    @Override
    public void delete() {
        em.createQuery("delete from user_temp").executeUpdate();
    }
}
