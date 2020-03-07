/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcs.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mcs.store.model.*;
import com.mcs.store.service.ClientService;
import com.mcs.store.repository.ClientRepository;
import  com.mcs.store.converter.ClientConverter;     
/**
 *
 * @author Luis Alfonso
 */
@Service
public class ClientServiceImpl implements ClientService {
         
    
    @Autowired
    private ClientConverter clientConverter;
    @Autowired
    private ClientRepository clientRepository;    
       
          
    @Override
    public ClientsGetDataModel getClients()    {
        return new ClientsGetDataModel(clientConverter.entitiesToModels(clientRepository.findAll()));
    }
        
    @Override
    public ClientGetDataModel postClient(ClientsPostDataModel model)    {
        clientRepository.save(clientConverter.modelToEntity(model.getData()));
        return new ClientGetDataModel()  ; 
    }
       
    @Override
    public ClientGetDataModel getClient(Long client_id)
    {
         return new ClientGetDataModel(clientConverter.entityToModel(clientRepository.findById(client_id).get()));
    }
             
    @Override
    public ClientGetDataModel putClient(ClientsPutDataModel model)    {
        clientRepository.update(clientConverter.modelToEntity(model.getData()));
        return new ClientGetDataModel()  ; 
    }
    
    @Override
    public void deleteClient(Long client_id)    {
          clientRepository.deleteById(client_id);
    }    
    
}
