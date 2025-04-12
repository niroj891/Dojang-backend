package com.dojang.response;

import com.dojang.model.WeightCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String dojangName;
    private WeightCategory weightCategory;
    private Integer userId;
    private String username;
    private String userFirstName;
    private String userLastName;
}
