
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
public class ProductPostModel {
        
    @JsonProperty("id")   
    private Long id;         
    @JsonProperty("description")   
    private String description;     
}
