package org.riskfinderteam.riskfinder.dataset.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "customer_docs")
@AllArgsConstructor
@Getter @Setter
@NoArgsConstructor @Builder
public class CustomerDocs {
    @Id
    @Column(name = "SK_ID_CURR", nullable = false)
    private String skIdCurr;

    @Column(name = "OBS_30_CNT_SOCIAL_CIRCLE", precision = 10, scale = 2)
    private BigDecimal obs30CntSocialCircle;

    @Column(name = "DEF_30_CNT_SOCIAL_CIRCLE", precision = 10, scale = 2)
    private BigDecimal def30CntSocialCircle;

    @Column(name = "OBS_60_CNT_SOCIAL_CIRCLE", precision = 10, scale = 2)
    private BigDecimal obs60CntSocialCircle;

    @Column(name = "DEF_60_CNT_SOCIAL_CIRCLE", precision = 10, scale = 2)
    private BigDecimal def60CntSocialCircle;

    @Column(name = "FLAG_DOCUMENT_2")
    private String flagDocument2;

    @Column(name = "FLAG_DOCUMENT_3")
    private String flagDocument3;

    @Column(name = "FLAG_DOCUMENT_4")
    private String flagDocument4;

    @Column(name = "FLAG_DOCUMENT_5")
    private String flagDocument5;

    @Column(name = "FLAG_DOCUMENT_6")
    private String flagDocument6;

    @Column(name = "FLAG_DOCUMENT_7")
    private String flagDocument7;

    @Column(name = "FLAG_DOCUMENT_8")
    private String flagDocument8;

    @Column(name = "FLAG_DOCUMENT_9")
    private String flagDocument9;

    @Column(name = "FLAG_DOCUMENT_10")
    private String flagDocument10;

    @Column(name = "FLAG_DOCUMENT_11")
    private String flagDocument11;

    @Column(name = "FLAG_DOCUMENT_12")
    private String flagDocument12;
    @Column(name = "FLAG_DOCUMENT_13")
    private String flagDocument13;

    @Column(name = "FLAG_DOCUMENT_14")
    private String flagDocument14;

    @Column(name = "FLAG_DOCUMENT_15")
    private String flagDocument15;

    @Column(name = "FLAG_DOCUMENT_16")
    private String flagDocument16;

    @Column(name = "FLAG_DOCUMENT_17")
    private String flagDocument17;

    @Column(name = "FLAG_DOCUMENT_18")
    private String flagDocument18;

    @Column(name = "FLAG_DOCUMENT_19")
    private String flagDocument19;

    @Column(name = "FLAG_DOCUMENT_20")
    private String flagDocument20;

    @Column(name = "FLAG_DOCUMENT_21")
    private String flagDocument21;
}
