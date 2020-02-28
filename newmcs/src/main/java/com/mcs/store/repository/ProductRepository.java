/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcs.store.repository;

import java.util.List;
import java.util.Optional;
import com.mcs.store.bean.ProductBean;

public interface ProductRepository {
    int count();

    int save(ProductBean entity);

    int update(ProductBean entity);

    int deleteById(Long id);

    List<ProductBean> findAll();

    Optional<ProductBean> findById(Long id);

    
}
