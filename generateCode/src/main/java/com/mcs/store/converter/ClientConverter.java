
package com.mcs.store.converter;
 
import com.mcs.store.model.ClientGetModel;
 import com.mcs.store.model.ClientPostModel;
import com.mcs.store.model.ClientPutModel;

import com.mcs.store.bean.ClientBean;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author CILALFONSO
 */
@Component
public class ClientConverter {
    
        public List<ClientGetModel> entitiesToModels(List<ClientBean> entities) {
        if (isNull(entities)) {
            return null;
        }
        List<ClientGetModel> models = new ArrayList();
        entities.forEach(m -> models.add(entityToModel(m)));
        return models;
    }

    public ClientGetModel entityToModel(ClientBean entity) {
        if (isNull(entity)) {
            return null;
        }
        ClientGetModel model = new ClientGetModel();
        
        model.setClientId(entity.getId());        
        model.setClientName(entity.getName());        
        model.setClientAge(entity.getAge());        
        return model;
    }
    
    
    public ClientBean modelToEntity(ClientPostModel model) {
        if (isNull(model)) {
            return null;
        }
        ClientBean entity = new ClientBean();
        
        entity.setId(model.getClientId()); 
        entity.setName(model.getClientName()); 
        entity.setAge(model.getClientAge()); 
        return entity;
    }
    
    public ClientBean modelToEntity(ClientPutModel model) {
        if (isNull(model)) {
            return null;
        }
        ClientBean entity = new ClientBean();
        
        entity.setId(model.getClientId()); 
        entity.setName(model.getClientName()); 
        entity.setAge(model.getClientAge()); 
        return entity;
    }
    


    public Boolean isNull(Object any) {
        return any == null;
    }
}
