package com.dojang.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactRequest {

    private String fullName;
    private String email;
    private String subject;
    private String message;
}
