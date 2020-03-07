
package com.mcs.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.Date;


/**
 *
 * @author CILALFONSO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoicePostModel {
        
    @JsonProperty("invoice_id")   
    private Long invoiceId;         
    @JsonProperty("date")   
    private Date date;         
    @JsonProperty("client")   
    private Long client;     
}
