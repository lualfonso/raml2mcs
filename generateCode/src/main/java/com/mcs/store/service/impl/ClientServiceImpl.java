/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcs.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mcs.store.model.*;

import com.mcs.store.repository.ClientRepository;
import com.mcs.store.service.ClientService;
import  com.mcs.store.converter.ClientConverter;

/**
 *
 * @author Luis Alfonso
 */
@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientConverter converter;
    @Autowired
    private ClientRepository repository;
    
             
    
    @Override
    public ClientsGetDataModel getEntity(Long [])
    {
         return new ClientsGetDataModel(converter.entityToModel(repository.findById([]).get()));
    }
            
    @Override
    public ClientGetDataModel postEntity(ClientsPostDataModel model)    {
        repository.save(converter.modelToEntity(model.getData()));
        return new ClientGetDataModel()  ; 
    }
     
    
    @Override
    public ClientGetDataModel getEntity(Long ["client_id"])
    {
         return new ClientGetDataModel(converter.entityToModel(repository.findById(["client_id"]).get()));
    }
             
    @Override
    public ClientGetDataModel putEntity(ClientsPutDataModel model)    {
        repository.update(converter.modelToEntity(model.getData()));
        return new ClientGetDataModel()  ; 
    }
    
    @Override
    public void deleteEntity(Long ["client_id"])    {
          repository.deleteById(["client_id"]);
    }    
    
}
