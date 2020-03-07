
package com.mcs.store.converter;
 
import com.mcs.store.model.InvoiceGetModel;
 import com.mcs.store.model.InvoicePostModel;
import com.mcs.store.model.InvoicePutModel;

import com.mcs.store.bean.InvoiceBean;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author CILALFONSO
 */
@Component
public class InvoiceConverter {
    
        public List<InvoiceGetModel> entitiesToModels(List<InvoiceBean> entities) {
        if (isNull(entities)) {
            return null;
        }
        List<InvoiceGetModel> models = new ArrayList();
        entities.forEach(m -> models.add(entityToModel(m)));
        return models;
    }

    public InvoiceGetModel entityToModel(InvoiceBean entity) {
        if (isNull(entity)) {
            return null;
        }
        InvoiceGetModel model = new InvoiceGetModel();
        
        model.setDate(entity.getDate());        
        model.setClient(entity.getClient());        
        return model;
    }
    
    
    public InvoiceBean modelToEntity(InvoicePostModel model) {
        if (isNull(model)) {
            return null;
        }
        InvoiceBean entity = new InvoiceBean();
        
        entity.setDate(model.getDate()); 
        entity.setClient(model.getClient()); 
        return entity;
    }
    
    public InvoiceBean modelToEntity(InvoicePutModel model) {
        if (isNull(model)) {
            return null;
        }
        InvoiceBean entity = new InvoiceBean();
        
        entity.setDate(model.getDate()); 
        entity.setClient(model.getClient()); 
        return entity;
    }
    


    public Boolean isNull(Object any) {
        return any == null;
    }
}
