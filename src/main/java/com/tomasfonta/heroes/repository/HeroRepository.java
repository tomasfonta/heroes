package com.tomasfonta.heroes.repository;

import com.tomasfonta.heroes.model.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Long> {
    List<Hero> findByNameContainingIgnoreCase(String heroName);

    /* Use only as example to log when call repository from service*/
    default public Optional<Hero> findByHeroId(Long id)  {
        System.out.println("Call repository to get id=" + id);
        return this.findById(id);
    }

}
