/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcs.store.repository;

import java.util.List;
import java.util.Optional;
import com.mcs.store.bean.ClientBean;

public interface ClientRepository {
    int count();

    int save(ClientBean entity);

    int update(ClientBean entity);

    int deleteById(Long client_id);

    List<ClientBean> findAll();

    Optional<ClientBean> findById(Long client_id);

    
}
