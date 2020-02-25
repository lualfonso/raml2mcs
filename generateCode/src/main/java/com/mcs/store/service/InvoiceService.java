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
public interface InvoiceService {             
    public InvoicesGetDataModel getEntity(Long []);        
    public InvoiceGetDataModel postEntity(InvoicesPostDataModel model); 
    public InvoiceGetDataModel getEntity(Long ["invoice_id"]);         
    public InvoiceGetDataModel putEntity(InvoicesPutDataModel model);
    public void deleteEntity(Long ["invoice_id"]);
    public InvoiceDetailsGetDataModel getEntity(Long ["invoice_id"]);        
    public InvoiceDetailGetDataModel postEntity(InvoiceDetailsPostDataModel model); 
    public InvoiceDetailGetDataModel getEntity(Long ["invoice_id", "detail_id"]);         
    public InvoiceDetailGetDataModel putEntity(InvoiceDetailsPutDataModel model);
    public void deleteEntity(Long ["invoice_id", "detail_id"]);
    
    

}
