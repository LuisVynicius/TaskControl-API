package com.mevy.taskcontrolapi.entities;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mevy.taskcontrolapi.entities.enums.UserProfileEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Integer> authorities = new HashSet<>();

    @Column
    @JsonFormat(
        pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
        timezone = "UTC",
        shape = JsonFormat.Shape.STRING
    )
    private Instant deleteDate;

    @ManyToOne
    @JoinColumn(
        name = "department_id"
    )
    private Department department;

    @OneToOne(cascade = CascadeType.ALL)
    private UserInformations userInformations;

    public Set<UserProfileEnum> getAuthorities() {
        return authorities
                        .stream()
                        .map(x -> UserProfileEnum.valueOf(x))
                        .collect(Collectors.toSet());
    }

}
