/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcs.usermcs.controller;

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
import com.mcs.usermcs.model.*;
import com.mcs.usermcs.service.UserService;


/**
 *
 * @author CILALFONSO
 */
@RestController
@RequestMapping("v1")
public class UserController {
    @Autowired
    private UserService userService;
    
     
    @GetMapping("/users")
    public ResponseEntity getEntity()  {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "UserController");
        
        UsersGetDataModel wrapper = userService.getEntities();        
        return ResponseEntity.ok().headers(headers).body(wrapper);
    }                        

    @PostMapping("/users")
    public ResponseEntity postEntity( @RequestBody UsersPostDataModel body,  HttpServletRequest request) throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "UserController"); 
        return ResponseEntity.created(new URI(request != null ? request.getRequestURI()  : "") ).headers(headers).body(userService.postEntity(body));
    }                 
     
    @GetMapping("/users/{user_id}")
    public ResponseEntity getEntity(@PathVariable Long user_id )  {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "UserController");
        
        UserGetDataModel wrapper = userService.getEntity(user_id);        
        return ResponseEntity.ok().headers(headers).body(wrapper);
    }                            

    @PutMapping("/users/{user_id}")
    public ResponseEntity putEntity(@PathVariable Long user_id,   @RequestBody UsersPutDataModel body ) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "UserController");
        if (user_id != null){
        body.getData().setUserId(user_id);
        }
        return ResponseEntity.ok().headers(headers).body(userService.putEntity(body));
    }                        

    @DeleteMapping("/users/{user_id}")
    public ResponseEntity deleteEntity(@PathVariable Long user_id ) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "UserController");
        userService.deleteEntity(user_id);
        return ResponseEntity.ok().headers(headers).build();
        
    }    
          
}
