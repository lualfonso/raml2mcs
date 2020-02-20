/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcs.usermcs.repository;

import java.util.List;
import java.util.Optional;
import com.mcs.usermcs.bean.UserBean;

public interface UserRepository {
    int count();

    int save(UserBean entity);

    int update(UserBean entity);

    int deleteById(Long id);

    List<UserBean> findAll();

    Optional<UserBean> findById(Long id);

    
}
