package org.riskfinderteam.riskfinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RiskFinderBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(RiskFinderBeApplication.class, args);
    }

}
