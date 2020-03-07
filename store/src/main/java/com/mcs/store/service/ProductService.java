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
    public ProductsGetDataModel getProducts();    
    public ProductGetDataModel postProduct(ProductsPostDataModel model); 
    public ProductGetDataModel getProduct(Long product_id);         
    public ProductGetDataModel putProduct(ProductsPutDataModel model);
    public void deleteProduct(Long product_id);
    
    

}
