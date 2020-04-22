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
    public ProductsGetDataModel getEntity();        
    public ProductGetDataModel postEntity(ProductsPostDataModel model); 
    public ProductGetDataModel getEntity(Long product_id);         
    public ProductGetDataModel putEntity(ProductsPutDataModel model);
    public void deleteEntity(Long {"get"=>{:request=>{:path=>["product_id"], :body=>nil}, :response=>{:code=>"ok", :object=>"ProductGetData"}}, "put"=>{:request=>{:path=>["product_id"], :body=>"ProductsPutData"}, :response=>{:code=>"ok", :object=>"ProductGetData"}}, "delete"=>{:request=>{:path=>["product_id"], :body=>nil}, :response=>{:code=>"ok", :object=>nil}}});
    
    

}
