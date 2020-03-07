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
    public ClientsGetDataModel getClients();    
    public ClientGetDataModel postClient(ClientsPostDataModel model); 
    public ClientGetDataModel getClient(Long client_id);         
    public ClientGetDataModel putClient(ClientsPutDataModel model);
    public void deleteClient(Long client_id);
    
    

}
