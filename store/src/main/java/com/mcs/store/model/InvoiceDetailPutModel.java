
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
public class InvoiceDetailPutModel {
        
    @JsonProperty("invoice_id")   
    private Long invoiceId;         
    @JsonProperty("detail_id")   
    private Long detailId;         
    @JsonProperty("amount")   
    private Long amount;     
}
