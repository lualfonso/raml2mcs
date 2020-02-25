/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcs.store.service;

import com.mcs.store.model.*;

/**
 *
 * @author CILALFONSO
 */
public interface ClientService {             
    public ClientsGetDataModel getEntity(Long []);        
    public ClientGetDataModel postEntity(ClientsPostDataModel model); 
    public ClientGetDataModel getEntity(Long ["client_id"]);         
    public ClientGetDataModel putEntity(ClientsPutDataModel model);
    public void deleteEntity(Long ["client_id"]);
    
    

}
