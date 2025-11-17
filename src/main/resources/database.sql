-- Users 테이블
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`
(
    `id`       BIGINT       NOT NULL AUTO_INCREMENT,
    `email`    VARCHAR(100) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `role`     VARCHAR(20)  NOT NULL,
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `loan_main`;
CREATE TABLE `loan_main`
(
    `SK_ID_CURR`                 INT            NOT NULL,
    `NAME_CONTRACT_TYPE`         VARCHAR(50)    NULL,
    `AMT_CREDIT`                 DECIMAL(18, 2) NULL,
    `AMT_ANNUITY`                DECIMAL(18, 2) NULL,
    `AMT_GOODS_PRICE`            DECIMAL(18, 2) NULL,
    `WEEKDAY_APPR_PROCESS_START` VARCHAR(20)    NULL,
    `HOUR_APPR_PROCESS_START`    INT            NULL,
    `AMT_REQ_CREDIT_BUREAU_HOUR` INT            NULL,
    `AMT_REQ_CREDIT_BUREAU_DAY`  INT            NULL,
    `AMT_REQ_CREDIT_BUREAU_WEEK` INT            NULL,
    `AMT_REQ_CREDIT_BUREAU_MON`  INT            NULL,
    `AMT_REQ_CREDIT_BUREAU_QRT`  INT            NULL,
    `AMT_REQ_CREDIT_BUREAU_YEAR` INT            NULL,
    PRIMARY KEY (`SK_ID_CURR`)
);

DROP TABLE IF EXISTS `customer_contact`;
CREATE TABLE `customer_contact`
(
    `SK_ID_CURR`             INT NOT NULL,
    `DAYS_REGISTRATION`      INT NULL,
    `DAYS_ID_PUBLISH`        INT NULL,
    `DAYS_LAST_PHONE_CHANGE` INT NULL,
    `FLAG_MOBIL`             INT NULL,
    `FLAG_EMP_PHONE`         INT NULL,
    `FLAG_WORK_PHONE`        INT NULL,
    `FLAG_CONT_MOBILE`       INT NULL,
    `FLAG_PHONE`             INT NULL,
    `FLAG_EMAIL`             INT NULL,
    PRIMARY KEY (`SK_ID_CURR`)
);

DROP TABLE IF EXISTS `customer_info`;
CREATE TABLE `customer_info`
(
    `SK_ID_CURR`          INT            NOT NULL,
    `CODE_GENDER`         VARCHAR(5)     NULL,
    `FLAG_OWN_CAR`        CHAR(1)        NULL,
    `FLAG_OWN_REALTY`     CHAR(1)        NULL,
    `OWN_CAR_AGE`         INT            NULL,
    `CNT_CHILDREN`        INT            NULL,
    `CNT_FAM_MEMBERS`     DECIMAL(10, 2) NULL,
    `NAME_FAMILY_STATUS`  VARCHAR(50)    NULL,
    `NAME_EDUCATION_TYPE` VARCHAR(50)    NULL,
    `NAME_INCOME_TYPE`    VARCHAR(50)    NULL,
    `NAME_HOUSING_TYPE`   VARCHAR(50)    NULL,
    `NAME_TYPE_SUITE`     VARCHAR(50)    NULL,
    `OCCUPATION_TYPE`     VARCHAR(50)    NULL,
    `ORGANIZATION_TYPE`   VARCHAR(50)    NULL,
    `AMT_INCOME_TOTAL`    DECIMAL(18, 2) NULL,
    `DAYS_BIRTH`          INT            NULL,
    `DAYS_EMPLOYED`       INT            NULL,
    `EXT_SOURCE_1`        DOUBLE         NULL,
    `EXT_SOURCE_2`        DOUBLE         NULL,
    `EXT_SOURCE_3`        DOUBLE         NULL,
    PRIMARY KEY (`SK_ID_CURR`)
);

DROP TABLE IF EXISTS `customer_docs`;
CREATE TABLE `customer_docs`
(
    `SK_ID_CURR`               INT            NOT NULL,
    `OBS_30_CNT_SOCIAL_CIRCLE` DECIMAL(10, 2) NULL,
    `DEF_30_CNT_SOCIAL_CIRCLE` DECIMAL(10, 2) NULL,
    `OBS_60_CNT_SOCIAL_CIRCLE` DECIMAL(10, 2) NULL,
    `DEF_60_CNT_SOCIAL_CIRCLE` DECIMAL(10, 2) NULL,
    `FLAG_DOCUMENT_2`          INT            NULL,
    `FLAG_DOCUMENT_3`          INT            NULL,
    `FLAG_DOCUMENT_4`          INT            NULL,
    `FLAG_DOCUMENT_5`          INT            NULL,
    `FLAG_DOCUMENT_6`          INT            NULL,
    `FLAG_DOCUMENT_7`          INT            NULL,
    `FLAG_DOCUMENT_8`          INT            NULL,
    `FLAG_DOCUMENT_9`          INT            NULL,
    `FLAG_DOCUMENT_10`         INT            NULL,
    `FLAG_DOCUMENT_11`         INT            NULL,
    `FLAG_DOCUMENT_12`         INT            NULL,
    `FLAG_DOCUMENT_13`         INT            NULL,
    `FLAG_DOCUMENT_14`         INT            NULL,
    `FLAG_DOCUMENT_15`         INT            NULL,
    `FLAG_DOCUMENT_16`         INT            NULL,
    `FLAG_DOCUMENT_17`         INT            NULL,
    `FLAG_DOCUMENT_18`         INT            NULL,
    `FLAG_DOCUMENT_19`         INT            NULL,
    `FLAG_DOCUMENT_20`         INT            NULL,
    `FLAG_DOCUMENT_21`         INT            NULL,
    PRIMARY KEY (`SK_ID_CURR`)
);

DROP TABLE IF EXISTS `customer_residence`;
CREATE TABLE `customer_residence`
(
    `SK_ID_CURR`                   INT            NOT NULL,
    `REGION_POPULATION_RELATIVE`   DECIMAL(10, 6) NULL,
    `REGION_RATING_CLIENT`         INT            NULL,
    `REGION_RATING_CLIENT_W_CITY`  INT            NULL,
    `REG_REGION_NOT_LIVE_REGION`   INT            NULL,
    `REG_REGION_NOT_WORK_REGION`   INT            NULL,
    `LIVE_REGION_NOT_WORK_REGION`  INT            NULL,
    `REG_CITY_NOT_LIVE_CITY`       INT            NULL,
    `REG_CITY_NOT_WORK_CITY`       INT            NULL,
    `LIVE_CITY_NOT_WORK_CITY`      INT            NULL,
    `APARTMENTS_AVG`               DECIMAL(10, 6) NULL,
    `APARTMENTS_MODE`              DECIMAL(10, 6) NULL,
    `APARTMENTS_MEDI`              DECIMAL(10, 6) NULL,
    `BASEMENTAREA_AVG`             DECIMAL(10, 6) NULL,
    `BASEMENTAREA_MODE`            DECIMAL(10, 6) NULL,
    `BASEMENTAREA_MEDI`            DECIMAL(10, 6) NULL,
    `YEARS_BEGINEXPLUATATION_AVG`  DECIMAL(10, 6) NULL,
    `YEARS_BEGINEXPLUATATION_MODE` DECIMAL(10, 6) NULL,
    `YEARS_BEGINEXPLUATATION_MEDI` DECIMAL(10, 6) NULL,
    `YEARS_BUILD_AVG`              DECIMAL(10, 6) NULL,
    `YEARS_BUILD_MODE`             DECIMAL(10, 6) NULL,
    `YEARS_BUILD_MEDI`             DECIMAL(10, 6) NULL,
    `COMMONAREA_AVG`               DECIMAL(10, 6) NULL,
    `COMMONAREA_MODE`              DECIMAL(10, 6) NULL,
    `COMMONAREA_MEDI`              DECIMAL(10, 6) NULL,
    `ELEVATORS_AVG`                DECIMAL(10, 6) NULL,
    `ELEVATORS_MODE`               DECIMAL(10, 6) NULL,
    `ELEVATORS_MEDI`               DECIMAL(10, 6) NULL,
    `ENTRANCES_AVG`                DECIMAL(10, 6) NULL,
    `ENTRANCES_MODE`               DECIMAL(10, 6) NULL,
    `ENTRANCES_MEDI`               DECIMAL(10, 6) NULL,
    `FLOORSMAX_AVG`                DECIMAL(10, 6) NULL,
    `FLOORSMAX_MODE`               DECIMAL(10, 6) NULL,
    `FLOORSMAX_MEDI`               DECIMAL(10, 6) NULL,
    `FLOORSMIN_AVG`                DECIMAL(10, 6) NULL,
    `FLOORSMIN_MODE`               DECIMAL(10, 6) NULL,
    `FLOORSMIN_MEDI`               DECIMAL(10, 6) NULL,
    `LANDAREA_AVG`                 DECIMAL(10, 6) NULL,
    `LANDAREA_MODE`                DECIMAL(10, 6) NULL,
    `LANDAREA_MEDI`                DECIMAL(10, 6) NULL,
    `LIVINGAPARTMENTS_AVG`         DECIMAL(10, 6) NULL,
    `LIVINGAPARTMENTS_MODE`        DECIMAL(10, 6) NULL,
    `LIVINGAPARTMENTS_MEDI`        DECIMAL(10, 6) NULL,
    `LIVINGAREA_AVG`               DECIMAL(10, 6) NULL,
    `LIVINGAREA_MODE`              DECIMAL(10, 6) NULL,
    `LIVINGAREA_MEDI`              DECIMAL(10, 6) NULL,
    `NONLIVINGAPARTMENTS_AVG`      DECIMAL(10, 6) NULL,
    `NONLIVINGAPARTMENTS_MODE`     DECIMAL(10, 6) NULL,
    `NONLIVINGAPARTMENTS_MEDI`     DECIMAL(10, 6) NULL,
    `NONLIVINGAREA_AVG`            DECIMAL(10, 6) NULL,
    `NONLIVINGAREA_MODE`           DECIMAL(10, 6) NULL,
    `NONLIVINGAREA_MEDI`           DECIMAL(10, 6) NULL,
    `FONDKAPREMONT_MODE`           VARCHAR(50)    NULL,
    `HOUSETYPE_MODE`               VARCHAR(50)    NULL,
    `TOTALAREA_MODE`               DECIMAL(10, 6) NULL,
    `WALLSMATERIAL_MODE`           VARCHAR(50)    NULL,
    `EMERGENCYSTATE_MODE`          VARCHAR(50)    NULL,
    PRIMARY KEY (`SK_ID_CURR`)
);

DROP TABLE IF EXISTS `scoring_results`;
CREATE TABLE `scoring_results`
(
    `SK_ID_CURR`    INT           NOT NULL,
    `SCORED_AT`     TIMESTAMP     NOT NULL,
    `SCORE`         DECIMAL(5, 4) NOT NULL,
    `GRADE`         VARCHAR(20)   NOT NULL,
    `TOP3_FEATURES` TEXT          NULL,
    primary key (`SK_ID_CURR`)
);

DROP TABLE IF EXISTS `score_history`;
CREATE TABLE `score_history`
(
    `SK_ID_CURR`    INT           NOT NULL,
    `HISTORY_ID`    INT           NOT NULL,
    `SCORE`         DECIMAL(5, 4) NOT NULL,
    `GRADE`         VARCHAR(20)   NOT NULL,
    `TOP3_FEATURES` TEXT          NULL,
    `SCORED_AT`     DATETIME      NOT NULL,
    primary key (`SK_ID_CURR`)
);