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
    private JdbcTemplate jdbcTemplate;

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from invoice", Integer.class);
    }

    @Override
    public int save(InvoiceBean entity) {
        return jdbcTemplate.update(
                "insert into invoice ( invoice_id, date, client) values(?,?,?)",
                 entity.getInvoiceId(), entity.getDate(), entity.getClient());
    }

    @Override
    public int update(InvoiceBean entity) {
        return jdbcTemplate.update(
                "update invoice set date= ?, client= ? where invoice_id = ?",
                entity.getDate(),entity.getClient(), entity.getInvoiceId());
    }

    @Override
    public int deleteById(Long invoice_id) {
        return jdbcTemplate.update(
                "delete from invoice where invoice_id = ?",
                invoice_id);
    }
    
    @Override
    public List<InvoiceBean> findAll() {
        return jdbcTemplate.query(
                "select invoice.invoice_id as invoice_id , invoice.date as date , client.client_id as client  from invoice left join client on invoice.client=client.client_id",
                
                (rs, rowNum) ->
                        new InvoiceBean(
                              rs.getLong("invoice_id"),
                              rs.getDate("date"),
                              rs.getLong("client")
                            )
        );
    }

    @Override
    public Optional<InvoiceBean> findById(Long invoice_id) {
        return jdbcTemplate.queryForObject(
                "select invoice.invoice_id as invoice_id , invoice.date as date , client.client_id as client  from invoice  left join client on invoice.client=client.client_id where invoice.invoice_id = ? ",
                new Object[]{invoice_id},
                (rs, rowNum) ->
                        Optional.of(new InvoiceBean(
                              rs.getLong("invoice_id"),
                              rs.getDate("date"),
                              rs.getLong("client")
                            ))
        );
    }

}
