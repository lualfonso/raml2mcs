
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
public class ClientPutModel {
        
    @JsonProperty("client_id")   
    private Long clientId;         
    @JsonProperty("client_name")   
    private String clientName;         
    @JsonProperty("client_age")   
    private Long clientAge;     
}
