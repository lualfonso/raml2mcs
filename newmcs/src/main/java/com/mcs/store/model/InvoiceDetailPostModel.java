
package com.mcs.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;





/**
 *
 * @author CILALFONSO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDetailPostModel {
        
    @JsonProperty("invoice_id")   
    private Long invoiceId;         
    @JsonProperty("id")   
    private Long id;         
    @JsonProperty("amount")   
    private Long amount;     
}
