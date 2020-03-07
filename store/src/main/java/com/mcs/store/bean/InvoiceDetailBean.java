
package com.mcs.store.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDetailBean {
    
    private Long invoiceId;
    private Long detailId;
    private Long amount;
    
}
