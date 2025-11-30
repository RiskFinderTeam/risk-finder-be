package org.riskfinderteam.riskfinder.dataset.repository;

import org.riskfinderteam.riskfinder.dataset.entity.CustomerContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerContactRepository extends JpaRepository<CustomerContact, Long> {
}
