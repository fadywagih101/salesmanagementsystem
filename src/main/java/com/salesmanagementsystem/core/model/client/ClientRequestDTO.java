package com.salesmanagementsystem.core.model.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClientRequestDTO {
    
    private String name;
    private String lastName;
    private String mobile;
    private String email;
    private String address;

}
