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
    public ResponseEntity getEntity()  {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "InvoiceController");
        
        InvoicesGetDataModel wrapper = invoiceService.getEntity([]);        
        return ResponseEntity.ok().headers(headers).body(wrapper);
    }                        

    @PostMapping("/invoices")
    public ResponseEntity postEntity( @RequestBody InvoicesPostDataModel body,  HttpServletRequest request) throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "InvoiceController"); 
        return ResponseEntity.created(new URI(request != null ? request.getRequestURI()  : "") ).headers(headers).body(invoiceService.postEntity(body));
    }                 
     
    @GetMapping("/invoices/{invoice_id}")
    public ResponseEntity getEntity(@PathVariable Long invoice_id )  {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "InvoiceController");
        
        InvoiceGetDataModel wrapper = invoiceService.getEntity(["invoice_id"]);        
        return ResponseEntity.ok().headers(headers).body(wrapper);
    }                            

    @PutMapping("/invoices/{invoice_id}")
    public ResponseEntity putEntity(@PathVariable Long ["invoice_id"],   @RequestBody InvoicesPutDataModel body ) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "InvoiceController");
        if (["invoice_id"] != null){
            
        body.getData().setInvoiceId(invoice_id);        
        }
        return ResponseEntity.ok().headers(headers).body(invoiceService.putEntity(body));
    }                        

    @DeleteMapping("/invoices/{invoice_id}")
    public ResponseEntity deleteEntity(@PathVariable Long ["invoice_id"] ) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "InvoiceController");
        invoiceService.deleteEntity(["invoice_id"]);
        return ResponseEntity.ok().headers(headers).build();
        
    }    
         
     
    @GetMapping("/invoices/{invoice_id}/invoicedetails")
    public ResponseEntity getEntity(@PathVariable Long invoice_id )  {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "InvoiceController");
        
        InvoiceDetailsGetDataModel wrapper = invoiceService.getEntity(["invoice_id"]);        
        return ResponseEntity.ok().headers(headers).body(wrapper);
    }                        

    @PostMapping("/invoices/{invoice_id}/invoicedetails")
    public ResponseEntity postEntity( @RequestBody InvoiceDetailsPostDataModel body,  HttpServletRequest request) throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "InvoiceController"); 
        return ResponseEntity.created(new URI(request != null ? request.getRequestURI()  : "") ).headers(headers).body(invoiceService.postEntity(body));
    }                 
     
    @GetMapping("/invoices/{invoice_id}/invoicedetails/{detail_id}")
    public ResponseEntity getEntity(@PathVariable Long invoice_id ,@PathVariable Long detail_id )  {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "InvoiceController");
        
        InvoiceDetailGetDataModel wrapper = invoiceService.getEntity(["invoice_id", "detail_id"]);        
        return ResponseEntity.ok().headers(headers).body(wrapper);
    }                            

    @PutMapping("/invoices/{invoice_id}/invoicedetails/{detail_id}")
    public ResponseEntity putEntity(@PathVariable Long ["invoice_id", "detail_id"],   @RequestBody InvoiceDetailsPutDataModel body ) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "InvoiceController");
        if (["invoice_id", "detail_id"] != null){
            
        body.getData().setInvoiceId(invoice_id);        
        body.getData().setDetailId(detail_id);        
        }
        return ResponseEntity.ok().headers(headers).body(invoiceService.putEntity(body));
    }                        

    @DeleteMapping("/invoices/{invoice_id}/invoicedetails/{detail_id}")
    public ResponseEntity deleteEntity(@PathVariable Long ["invoice_id", "detail_id"] ) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "InvoiceController");
        invoiceService.deleteEntity(["invoice_id", "detail_id"]);
        return ResponseEntity.ok().headers(headers).build();
        
    }    
          
}
