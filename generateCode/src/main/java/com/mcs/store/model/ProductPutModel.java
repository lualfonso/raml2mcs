
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
public class ProductPutModel {
        
    @JsonProperty("product_id")   
    private Long productId;         
    @JsonProperty("product_description")   
    private String productDescription;     
}
