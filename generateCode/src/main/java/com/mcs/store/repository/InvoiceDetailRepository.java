/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcs.store.repository;

import java.util.List;
import java.util.Optional;
import com.mcs.store.bean.InvoiceDetailBean;

public interface InvoiceDetailRepository {
    int count();

    int save(InvoiceDetailBean entity);

    int update(InvoiceDetailBean entity);

    int deleteById(Long id);

    List<InvoiceDetailBean> findAll();

    Optional<InvoiceDetailBean> findById(Long id);

    
}
