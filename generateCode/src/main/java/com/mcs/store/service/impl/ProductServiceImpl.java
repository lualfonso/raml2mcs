/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcs.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mcs.store.model.*;

import com.mcs.store.repository.ProductRepository;
import com.mcs.store.service.ProductService;
import  com.mcs.store.converter.ProductConverter;

/**
 *
 * @author Luis Alfonso
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductConverter converter;
    @Autowired
    private ProductRepository repository;
    
             
    
    @Override
    public ProductsGetDataModel getEntity(Long [])
    {
         return new ProductsGetDataModel(converter.entityToModel(repository.findById([]).get()));
    }
            
    @Override
    public ProductGetDataModel postEntity(ProductsPostDataModel model)    {
        repository.save(converter.modelToEntity(model.getData()));
        return new ProductGetDataModel()  ; 
    }
     
    
    @Override
    public ProductGetDataModel getEntity(Long ["product_id"])
    {
         return new ProductGetDataModel(converter.entityToModel(repository.findById(["product_id"]).get()));
    }
             
    @Override
    public ProductGetDataModel putEntity(ProductsPutDataModel model)    {
        repository.update(converter.modelToEntity(model.getData()));
        return new ProductGetDataModel()  ; 
    }
    
    @Override
    public void deleteEntity(Long ["product_id"])    {
          repository.deleteById(["product_id"]);
    }    
    
}
