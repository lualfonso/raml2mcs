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
import com.mcs.store.bean.InvoiceDetailBean;
import com.mcs.store.repository.InvoiceDetailRepository;
/**
 *
 * @author Luis Alfonso
 */
@Repository
public class InvoiceDetailRepositoryImpl implements InvoiceDetailRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from invoice_detail", Integer.class);
    }

    @Override
    public int save(InvoiceDetailBean entity) {
        return jdbcTemplate.update(
                "insert into invoice_detail ( invoice_id, id, amount) values(?,?,?)",
                 entity.getInvoiceId(), entity.getId(), entity.getAmount());
    }

    @Override
    public int update(InvoiceDetailBean entity) {
        return jdbcTemplate.update(
                "update invoice_detail set amount= ? where invoice_id = ? and id = ?",
                entity.getAmount(), entity.getInvoiceId(), entity.getId());
    }

    @Override
    public int deleteById(Long invoice_id, Long id) {
        return jdbcTemplate.update(
                "delete from invoicedetail where invoice_id = ? and id = ?",
                invoice_id, id);
    }
    
    @Override
    public List<InvoiceDetailBean> findAll(Long invoice_id) {
        return jdbcTemplate.query(
                "select invoice.id as invoice_id , invoice_detail.id as id , invoice_detail.amount as amount  from invoice_detail left join invoice on invoice_detail.invoice_id=invoice.id where invoice_detail.invoice_id = ? ",
                new Object[]{invoice_id},
                (rs, rowNum) ->
                        new InvoiceDetailBean(
                              rs.getLong("invoice_id"),
                              rs.getLong("id"),
                              rs.getLong("amount")
                            )
        );
    }

    @Override
    public Optional<InvoiceDetailBean> findById(Long invoice_id, Long id) {
        return jdbcTemplate.queryForObject(
                "select invoice.id as invoice_id , invoice_detail.id as id , invoice_detail.amount as amount  from invoice_detail  left join invoice on invoice_detail.invoice_id=invoice.id where invoice_detail.invoice_id = ?  and invoice_detail.id = ? ",
                new Object[]{invoice_id, id},
                (rs, rowNum) ->
                        Optional.of(new InvoiceDetailBean(
                              rs.getLong("invoice_id"),
                              rs.getLong("id"),
                              rs.getLong("amount")
                            ))
        );
    }

}