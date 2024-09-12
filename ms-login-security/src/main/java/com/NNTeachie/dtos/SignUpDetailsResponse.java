package com.NNTeachie.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SignUpDetailsResponse {

    private String id;

    private String fullName;

    private String userName;

    private String role;

    private String phNumber;

    private Date date;
}
