package com.tomasfonta.heroes.mapper;

import com.tomasfonta.heroes.model.Hero;
import com.tomasfonta.heroes.model.dto.HeroDto;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class HeroMapperTest {

    private HeroMapper heroMapper;

    @BeforeEach
    public void setUp() {
        heroMapper = new HeroMapperImpl();
    }

    @Test
    void CorrectHeroToHeroDto() {
        Hero hero = generateHero();

        HeroDto heroDto = heroMapper.heroToHeroDto(hero);

        assertThat(heroDto.getId()).isEqualTo(hero.getId());
        assertThat(heroDto.getName()).isEqualTo(hero.getName());
        assertThat(heroDto.getSlug()).isEqualTo(hero.getSlug());
    }

    @Test
    void CorrectHeroToHeroDtoList() {
        List<Hero> heroesList = generateHeroList();

        List<HeroDto> heroDtoList = heroMapper.heroToHeroDto(heroesList);

        assertThat(heroDtoList).isNotNull();
        assertThat(heroDtoList).isNotEmpty();
        assertThat(heroDtoList).contains(generateHeroDto());
    }

    @Test
    void IncorrectHeroToHeroDtoList() {
        List<Hero> heroesList = Collections.emptyList();

        List<HeroDto> heroDtoList = heroMapper.heroToHeroDto(heroesList);

        assertThat(heroDtoList).isNotNull();
        assertThat(heroDtoList).isEmpty();
    }

    @Test
    void IncorrectHeroToHeroDto() {
        Hero hero = new Hero();

        HeroDto heroMapped = heroMapper.heroToHeroDto(hero);

        assertThat(heroMapped.getId()).isNull();
        assertThat(heroMapped.getName()).isNull();
        assertThat(heroMapped.getSlug()).isNull();
    }

    @Test
    void CorrectHeroDtoHero() {
        HeroDto heroDto = generateHeroDto();

        Hero hero = heroMapper.heroDtoHero(heroDto);

        assertThat(hero.getId()).isEqualTo(heroDto.getId());
        assertThat(hero.getName()).isEqualTo(heroDto.getName());
        assertThat(hero.getSlug()).isEqualTo(heroDto.getSlug());
    }

    @Test
    void IncorrectTHeroToHeroDto() {
        HeroDto heroDto = new HeroDto();

        Hero hero = heroMapper.heroDtoHero(heroDto);

        assertThat(hero.getId()).isNull();
        assertThat(hero.getName()).isNull();
        assertThat(hero.getSlug()).isNull();
    }

    private HeroDto generateHeroDto() {
        return HeroDto.builder()
                .id(1l)
                .name("Superman")
                .slug("Superman Slug")
                .build();
    }

    private Hero generateHero() {
        return Hero.builder()
                .id(1l)
                .name("Superman")
                .slug("Superman Slug")
                .build();
    }

    private List<Hero> generateHeroList() {
        return List.of(Hero.builder()
                        .id(1l)
                        .name("Superman")
                        .slug("Superman Slug")
                        .build(),
                Hero.builder()
                        .id(2l)
                        .name("Batman")
                        .slug("Batman Slug")
                        .build());
    }
}