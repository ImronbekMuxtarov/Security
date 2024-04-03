package com.example.securitychains.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.context.annotation.Bean;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;
    private String code;
    private Integer count;



    private Time expireTime;


   private String username;
   private String first_name;
   private String last_name;
   private String password;


    @PrePersist
    public void prePersist(){
        if (expireTime==null && count==null){
            expireTime = Time.valueOf(LocalTime.now().plusMinutes(5L));
            count =0;
        }
    }


}
