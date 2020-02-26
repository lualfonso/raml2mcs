/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcs.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mcs.store.model.*;
import com.mcs.store.service.InvoiceService;
import com.mcs.store.repository.InvoiceRepository;
import  com.mcs.store.converter.InvoiceConverter;    
import com.mcs.store.repository.InvoiceDetailRepository;
import  com.mcs.store.converter.InvoiceDetailConverter;     
/**
 *
 * @author Luis Alfonso
 */
@Service
public class InvoiceServiceImpl implements InvoiceService {
         
    
    @Autowired
    private InvoiceConverter invoiceConverter;
    @Autowired
    private InvoiceRepository invoiceRepository;    
    
    
    @Autowired
    private InvoiceDetailConverter invoiceDetailConverter;
    @Autowired
    private InvoiceDetailRepository invoiceDetailRepository;    
       
          
    @Override
    public InvoicesGetDataModel getInvoices()    {
        return new InvoicesGetDataModel(invoiceConverter.entitiesToModels(invoiceRepository.findAll()));
    }
        
    @Override
    public InvoiceGetDataModel postInvoice(InvoicesPostDataModel model)    {
        invoiceRepository.save(invoiceConverter.modelToEntity(model.getData()));
        return new InvoiceGetDataModel()  ; 
    }
       
    @Override
    public InvoiceGetDataModel getInvoice(Long invoice_id)
    {
         return new InvoiceGetDataModel(invoiceConverter.entityToModel(invoiceRepository.findById(invoice_id).get()));
    }
             
    @Override
    public InvoiceGetDataModel putInvoice(InvoicesPutDataModel model)    {
        invoiceRepository.update(invoiceConverter.modelToEntity(model.getData()));
        return new InvoiceGetDataModel()  ; 
    }
    
    @Override
    public void deleteInvoice(Long invoice_id)    {
          invoiceRepository.deleteById(invoice_id);
    }          
    @Override
    public InvoiceDetailsGetDataModel getInvoiceDetails(Long invoice_id)    {
        return new InvoiceDetailsGetDataModel(invoiceDetailConverter.entitiesToModels(invoiceDetailRepository.findAll(invoice_id)));
    }
        
    @Override
    public InvoiceDetailGetDataModel postInvoiceDetail(InvoiceDetailsPostDataModel model)    {
        invoiceDetailRepository.save(invoiceDetailConverter.modelToEntity(model.getData()));
        return new InvoiceDetailGetDataModel()  ; 
    }
       
    @Override
    public InvoiceDetailGetDataModel getInvoiceDetail(Long invoice_id,Long detail_id)
    {
         return new InvoiceDetailGetDataModel(invoiceDetailConverter.entityToModel(invoiceDetailRepository.findById(invoice_id,detail_id).get()));
    }
             
    @Override
    public InvoiceDetailGetDataModel putInvoiceDetail(InvoiceDetailsPutDataModel model)    {
        invoiceDetailRepository.update(invoiceDetailConverter.modelToEntity(model.getData()));
        return new InvoiceDetailGetDataModel()  ; 
    }
    
    @Override
    public void deleteInvoiceDetail(Long invoice_id,Long detail_id)    {
          invoiceDetailRepository.deleteById(invoice_id,detail_id);
    }    
    
}
