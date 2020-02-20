
package com.mcs.usermcs.converter;
 
import com.mcs.usermcs.model.UserGetModel;
 import com.mcs.usermcs.model.UserPostModel;
import com.mcs.usermcs.model.UserPutModel;

import com.mcs.usermcs.bean.UserBean;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author CILALFONSO
 */
@Component
public class UserConverter {
    
        public List<UserGetModel> entitiesToModels(List<UserBean> entities) {
        if (isNull(entities)) {
            return null;
        }
        List<UserGetModel> models = new ArrayList();
        entities.forEach(m -> models.add(entityToModel(m)));
        return models;
    }

    public UserGetModel entityToModel(UserBean entity) {
        if (isNull(entity)) {
            return null;
        }
        UserGetModel model = new UserGetModel();
        
        model.setUserId(entity.getUserId());        
        model.setUserAge(entity.getUserAge());        
        model.setUserName(entity.getUserName());        
        model.setUserAddress(entity.getUserAddress());        
        return model;
    }
    
    
    public UserBean modelToEntity(UserPostModel model) {
        if (isNull(model)) {
            return null;
        }
        UserBean entity = new UserBean();
        
        entity.setUserId(model.getUserId()); 
        entity.setUserAge(model.getUserAge()); 
        entity.setUserName(model.getUserName()); 
        entity.setUserAddress(model.getUserAddress()); 
        return entity;
    }
    
    public UserBean modelToEntity(UserPutModel model) {
        if (isNull(model)) {
            return null;
        }
        UserBean entity = new UserBean();
        
        entity.setUserId(model.getUserId()); 
        entity.setUserAge(model.getUserAge()); 
        entity.setUserName(model.getUserName()); 
        entity.setUserAddress(model.getUserAddress()); 
        return entity;
    }
    


    public Boolean isNull(Object any) {
        return any == null;
    }
}
