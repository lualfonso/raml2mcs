/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcs.usermcs.service;

import com.mcs.usermcs.model.*;

/**
 *
 * @author CILALFONSO
 */
public interface UserService {                 
    public UsersGetDataModel getEntities();    
    public UserGetDataModel postEntity(UsersPostDataModel model); 
    public UserGetDataModel getEntity(Long user_id);         
    public UserGetDataModel putEntity(UsersPutDataModel model);
    public void deleteEntity(Long user_id);
    
    

}
