package com.tomasfonta.heroes.service;

import com.tomasfonta.heroes.error.HeroNotFoudException;
import com.tomasfonta.heroes.mapper.HeroMapper;
import com.tomasfonta.heroes.model.Hero;
import com.tomasfonta.heroes.model.dto.HeroDto;
import com.tomasfonta.heroes.repository.HeroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HeroService {

    private final HeroRepository heroRepository;
    private final HeroMapper heroMapper;

    public HeroService(HeroRepository heroRepository, HeroMapper heroMapper) {
        this.heroRepository = heroRepository;
        this.heroMapper = heroMapper;
    }

    public List<HeroDto> getAll() {
        return heroRepository.findAll()
                .stream()
                .map(hero -> heroMapper.heroToHeroDto(hero))
                .collect(Collectors.toList());
    }

    @Transactional
    public HeroDto updateHero(HeroDto heroDto) throws HeroNotFoudException {
        Hero hero = heroRepository.findById(heroDto.getId()).orElseThrow(() -> new HeroNotFoudException());
        hero.setName(heroDto.getName());
        hero.setSlug(heroDto.getSlug());
        return heroMapper.heroToHeroDto(hero);
    }

    public void removeHero(Long heroId) throws HeroNotFoudException {
        Hero hero = heroRepository.findById(heroId).orElseThrow(() -> new HeroNotFoudException());
        heroRepository.delete(hero);
    }

    public HeroDto create(HeroDto heroDto) {
        return heroMapper.heroToHeroDto(heroRepository.save(heroMapper.heroDtoHero(heroDto)));
    }

    public Optional<HeroDto> findById(Long id) {
        Optional<Hero> hero = heroRepository.findById(id);
        return hero.map(h -> heroMapper.heroToHeroDto(h));
    }

    public List<HeroDto> findByNameContainingIgnoreCase(String heroName) {
        return heroMapper.heroToHeroDto(heroRepository.findByNameContainingIgnoreCase(heroName));
    }
}
