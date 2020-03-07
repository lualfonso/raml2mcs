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
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from invoicedetail", Integer.class);
    }

    @Override
    public int save(InvoiceDetailBean entity) {
        return jdbcTemplate.update(
                "insert into invoicedetail ( invoice_id, amount) values(?,?)",
                 entity.getInvoiceId(), entity.getAmount());
    }

    @Override
    public int update(InvoiceDetailBean entity) {
        return jdbcTemplate.update(
                "update invoicedetail set invoice_id= ? ,amount= ?  where id  = ?  ",
                  entity.getInvoiceId(),   entity.getAmount(), entity.getId());
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update(
                "delete from invoicedetail where id = ?",
                id);
    }

    @Override
    public List<InvoiceDetailBean> findAll() {
        return jdbcTemplate.query(
                "select invoice.id as invoice_id , invoice_detail.id as id , invoice_detail.amount as amount   from invoicedetail  left join invoice on invoice_detail.invoice_id=invoice.id ",
                (rs, rowNum) ->
                        new InvoiceDetailBean(
                              rs.getLong("invoice_id"),
                              rs.getLong("id"),
                              rs.getLong("amount")
                            )
        );
    }

    @Override
    public Optional<InvoiceDetailBean> findById(Long id) {
        return jdbcTemplate.queryForObject(
                "select invoice.id as invoice_id , invoice_detail.id as id , invoice_detail.amount as amount  from invoicedetail  left join invoice on invoice_detail.invoice_id=invoice.id where  invoicedetail.id = ?",
                new Object[]{id},
                (rs, rowNum) ->
                        Optional.of(new InvoiceDetailBean(
                              rs.getLong("invoice_id"),
                              rs.getLong("id"),
                              rs.getLong("amount")
                            ))
        );
    }

}
