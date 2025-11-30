package org.riskfinderteam.riskfinder.dataset.repository;

import org.riskfinderteam.riskfinder.dataset.entity.CustomerData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDataRepository extends JpaRepository<CustomerData, Long> {
}
