/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcs.usermcs.repository.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import com.mcs.usermcs.bean.UserBean;
import com.mcs.usermcs.repository.UserRepository;
/**
 *
 * @author Luis Alfonso
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from user", Integer.class);
    }

    @Override
    public int save(UserBean entity) {
        return jdbcTemplate.update(
                "insert into user ( user_age, user_name, user_address) values(?,?,?)",
                 entity.getUserAge(), entity.getUserName(), entity.getUserAddress());
    }

    @Override
    public int update(UserBean entity) {
        return jdbcTemplate.update(
                "update user set user_age= ? ,user_name= ? ,user_address= ?  where user_id  = ?  ",
                   entity.getUserAge(),  entity.getUserName(),  entity.getUserAddress(), entity.getUserId());
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update(
                "delete from user where user_id = ?",
                id);
    }

    @Override
    public List<UserBean> findAll() {
        return jdbcTemplate.query(
                "select user_id as user_id , user_age as user_age , user_name as user_name , user_address as user_address   from user  ",
                (rs, rowNum) ->
                        new UserBean(
                              rs.getLong("user_id"),
                              rs.getLong("user_age"),
                              rs.getString("user_name"),
                              rs.getString("user_address")
                            )
        );
    }

    @Override
    public Optional<UserBean> findById(Long id) {
        return jdbcTemplate.queryForObject(
                "select user_id as user_id , user_age as user_age , user_name as user_name , user_address as user_address  from user  where user_id = ?",
                new Object[]{id},
                (rs, rowNum) ->
                        Optional.of(new UserBean(
                              rs.getLong("user_id"),
                              rs.getLong("user_age"),
                              rs.getString("user_name"),
                              rs.getString("user_address")
                            ))
        );
    }

}
