package com.tomasfonta.heroes.repository;

import com.tomasfonta.heroes.model.PowerStat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PowerStatRepository extends JpaRepository<PowerStat, Long> {
}
