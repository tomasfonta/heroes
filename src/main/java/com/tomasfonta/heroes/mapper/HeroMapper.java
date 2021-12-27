package com.tomasfonta.heroes.mapper;

import com.tomasfonta.heroes.model.Hero;
import com.tomasfonta.heroes.model.dto.HeroDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HeroMapper {
    /**
     * Maps a Hero to a HeroDto Object
     *
     * @param hero
     * @return HeroDto
     */
    HeroDto heroToHeroDto(Hero hero);

    /**
     * Maps a HeroDto to a Hero Object
     *
     * @param heroDto
     * @return Hero
     */
    Hero heroDtoHero(HeroDto heroDto);

    List<HeroDto> heroToHeroDto(List<Hero> heroList);

}
