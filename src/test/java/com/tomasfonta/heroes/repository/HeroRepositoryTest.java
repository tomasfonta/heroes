package com.tomasfonta.heroes.repository;

import com.tomasfonta.heroes.model.Hero;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class HeroRepositoryTest {

    @Autowired
    private HeroRepository heroRepository;

    @AfterEach
    void clear(){
        heroRepository.deleteAll();
    }

    @Test
    void itShouldFindByNameContainingIgnoreCase() {
        Hero hero = new Hero(
                Long.valueOf(1),
                "Super Man",
                "Super");
        heroRepository.save(hero);
        List<Hero> expected = heroRepository.findByNameContainingIgnoreCase("Super");
        assertThat(expected).contains(hero);
    }

    @Test
    void itShouldNotFindByNameContainingIgnoreCase() {
        Hero hero = new Hero(
                Long.valueOf(1),
                "Super Man",
                "Super");
        final String name = "NotFound";
        List<Hero> expected = heroRepository.findByNameContainingIgnoreCase(name);
        assertThat(expected).doesNotContain(hero);
    }


}