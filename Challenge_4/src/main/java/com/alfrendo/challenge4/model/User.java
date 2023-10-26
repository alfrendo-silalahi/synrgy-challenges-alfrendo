package com.alfrendo.challenge4.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 50)
    private String username;

    @Column(length = 50, unique = true)
    private String emailAddress;

    private String password;

    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    private boolean isDeleted;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

}
