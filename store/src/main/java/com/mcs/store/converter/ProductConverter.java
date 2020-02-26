
package com.mcs.store.converter;
 
import com.mcs.store.model.ProductGetModel;
 import com.mcs.store.model.ProductPostModel;
import com.mcs.store.model.ProductPutModel;

import com.mcs.store.bean.ProductBean;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author CILALFONSO
 */
@Component
public class ProductConverter {
    
        public List<ProductGetModel> entitiesToModels(List<ProductBean> entities) {
        if (isNull(entities)) {
            return null;
        }
        List<ProductGetModel> models = new ArrayList();
        entities.forEach(m -> models.add(entityToModel(m)));
        return models;
    }

    public ProductGetModel entityToModel(ProductBean entity) {
        if (isNull(entity)) {
            return null;
        }
        ProductGetModel model = new ProductGetModel();
        
        model.setDescription(entity.getDescription());        
        return model;
    }
    
    
    public ProductBean modelToEntity(ProductPostModel model) {
        if (isNull(model)) {
            return null;
        }
        ProductBean entity = new ProductBean();
        
        entity.setDescription(model.getDescription()); 
        return entity;
    }
    
    public ProductBean modelToEntity(ProductPutModel model) {
        if (isNull(model)) {
            return null;
        }
        ProductBean entity = new ProductBean();
        
        entity.setDescription(model.getDescription()); 
        return entity;
    }
    


    public Boolean isNull(Object any) {
        return any == null;
    }
}
