package com.tomasfonta.heroes.service;

import com.tomasfonta.heroes.config.cache.RedisConfig;
import com.tomasfonta.heroes.error.HeroNotFoundException;
import com.tomasfonta.heroes.error.PowerStatNotFoundExpetion;
import com.tomasfonta.heroes.error.ValidationException;
import com.tomasfonta.heroes.error.ValidationType;
import com.tomasfonta.heroes.mapper.HeroMapper;
import com.tomasfonta.heroes.model.Hero;
import com.tomasfonta.heroes.model.PowerStat;
import com.tomasfonta.heroes.model.dto.HeroDto;
import com.tomasfonta.heroes.model.dto.PowerStatDto;
import com.tomasfonta.heroes.repository.HeroRepository;
import com.tomasfonta.heroes.repository.PowerStatRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HeroService {

    private final HeroRepository heroRepository;
    private final PowerStatRepository powerStatRepository;
    private final HeroMapper heroMapper;

    public HeroService(HeroRepository heroRepository,
                       PowerStatRepository powerStatRepository,
                       HeroMapper heroMapper) {
        this.heroRepository = heroRepository;
        this.powerStatRepository = powerStatRepository;
        this.heroMapper = heroMapper;
    }

    public List<HeroDto> getAll() {
        return heroRepository.findAll()
                .stream()
                .map(heroMapper::heroToHeroDto)
                .collect(Collectors.toList());
    }

    @CachePut(cacheNames = RedisConfig.HERO_CACHE, key = "#heroDto.id", unless = "#result == null")
    @Transactional
    public HeroDto updateHero(HeroDto heroDto) throws HeroNotFoundException {
        if (heroDto == null || heroDto.getId() == null) {
            throw new ValidationException(ValidationType.BADREQUEST, HttpStatus.BAD_REQUEST, "Missing UserId Parameter.");
        }
        Hero hero = heroRepository.findById(heroDto.getId())
                .orElseThrow(() -> new HeroNotFoundException(
                        String.format("Hero with ID: %s Not Found.", heroDto.getId())));
        hero.setName(heroDto.getName());
        hero.setSlug(heroDto.getSlug());
        hero.setPowerStats(
                heroDto.getPowerStats()
                        .stream()
                        .map(this::updatePowerStats)
                        .map(heroMapper::powerStatDtoToPowerStat).collect(Collectors.toList()));
        return heroMapper.heroToHeroDto(hero);
    }

    @CacheEvict(cacheNames = RedisConfig.HERO_CACHE, key = "#heroId")
    public void removeHero(Long heroId) throws HeroNotFoundException {
        Hero hero = heroRepository.findById(heroId)
                .orElseThrow(() -> new HeroNotFoundException(
                        String.format("Hero with ID: %s Not Found.", heroId)));
        heroRepository.delete(hero);
    }

    @Transactional
    public HeroDto create(HeroDto heroDto) {
        Hero hero = heroMapper.heroDtoHero(heroDto);
        heroDto = heroMapper.heroToHeroDto(heroRepository.save(hero));

        heroDto.setPowerStats(hero.getPowerStats()
                .stream()
                .map(powerStat -> {
                    powerStat.setHero(hero);
                    return heroMapper.powerStatToPowerStatDto(powerStatRepository.save(powerStat));
                }).collect(Collectors.toList()));
        return heroDto;
    }

    @Cacheable(cacheNames = RedisConfig.HERO_CACHE, unless = "#result == null")
    public HeroDto findById(Long id) throws HeroNotFoundException {
        Hero hero = heroRepository.findById(id)
                .orElseThrow(() -> new HeroNotFoundException(String.format("Hero with Id:%s Not Found.", id)));
        return heroMapper.heroToHeroDto(hero);
    }

    public List<HeroDto> findByNameContainingIgnoreCase(@NotNull String heroName) {
        return heroMapper.heroToHeroDto(heroRepository.findByNameContainingIgnoreCase(heroName));
    }


    private PowerStatDto updatePowerStats(PowerStatDto powerStatDto) {
        PowerStat powerStat = powerStatRepository.findById(powerStatDto.getId())
                .orElseThrow(
                        () -> new PowerStatNotFoundExpetion(String.format("Power Stat with id: %s not found", powerStatDto.getId())));
        powerStat.setName(powerStatDto.getName());
        powerStat.setLevel(powerStatDto.getLevel());
        powerStat.setHero(heroRepository.getById(powerStatDto.getHeroId()));
        return heroMapper.powerStatToPowerStatDto(powerStat);
    }

}
