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
import com.mcs.store.service.ProductService;

/**
 *
 * @author CILALFONSO
 */
@RestController
@RequestMapping("v1")
public class ProductController {
    @Autowired
    private ProductService productService;
    
                     
    @GetMapping("/products")
    public ResponseEntity getProduct()  {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "Product");
        
        ProductsGetDataModel wrapper = productService.getProducts();        
        return ResponseEntity.ok().headers(headers).body(wrapper);
    }                        

    @PostMapping("/products")
    public ResponseEntity postProduct( @RequestBody ProductsPostDataModel body,  HttpServletRequest request) throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "Product"); 
        return ResponseEntity.created(new URI(request != null ? request.getRequestURI()  : "") ).headers(headers).body(productService.postProduct(body));
    }                 
                     
    @GetMapping("/products/{product_id}")
    public ResponseEntity getProduct(@PathVariable Long product_id )  {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "Product");
        
        ProductGetDataModel wrapper = productService.getProduct(product_id);        
        return ResponseEntity.ok().headers(headers).body(wrapper);
    }                            

    @PutMapping("/products/{product_id}")
    public ResponseEntity putProduct(@PathVariable Long product_id,   @RequestBody ProductsPutDataModel body ) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "Product"); 
        if (product_id != null){
            
        body.getData().setProductId(product_id);        
        }
        return ResponseEntity.ok().headers(headers).body(productService.putProduct(body));
    }                        

    @DeleteMapping("/products/{product_id}")
    public ResponseEntity deleteProduct(@PathVariable Long product_id ) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "Product");
        productService.deleteProduct(product_id);
        return ResponseEntity.ok().headers(headers).build();
        
    }    
          
}
