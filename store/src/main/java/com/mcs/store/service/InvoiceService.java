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
    public InvoicesGetDataModel getInvoices();    
    public InvoiceGetDataModel postInvoice(InvoicesPostDataModel model); 
    public InvoiceGetDataModel getInvoice(Long invoice_id);         
    public InvoiceGetDataModel putInvoice(InvoicesPutDataModel model);
    public void deleteInvoice(Long invoice_id);    
    public InvoiceDetailsGetDataModel getInvoiceDetails(Long invoice_id);    
    public InvoiceDetailGetDataModel postInvoiceDetail(InvoiceDetailsPostDataModel model); 
    public InvoiceDetailGetDataModel getInvoiceDetail(Long invoice_id,Long detail_id);         
    public InvoiceDetailGetDataModel putInvoiceDetail(InvoiceDetailsPutDataModel model);
    public void deleteInvoiceDetail(Long invoice_id,Long detail_id);
    
    

}
