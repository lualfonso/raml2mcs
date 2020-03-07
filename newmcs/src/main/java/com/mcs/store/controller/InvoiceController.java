/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcs.store.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mcs.store.model.*;
import com.mcs.store.service.InvoiceService;

/**
 *
 * @author CILALFONSO
 */
@RestController
@RequestMapping("v1")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;
    
                     
    @GetMapping("/invoices")
    public ResponseEntity getInvoice()  {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "Invoice");
        
        InvoicesGetDataModel wrapper = invoiceService.getInvoices();        
        return ResponseEntity.ok().headers(headers).body(wrapper);
    }                        

    @PostMapping("/invoices")
    public ResponseEntity postInvoice( @RequestBody InvoicesPostDataModel body,  HttpServletRequest request) throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "Invoice"); 
        return ResponseEntity.created(new URI(request != null ? request.getRequestURI()  : "") ).headers(headers).body(invoiceService.postInvoice(body));
    }                 
                     
    @GetMapping("/invoices/{invoice_id}")
    public ResponseEntity getInvoice(@PathVariable Long invoice_id )  {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "Invoice");
        
        InvoiceGetDataModel wrapper = invoiceService.getInvoice(invoice_id);        
        return ResponseEntity.ok().headers(headers).body(wrapper);
    }                            

    @PutMapping("/invoices/{invoice_id}")
    public ResponseEntity putInvoice(@PathVariable Long invoice_id,   @RequestBody InvoicesPutDataModel body ) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "Invoice"); 
        if (invoice_id != null){
            
        body.getData().setId(invoice_id);        
        }
        return ResponseEntity.ok().headers(headers).body(invoiceService.putInvoice(body));
    }                        

    @DeleteMapping("/invoices/{invoice_id}")
    public ResponseEntity deleteInvoice(@PathVariable Long invoice_id ) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "Invoice");
        invoiceService.deleteInvoice(invoice_id);
        return ResponseEntity.ok().headers(headers).build();
        
    }    
         
                     
    @GetMapping("/invoices/{invoice_id}/details")
    public ResponseEntity getInvoiceDetail(@PathVariable Long invoice_id )  {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "Invoice");
        
        InvoiceDetailsGetDataModel wrapper = invoiceService.getInvoiceDetails(invoice_id);        
        return ResponseEntity.ok().headers(headers).body(wrapper);
    }                        

    @PostMapping("/invoices/{invoice_id}/details")
    public ResponseEntity postInvoiceDetail( @RequestBody InvoiceDetailsPostDataModel body,  HttpServletRequest request) throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "Invoice"); 
        return ResponseEntity.created(new URI(request != null ? request.getRequestURI()  : "") ).headers(headers).body(invoiceService.postInvoiceDetail(body));
    }                 
                     
    @GetMapping("/invoices/{invoice_id}/details/{invoice_detail_id}")
    public ResponseEntity getInvoiceDetail(@PathVariable Long invoice_id ,@PathVariable Long invoice_detail_id )  {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "Invoice");
        
        InvoiceDetailGetDataModel wrapper = invoiceService.getInvoiceDetail(invoice_id,invoice_detail_id);        
        return ResponseEntity.ok().headers(headers).body(wrapper);
    }                            

    @PutMapping("/invoices/{invoice_id}/details/{invoice_detail_id}")
    public ResponseEntity putInvoiceDetail(@PathVariable Long invoice_id, @PathVariable Long invoice_detail_id,   @RequestBody InvoiceDetailsPutDataModel body ) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "Invoice"); 
        if (invoice_id != null && invoice_detail_id != null){
            
        body.getData().setInvoiceId(invoice_id);        
        body.getData().setId(invoice_detail_id);        
        }
        return ResponseEntity.ok().headers(headers).body(invoiceService.putInvoiceDetail(body));
    }                        

    @DeleteMapping("/invoices/{invoice_id}/details/{invoice_detail_id}")
    public ResponseEntity deleteInvoiceDetail(@PathVariable Long invoice_id ,@PathVariable Long invoice_detail_id ) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "Invoice");
        invoiceService.deleteInvoiceDetail(invoice_id, invoice_detail_id);
        return ResponseEntity.ok().headers(headers).build();
        
    }    
          
}
