package com.example.securitychains.entity;

import com.example.securitychains.RoleName;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Roles implements Serializable {
    @Id
    @Enumerated(EnumType.STRING)
    private RoleName role;
}
