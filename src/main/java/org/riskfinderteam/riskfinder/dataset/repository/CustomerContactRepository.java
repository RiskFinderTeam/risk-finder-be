package org.riskfinderteam.riskfinder.dataset.repository;

import org.riskfinderteam.riskfinder.dataset.entity.CustomerContact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerContactRepository extends CrudRepository<CustomerContact, Long> {
}
