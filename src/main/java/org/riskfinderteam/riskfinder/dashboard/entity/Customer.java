package org.riskfinderteam.riskfinder.dashboard.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "customer")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class Customer {
    @Id
    @Column(name = "SK_ID_CURR", nullable = false)
    private Integer skIdCurr;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birth", nullable = false)
    private String birth;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "email", nullable = false)
    private String email;
}
