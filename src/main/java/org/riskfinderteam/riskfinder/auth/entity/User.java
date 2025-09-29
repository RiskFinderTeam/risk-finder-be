package org.riskfinderteam.riskfinder.auth.entity;

import jakarta.persistence.*;
import lombok.*;
import org.riskfinderteam.riskfinder.auth.enums.Role;

@Entity
@Table(name = "users")
@AllArgsConstructor
@Getter @Setter
@NoArgsConstructor @Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column
    private Role role;
}
