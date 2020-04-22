/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcs.store.repository.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import com.mcs.store.bean.ClientBean;
import com.mcs.store.repository.ClientRepository;
/**
 *
 * @author Luis Alfonso
 */
@Repository
public class ClientRepositoryImpl implements ClientRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from client", Integer.class);
    }

    @Override
    public int save(ClientBean entity) {
        return jdbcTemplate.update(
                "insert into client ( name, age) values(?,?)",
                 entity.getName(), entity.getAge());
    }

    @Override
    public int update(ClientBean entity) {
        return jdbcTemplate.update(
                "update client set name= ? ,age= ?  where id  = ?  ",
                   entity.getName(),  entity.getAge(), entity.getId());
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update(
                "delete from client where id = ?",
                id);
    }

    @Override
    public List<ClientBean> findAll() {
        return jdbcTemplate.query(
                "select client.id as id , client.name as name , client.age as age   from client  ",
                (rs, rowNum) ->
                        new ClientBean(
                              rs.getLong("id"),
                              rs.getString("name"),
                              rs.getLong("age")
                            )
        );
    }

    @Override
    public Optional<ClientBean> findById(Long id) {
        return jdbcTemplate.queryForObject(
                "select client.id as id , client.name as name , client.age as age  from client  where  client.id = ?",
                new Object[]{id},
                (rs, rowNum) ->
                        Optional.of(new ClientBean(
                              rs.getLong("id"),
                              rs.getString("name"),
                              rs.getLong("age")
                            ))
        );
    }

}
