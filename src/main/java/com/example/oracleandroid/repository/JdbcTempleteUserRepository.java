package com.example.oracleandroid.repository;

import com.example.oracleandroid.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class JdbcTempleteUserRepository implements UserRepository{
    private final JdbcTemplate jdbcTemplate;

    public JdbcTempleteUserRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User save(User user) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("user_temp").usingGeneratedKeyColumns("EMP_NO");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", user.getName());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        user.setEmpNo(key.longValue());
        return user;
    }

    @Override
    public Optional<User> findById(Long empNo) {
        List<User> result = jdbcTemplate.query("select * from user_temp where EMP_NO=?", userRowMapper(), empNo);
        return result.stream().findAny();
    }

    @Override
    public Optional<User> findByName(String name) {
        List<User> result = jdbcTemplate.query("select * from user_temp where name=?", userRowMapper(), name);
        return result.stream().findAny();
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from user_temp", userRowMapper());
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> {
            User user = new User();
            user.setEmpNo(rs.getLong("emp_no"));
            user.setName(rs.getString("name"));
            return user;
        };
    }
    public void delete() {
    }
    //jdbcTemplate.query 메서드는 주어진 SQL 쿼리를 실행하고, 그 결과를 RowMapper를 사용하여 매핑한 후에 List 형태로 반환합니다.

//    private RowMapper<User> userRowMapper() {
//        return new RowMapper<User>() {
//            @Override
//            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
//                User user = new User();
//                user.setEmpNo(rs.getLong("id"));
//                user.setName(rs.getString("name"));
//                return user;
//            }
//        };
//    }

}
