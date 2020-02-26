
package com.mcs.store.converter;
 
import com.mcs.store.model.InvoiceDetailGetModel;
 import com.mcs.store.model.InvoiceDetailPostModel;
import com.mcs.store.model.InvoiceDetailPutModel;

import com.mcs.store.bean.InvoiceDetailBean;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author CILALFONSO
 */
@Component
public class InvoiceDetailConverter {
    
        public List<InvoiceDetailGetModel> entitiesToModels(List<InvoiceDetailBean> entities) {
        if (isNull(entities)) {
            return null;
        }
        List<InvoiceDetailGetModel> models = new ArrayList();
        entities.forEach(m -> models.add(entityToModel(m)));
        return models;
    }

    public InvoiceDetailGetModel entityToModel(InvoiceDetailBean entity) {
        if (isNull(entity)) {
            return null;
        }
        InvoiceDetailGetModel model = new InvoiceDetailGetModel();
        
        model.setAmount(entity.getAmount());        
        return model;
    }
    
    
    public InvoiceDetailBean modelToEntity(InvoiceDetailPostModel model) {
        if (isNull(model)) {
            return null;
        }
        InvoiceDetailBean entity = new InvoiceDetailBean();
        
        entity.setAmount(model.getAmount()); 
        return entity;
    }
    
    public InvoiceDetailBean modelToEntity(InvoiceDetailPutModel model) {
        if (isNull(model)) {
            return null;
        }
        InvoiceDetailBean entity = new InvoiceDetailBean();
        
        entity.setAmount(model.getAmount()); 
        return entity;
    }
    


    public Boolean isNull(Object any) {
        return any == null;
    }
}
