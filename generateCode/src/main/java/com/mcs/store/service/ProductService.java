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
public interface ProductService {             
    public ProductsGetDataModel getEntity(Long []);        
    public ProductGetDataModel postEntity(ProductsPostDataModel model); 
    public ProductGetDataModel getEntity(Long ["product_id"]);         
    public ProductGetDataModel putEntity(ProductsPutDataModel model);
    public void deleteEntity(Long ["product_id"]);
    
    

}
