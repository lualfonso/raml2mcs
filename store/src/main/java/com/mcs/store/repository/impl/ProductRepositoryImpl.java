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
                "insert into product ( product_id, description) values(?,?)",
                 entity.getProductId(), entity.getDescription());
    }

    @Override
    public int update(ProductBean entity) {
        return jdbcTemplate.update(
                "update product set description= ? where product_id = ?",
                entity.getDescription(), entity.getProductId());
    }

    @Override
    public int deleteById(Long product_id) {
        return jdbcTemplate.update(
                "delete from product where product_id = ?",
                product_id);
    }
    
    @Override
    public List<ProductBean> findAll() {
        return jdbcTemplate.query(
                "select product.product_id as product_id , product.description as description  from product",
                
                (rs, rowNum) ->
                        new ProductBean(
                              rs.getLong("product_id"),
                              rs.getString("description")
                            )
        );
    }

    @Override
    public Optional<ProductBean> findById(Long product_id) {
        return jdbcTemplate.queryForObject(
                "select product.product_id as product_id , product.description as description  from product  where product.product_id = ? ",
                new Object[]{product_id},
                (rs, rowNum) ->
                        Optional.of(new ProductBean(
                              rs.getLong("product_id"),
                              rs.getString("description")
                            ))
        );
    }

}
