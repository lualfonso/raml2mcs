
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
        
    @JsonProperty("invoice_detail_invoice_id")   
    private Long invoiceDetailInvoiceId;         
    @JsonProperty("invoice_detail_id")   
    private Long invoiceDetailId;         
    @JsonProperty("invoice_detail_amount")   
    private Long invoiceDetailAmount;     
}
