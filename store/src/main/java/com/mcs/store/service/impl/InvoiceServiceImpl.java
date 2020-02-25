/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcs.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mcs.store.model.*;

import com.mcs.store.repository.InvoiceRepository;
import com.mcs.store.service.InvoiceService;
import  com.mcs.store.converter.InvoiceConverter;

/**
 *
 * @author Luis Alfonso
 */
@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private InvoiceConverter converter;
    @Autowired
    private InvoiceRepository repository;
    
             
    
    @Override
    public InvoicesGetDataModel getEntity(Long [])
    {
         return new InvoicesGetDataModel(converter.entityToModel(repository.findById([]).get()));
    }
            
    @Override
    public InvoiceGetDataModel postEntity(InvoicesPostDataModel model)    {
        repository.save(converter.modelToEntity(model.getData()));
        return new InvoiceGetDataModel()  ; 
    }
     
    
    @Override
    public InvoiceGetDataModel getEntity(Long ["invoice_id"])
    {
         return new InvoiceGetDataModel(converter.entityToModel(repository.findById(["invoice_id"]).get()));
    }
             
    @Override
    public InvoiceGetDataModel putEntity(InvoicesPutDataModel model)    {
        repository.update(converter.modelToEntity(model.getData()));
        return new InvoiceGetDataModel()  ; 
    }
    
    @Override
    public void deleteEntity(Long ["invoice_id"])    {
          repository.deleteById(["invoice_id"]);
    }    
    
    @Override
    public DetailsGetDataModel getEntity(Long ["invoice_id"])
    {
         return new DetailsGetDataModel(converter.entityToModel(repository.findById(["invoice_id"]).get()));
    }
            
    @Override
    public InvoiceDetailGetDataModel postEntity(DetailsPostDataModel model)    {
        repository.save(converter.modelToEntity(model.getData()));
        return new InvoiceDetailGetDataModel()  ; 
    }
     
    
    @Override
    public InvoiceDetailGetDataModel getEntity(Long ["invoice_id", "detail_id"])
    {
         return new InvoiceDetailGetDataModel(converter.entityToModel(repository.findById(["invoice_id", "detail_id"]).get()));
    }
             
    @Override
    public InvoiceDetailGetDataModel putEntity(DetailsPutDataModel model)    {
        repository.update(converter.modelToEntity(model.getData()));
        return new InvoiceDetailGetDataModel()  ; 
    }
    
    @Override
    public void deleteEntity(Long ["invoice_id", "detail_id"])    {
          repository.deleteById(["invoice_id", "detail_id"]);
    }    
    
}
