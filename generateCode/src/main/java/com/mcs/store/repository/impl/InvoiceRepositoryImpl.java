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
import com.mcs.store.bean.InvoiceBean;
import com.mcs.store.repository.InvoiceRepository;
/**
 *
 * @author Luis Alfonso
 */
@Repository
public class InvoiceRepositoryImpl implements InvoiceRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from invoice", Integer.class);
    }

    @Override
    public int save(InvoiceBean entity) {
        return jdbcTemplate.update(
                "insert into invoice ( date, client) values(?,?)",
                 entity.getDate(), entity.getClient());
    }

    @Override
    public int update(InvoiceBean entity) {
        return jdbcTemplate.update(
                "update invoice set date= ? ,client= ?  where id  = ?  ",
                   entity.getDate(),  entity.getClient(), entity.getId());
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update(
                "delete from invoice where id = ?",
                id);
    }

    @Override
    public List<InvoiceBean> findAll() {
        return jdbcTemplate.query(
                "select invoice.id as id , invoice.date as date , client.id as client   from invoice  left join client on invoice.client=client.id ",
                (rs, rowNum) ->
                        new InvoiceBean(
                              rs.getLong("id"),
                              rs.getDate("date"),
                              rs.getLong("client")
                            )
        );
    }

    @Override
    public Optional<InvoiceBean> findById(Long id) {
        return jdbcTemplate.queryForObject(
                "select invoice.id as id , invoice.date as date , client.id as client  from invoice  left join client on invoice.client=client.id where  invoice.id = ?",
                new Object[]{id},
                (rs, rowNum) ->
                        Optional.of(new InvoiceBean(
                              rs.getLong("id"),
                              rs.getDate("date"),
                              rs.getLong("client")
                            ))
        );
    }

}
