
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
public class UserPostModel {
        
    @JsonProperty("user_id")   
    private Long userId;         
    @JsonProperty("user_age")   
    private Long userAge;         
    @JsonProperty("user_name")   
    private String userName;         
    @JsonProperty("user_address")   
    private String userAddress;     
}
