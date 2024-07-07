package com.mevy.taskcontrolapi.entities;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
        nullable = false,
        unique = true,
        updatable = false
    )
    private Long id;

    @Column(
        nullable = false,
        unique = true,
        length = 60
    )
    private String fullName;

    @Column(
        nullable = false,
        unique = true,
        length = 60
    )
    private String email;

    @Column(
        nullable = false
    )
    private String password;

    @Column
    @JsonFormat(
        pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
        timezone = "UTC",
        shape = JsonFormat.Shape.STRING
    )
    private Instant deleteDate;    

}