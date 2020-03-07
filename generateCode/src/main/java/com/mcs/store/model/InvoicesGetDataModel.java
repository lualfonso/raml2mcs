
package com.mcs.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;



import java.util.List;

/**
 *
 * @author CILALFONSO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoicesGetDataModel {
        
    @JsonProperty("data")     private List<InvoiceGetModel> data;         
}
