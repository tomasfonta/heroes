package com.tomasfonta.heroes.service;

import com.tomasfonta.heroes.error.HeroNotFoudException;
import com.tomasfonta.heroes.mapper.HeroMapper;
import com.tomasfonta.heroes.mapper.HeroMapperImpl;
import com.tomasfonta.heroes.model.Hero;
import com.tomasfonta.heroes.model.dto.HeroDto;
import com.tomasfonta.heroes.repository.HeroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HeroServiceTest {

    @InjectMocks
    private HeroService heroService;

    @Mock
    private HeroRepository heroRepository;

    @Spy
    private HeroMapper heroMapper = new HeroMapperImpl();

    @Test
    void getAll() {
        heroService.getAll();
        verify(heroRepository).findAll();
    }

    @Test
    void create() {
        HeroDto heroDto = HeroDto.builder()
                .name("Superman")
                .slug("Super")
                .build();

        heroService.create(heroDto);

        ArgumentCaptor<Hero> heroArgumentCaptor =
                ArgumentCaptor.forClass(Hero.class);

        verify(heroRepository).save(heroArgumentCaptor.capture());

        Hero heroCaptured = heroArgumentCaptor.getValue();

        HeroDto heroDtoCaptured = heroMapper.heroToHeroDto(heroCaptured);

        assertThat(heroDto.getName()).isEqualTo(heroDtoCaptured.getName());
        assertThat(heroDto.getSlug()).isEqualTo(heroDtoCaptured.getSlug());
        assertThat(heroDto.getId()).isEqualTo(heroDtoCaptured.getId());
    }

    @Test
    void updateHero() throws HeroNotFoudException {

        final Long id = Long.valueOf(1);

        Hero hero = Hero.builder()
                .id(id)
                .name("Superman")
                .slug("Super")
                .build();

        Hero heroChanged = Hero.builder()
                .id(id)
                .name("Superman Changed")
                .slug("Super Changed")
                .build();

        HeroDto heroRequest = HeroDto.builder()
                .id(id)
                .name("Superman Changed")
                .slug("Super Changed")
                .build();

        when(heroRepository.findById(id)).thenReturn(Optional.of(hero));

        assertThat(heroService.updateHero(heroRequest))
                .isEqualTo(heroMapper.heroToHeroDto(heroChanged));
    }

    @Test
    void removeHero() throws HeroNotFoudException {

        Hero hero = Hero.builder()
                .id(1L)
                .name("Superman")
                .slug("Super")
                .build();

        when(heroRepository.findById(hero.getId())).thenReturn(Optional.of(hero));

        heroService.removeHero(1L);

        ArgumentCaptor<Hero> heroArgumentCaptor =
                ArgumentCaptor.forClass(Hero.class);

        verify(heroRepository).delete(heroArgumentCaptor.capture());

        assertThat(heroArgumentCaptor.getValue()).isEqualTo(hero);

    }

    @Test
    void findById() {
        //TODO
    }

    @Test
    void findByNameContainingIgnoreCase() {
        //TODO
    }
}