package com.NNTeachie.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignUpDetailsRequest {

    private String userName;

    private String fullName;

    private String passward;

    private String role;

    private String phNumber;

    private Date data;
}
