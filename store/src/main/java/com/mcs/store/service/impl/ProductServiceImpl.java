/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcs.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mcs.store.model.*;
import com.mcs.store.service.ProductService;
import com.mcs.store.repository.ProductRepository;
import  com.mcs.store.converter.ProductConverter;     
/**
 *
 * @author Luis Alfonso
 */
@Service
public class ProductServiceImpl implements ProductService {
         
    
    @Autowired
    private ProductConverter productConverter;
    @Autowired
    private ProductRepository productRepository;    
       
          
    @Override
    public ProductsGetDataModel getProducts()    {
        return new ProductsGetDataModel(productConverter.entitiesToModels(productRepository.findAll()));
    }
        
    @Override
    public ProductGetDataModel postProduct(ProductsPostDataModel model)    {
        productRepository.save(productConverter.modelToEntity(model.getData()));
        return new ProductGetDataModel()  ; 
    }
       
    @Override
    public ProductGetDataModel getProduct(Long product_id)
    {
         return new ProductGetDataModel(productConverter.entityToModel(productRepository.findById(product_id).get()));
    }
             
    @Override
    public ProductGetDataModel putProduct(ProductsPutDataModel model)    {
        productRepository.update(productConverter.modelToEntity(model.getData()));
        return new ProductGetDataModel()  ; 
    }
    
    @Override
    public void deleteProduct(Long product_id)    {
          productRepository.deleteById(product_id);
    }    
    
}
