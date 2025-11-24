package org.riskfinderteam.riskfinder.dashboard.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "customer_info")
@AllArgsConstructor
@Getter @Setter
@NoArgsConstructor @Builder
public class DashboardCustomerInfo {
    @Id
    @Column(name = "SK_ID_CURR", nullable = false)
    private Integer skIdCurr;

    @Column(name = "EXT_SOURCE_1")
    private Double extSource1;

    @Column(name = "EXT_SOURCE_2")
    private Double extSource2;

    @Column(name = "EXT_SOURCE_3")
    private Double extSource3;
}
