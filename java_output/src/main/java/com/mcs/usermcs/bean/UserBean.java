
package com.mcs.usermcs.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBean {
    
    private Long userId;
    private Long userAge;
    private String userName;
    private String userAddress;
    
}
