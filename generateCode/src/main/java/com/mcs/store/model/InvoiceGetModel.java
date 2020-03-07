
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
public class InvoiceGetModel {
        
    @JsonProperty("invoice_id")   
    private Long invoiceId;         
    @JsonProperty("invoice_date")   
    private Date invoiceDate;         
    @JsonProperty("invoice_client")   
    private Long invoiceClient;     
}
