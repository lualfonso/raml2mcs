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
    public InvoicesGetDataModel getEntity();        
    public InvoiceGetDataModel postEntity(InvoicesPostDataModel model); 
    public InvoiceGetDataModel getEntity(Long invoice_id);         
    public InvoiceGetDataModel putEntity(InvoicesPutDataModel model);
    public void deleteEntity(Long {"get"=>{:request=>{:path=>["invoice_id"], :body=>nil}, :response=>{:code=>"ok", :object=>"InvoiceGetData"}}, "put"=>{:request=>{:path=>["invoice_id"], :body=>"InvoicesPutData"}, :response=>{:code=>"ok", :object=>"InvoiceGetData"}}, "delete"=>{:request=>{:path=>["invoice_id"], :body=>nil}, :response=>{:code=>"ok", :object=>nil}}});
    public InvoiceDetailsGetDataModel getEntity(Long invoice_id);        
    public InvoiceDetailGetDataModel postEntity(InvoiceDetailsPostDataModel model); 
    public InvoiceDetailGetDataModel getEntity(Long invoice_idLong detail_id);         
    public InvoiceDetailGetDataModel putEntity(InvoiceDetailsPutDataModel model);
    public void deleteEntity(Long {"get"=>{:request=>{:path=>["invoice_id", "detail_id"], :body=>nil}, :response=>{:code=>"ok", :object=>"InvoiceDetailGetData"}}, "put"=>{:request=>{:path=>["invoice_id", "detail_id"], :body=>"InvoiceDetailsPutData"}, :response=>{:code=>"ok", :object=>"InvoiceDetailGetData"}}, "delete"=>{:request=>{:path=>["invoice_id", "detail_id"], :body=>nil}, :response=>{:code=>"ok", :object=>nil}}}Long {"get"=>{:request=>{:path=>["invoice_id", "detail_id"], :body=>nil}, :response=>{:code=>"ok", :object=>"InvoiceDetailGetData"}}, "put"=>{:request=>{:path=>["invoice_id", "detail_id"], :body=>"InvoiceDetailsPutData"}, :response=>{:code=>"ok", :object=>"InvoiceDetailGetData"}}, "delete"=>{:request=>{:path=>["invoice_id", "detail_id"], :body=>nil}, :response=>{:code=>"ok", :object=>nil}}});
    
    

}
