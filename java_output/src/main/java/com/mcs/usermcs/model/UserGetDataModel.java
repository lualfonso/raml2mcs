
package com.mcs.usermcs.model;

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
public class UserGetDataModel {
        
    @JsonProperty("data")   
    private UserGetModel data;     
}