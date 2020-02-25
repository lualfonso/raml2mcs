/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcs.store.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mcs.store.model.*;
import com.mcs.store.service.ClientService;


/**
 *
 * @author CILALFONSO
 */
@RestController
@RequestMapping("v1")
public class ClientController {
    @Autowired
    private ClientService clientService;
    
     
    @GetMapping("/clients")
    public ResponseEntity getEntity()  {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "ClientController");
        
        ClientsGetDataModel wrapper = clientService.getEntity([]);        
        return ResponseEntity.ok().headers(headers).body(wrapper);
    }                        

    @PostMapping("/clients")
    public ResponseEntity postEntity( @RequestBody ClientsPostDataModel body,  HttpServletRequest request) throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "ClientController"); 
        return ResponseEntity.created(new URI(request != null ? request.getRequestURI()  : "") ).headers(headers).body(clientService.postEntity(body));
    }                 
     
    @GetMapping("/clients/{client_id}")
    public ResponseEntity getEntity(@PathVariable Long client_id )  {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "ClientController");
        
        ClientGetDataModel wrapper = clientService.getEntity(["client_id"]);        
        return ResponseEntity.ok().headers(headers).body(wrapper);
    }                            

    @PutMapping("/clients/{client_id}")
    public ResponseEntity putEntity(@PathVariable Long ["client_id"],   @RequestBody ClientsPutDataModel body ) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "ClientController");
        if (["client_id"] != null){
            
        body.getData().setClientId(client_id);        
        }
        return ResponseEntity.ok().headers(headers).body(clientService.putEntity(body));
    }                        

    @DeleteMapping("/clients/{client_id}")
    public ResponseEntity deleteEntity(@PathVariable Long ["client_id"] ) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "ClientController");
        clientService.deleteEntity(["client_id"]);
        return ResponseEntity.ok().headers(headers).build();
        
    }    
          
}
