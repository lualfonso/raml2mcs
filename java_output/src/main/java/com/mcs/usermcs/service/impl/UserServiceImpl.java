/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcs.usermcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mcs.usermcs.model.*;

import com.mcs.usermcs.repository.UserRepository;
import com.mcs.usermcs.service.UserService;
import  com.mcs.usermcs.converter.UserConverter;

/**
 *
 * @author Luis Alfonso
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserConverter converter;
    @Autowired
    private UserRepository repository;
    
             
        
    @Override
    public UsersGetDataModel getEntities()    {
        return new UsersGetDataModel(converter.entitiesToModels(repository.findAll()));
    }
        
    @Override
    public UserGetDataModel postEntity(UsersPostDataModel model)    {
        repository.save(converter.modelToEntity(model.getData()));
        return new UserGetDataModel()  ; 
    }
     
    
    @Override
    public UserGetDataModel getEntity(Long user_id)
    {
         return new UserGetDataModel(converter.entityToModel(repository.findById(user_id).get()));
    }
             
    @Override
    public UserGetDataModel putEntity(UsersPutDataModel model)    {
        repository.update(converter.modelToEntity(model.getData()));
        return new UserGetDataModel()  ; 
    }
    
    @Override
    public void deleteEntity(Long user_id)    {
          repository.deleteById(user_id);
    }    
    
}
