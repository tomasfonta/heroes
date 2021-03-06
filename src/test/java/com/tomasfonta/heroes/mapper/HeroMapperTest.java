package com.tomasfonta.heroes.mapper;

import com.tomasfonta.heroes.model.Hero;
import com.tomasfonta.heroes.model.PowerStat;
import com.tomasfonta.heroes.model.dto.HeroDto;
import com.tomasfonta.heroes.model.dto.PowerStatDto;
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
        Hero hero = generateHero(1l, "Superman", "Superman");

        HeroDto heroDto = heroMapper.heroToHeroDto(hero);

        assertThat(heroDto.getId()).isEqualTo(hero.getId());
        assertThat(heroDto.getName()).isEqualTo(hero.getName());
        assertThat(heroDto.getSlug()).isEqualTo(hero.getSlug());
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
    void NullHeroToHeroDto() {
        HeroDto heroMapped = heroMapper.heroToHeroDto((Hero) null);
        assertThat(heroMapped).isNull();
    }

    @Test
    void CorrectHeroToHeroDtoList() {
        List<Hero> heroesList = generateHeroList();

        List<HeroDto> heroDtoList = heroMapper.heroToHeroDto(heroesList);

        assertThat(heroDtoList).isNotNull();
        assertThat(heroDtoList).isNotEmpty();
        assertThat(heroDtoList).contains(generateHeroDto(1l, "Superman", "Super"));

    }

    @Test
    void EmptyHeroToHeroDtoList() {
        List<Hero> heroesList = Collections.emptyList();

        List<HeroDto> heroDtoList = heroMapper.heroToHeroDto(heroesList);

        assertThat(heroDtoList).isNotNull();
        assertThat(heroDtoList).isEmpty();
    }

    @Test
    void NullHeroToHeroDtoList() {
        List<HeroDto> heroDtoList = heroMapper.heroToHeroDto((List<Hero>) null);
        assertThat(heroDtoList).isNull();
    }

    @Test
    void CorrectHeroDtoHero() {
        HeroDto heroDto = generateHeroDto(1l, "Superman", "Super");

        Hero hero = heroMapper.heroDtoHero(heroDto);

        assertThat(hero.getId()).isEqualTo(heroDto.getId());
        assertThat(hero.getName()).isEqualTo(heroDto.getName());
        assertThat(hero.getSlug()).isEqualTo(heroDto.getSlug());
    }

    @Test
    void IncorrectTHeroDtoToHero() {
        HeroDto heroDto = new HeroDto();

        Hero hero = heroMapper.heroDtoHero(heroDto);

        assertThat(hero.getId()).isNull();
        assertThat(hero.getName()).isNull();
        assertThat(hero.getSlug()).isNull();
    }

    @Test
    void NullHeroDtoToHero() {
        Hero hero = heroMapper.heroDtoHero(null);
        assertThat(hero).isNull();
    }

    private HeroDto generateHeroDto(Long id, String name, String slug) {
        return HeroDto.builder()
                .id(id)
                .name(name)
                .slug(slug)
                .powerStats(List.of(new PowerStatDto(1L, "Power", 100, 1l)))
                .build();
    }

    private Hero generateHero(Long id, String name, String slug) {
        Hero hero = Hero.builder()
                .id(id)
                .name(name)
                .slug(slug)
                .powerStats(Collections.emptyList())
                .build();

        hero.setPowerStats(List.of(new PowerStat(id, "Power", 100, hero)));
        return hero;
    }

    private List<Hero> generateHeroList() {
        return List.of(generateHero(1l, "Superman", "Super"),
                generateHero(2l, "SpiderMan", "Spider"));
    }
}