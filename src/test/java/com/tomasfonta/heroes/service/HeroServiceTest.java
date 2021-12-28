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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
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
        when(heroRepository.findAll()).thenReturn(List.of(generateHero()));
        List<HeroDto> heroes = heroService.getAll();
        verify(heroRepository).findAll();
        assertThat(heroes).isNotNull();
        assertThat(heroes).isNotEmpty();
        assertThat(heroes).contains(generateHeroDto());
    }

    @Test
    void EmptyGetAll() {
        when(heroRepository.findAll()).thenReturn(Collections.emptyList());
        List<HeroDto> heroes = heroService.getAll();
        verify(heroRepository).findAll();
        assertThat(heroes).isNotNull();
        assertThat(heroes).isEmpty();
    }

    @Test
    void create() {
        HeroDto heroDto = generateHeroDto();

        heroService.create(heroDto);

        ArgumentCaptor<Hero> heroArgumentCaptor =
                ArgumentCaptor.forClass(Hero.class);

        verify(heroRepository).save(heroArgumentCaptor.capture());
        HeroDto heroDtoCaptured = heroMapper.heroToHeroDto(heroArgumentCaptor.getValue());
        assertThat(heroDto.getName()).isEqualTo(heroDtoCaptured.getName());
        assertThat(heroDto.getSlug()).isEqualTo(heroDtoCaptured.getSlug());
        assertThat(heroDto.getId()).isEqualTo(heroDtoCaptured.getId());
    }

    @Test
    void updateHero() throws HeroNotFoudException {
        final Long id = Long.valueOf(1);
        Hero hero = generateHero();
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
    void HeroNotFoundUpdateHero() {
        HeroDto heroRequest = generateHeroDto();

        when(heroRepository.findById(heroRequest.getId())).thenReturn(Optional.empty());

        assertThatExceptionOfType(HeroNotFoudException.class)
                .isThrownBy(() -> heroService.updateHero(heroRequest));
    }

    @Test
    void removeHero() throws HeroNotFoudException {
        Hero hero = generateHero();
        when(heroRepository.findById(hero.getId())).thenReturn(Optional.of(hero));

        heroService.removeHero(1L);

        ArgumentCaptor<Hero> heroArgumentCaptor =
                ArgumentCaptor.forClass(Hero.class);
        verify(heroRepository).delete(heroArgumentCaptor.capture());
        assertThat(heroArgumentCaptor.getValue()).isEqualTo(hero);
    }

    @Test
    void HeroNotFoundRemoveHero() {
        when(heroRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatExceptionOfType(HeroNotFoudException.class)
                .isThrownBy(() -> heroService.removeHero(1L));
    }

    @Test
    void ShouldFindById() {
        Long id = Long.valueOf(1);
        Hero hero = generateHero();
        when(heroRepository.findById(id)).thenReturn(Optional.of(hero));

        Optional<Hero> heroFound = heroRepository.findById(id);

        verify(heroRepository).findById(id);
        assertThat(heroFound).isNotEmpty();
        assertThat(heroFound.get()).isEqualTo(hero);
    }

    @Test
    void EmptyFindById() {
        Long id = Long.valueOf(1);
        when(heroRepository.findById(id)).thenReturn(Optional.empty());

        Optional<HeroDto> heroFound = heroService.findById(id);
        assertThat(heroFound).isEmpty();
    }

    @Test
    void findByNameContainingIgnoreCase() {
        String search = "perm";
        Hero hero = generateHero();
        when(heroRepository.findByNameContainingIgnoreCase(search)).thenReturn(List.of(hero));

        List<HeroDto> byNameContainingIgnoreCase = heroService.findByNameContainingIgnoreCase(search);

        verify(heroRepository).findByNameContainingIgnoreCase(search);
        assertThat(byNameContainingIgnoreCase).isNotEmpty();
        assertThat(byNameContainingIgnoreCase).contains(heroMapper.heroToHeroDto(hero));
    }

    @Test
    void EmptyFindByNameContainingIgnoreCase() {
        String search = "perm";
        Hero hero = generateHero();
        when(heroRepository.findByNameContainingIgnoreCase(search)).thenReturn(Collections.emptyList());

        List<HeroDto> byNameContainingIgnoreCase = heroService.findByNameContainingIgnoreCase(search);

        verify(heroRepository).findByNameContainingIgnoreCase(search);
        assertThat(byNameContainingIgnoreCase).isEmpty();
    }

    private Hero generateHero() {
        return Hero.builder()
                .id(1L)
                .name("Superman")
                .slug("Super")
                .build();
    }

    private HeroDto generateHeroDto() {
        return HeroDto.builder()
                .id(1L)
                .name("Superman")
                .slug("Super")
                .build();
    }
}