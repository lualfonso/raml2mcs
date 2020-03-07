
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
public class ClientGetModel {
        
    @JsonProperty("id")   
    private Long id;         
    @JsonProperty("name")   
    private String name;         
    @JsonProperty("age")   
    private Long age;     
}
