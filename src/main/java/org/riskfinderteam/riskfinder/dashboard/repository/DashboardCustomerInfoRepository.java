package org.riskfinderteam.riskfinder.dashboard.repository;

import org.riskfinderteam.riskfinder.dashboard.dto.CustomerExtSourceDataDto;
import org.riskfinderteam.riskfinder.dashboard.entity.DashboardCustomerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DashboardCustomerInfoRepository extends JpaRepository<DashboardCustomerInfo, Integer>{
    @Query("""
        SELECT new org.riskfinderteam.riskfinder.dashboard.dto.CustomerExtSourceDataDto(
            AVG(c.extSource1),
            AVG(c.extSource2),
            AVG(c.extSource3)
        )
        FROM DashboardCustomerInfo c
    """)
    CustomerExtSourceDataDto getExtSourceAverages();
}
