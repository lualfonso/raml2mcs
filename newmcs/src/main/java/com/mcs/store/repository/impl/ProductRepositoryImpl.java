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
import com.mcs.store.bean.ProductBean;
import com.mcs.store.repository.ProductRepository;
/**
 *
 * @author Luis Alfonso
 */
@Repository
public class ProductRepositoryImpl implements ProductRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from product", Integer.class);
    }

    @Override
    public int save(ProductBean entity) {
        return jdbcTemplate.update(
                "insert into product ( id, description) values(?,?)",
                 entity.getId(), entity.getDescription());
    }

    @Override
    public int update(ProductBean entity) {
        return jdbcTemplate.update(
                "update product set description= ? where id = ?",
                entity.getDescription(), entity.getId());
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update(
                "delete from product where id = ?",
                id);
    }
    
    @Override
    public List<ProductBean> findAll() {
        return jdbcTemplate.query(
                "select product.id as id , product.description as description  from product",
                
                (rs, rowNum) ->
                        new ProductBean(
                              rs.getLong("id"),
                              rs.getString("description")
                            )
        );
    }

    @Override
    public Optional<ProductBean> findById(Long id) {
        return jdbcTemplate.queryForObject(
                "select product.id as id , product.description as description  from product  where product.id = ? ",
                new Object[]{id},
                (rs, rowNum) ->
                        Optional.of(new ProductBean(
                              rs.getLong("id"),
                              rs.getString("description")
                            ))
        );
    }

}
