package com.NNTeachie.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "sign_up")
public class SignUpDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String fullName;

    private String userName;

    private String passward;

    private String role;

    private String phNumber;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
}
