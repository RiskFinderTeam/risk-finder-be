package org.riskfinderteam.riskfinder.dataset.repository;

import org.riskfinderteam.riskfinder.dataset.entity.CustomerInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerInfoRepository extends CrudRepository<CustomerInfo, Integer> {
}
