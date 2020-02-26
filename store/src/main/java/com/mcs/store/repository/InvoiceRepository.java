/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcs.store.repository;

import java.util.List;
import java.util.Optional;
import com.mcs.store.bean.InvoiceBean;

public interface InvoiceRepository {
    int count();

    int save(InvoiceBean entity);

    int update(InvoiceBean entity);

    int deleteById(Long invoice_id);

    List<InvoiceBean> findAll();

    Optional<InvoiceBean> findById(Long invoice_id);

    
}
