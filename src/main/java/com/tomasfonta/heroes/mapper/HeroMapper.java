package com.tomasfonta.heroes.mapper;

import com.tomasfonta.heroes.model.Hero;
import com.tomasfonta.heroes.model.PowerStat;
import com.tomasfonta.heroes.model.dto.HeroDto;
import com.tomasfonta.heroes.model.dto.PowerStatDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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

    @Mapping(target = "heroId", source = "hero.id")
    PowerStatDto powerStatToPowerStatDto(PowerStat powerStat);

    @Mapping(target = "hero.id", source = "heroId")
    PowerStat powerStatDtoToPowerStat(PowerStatDto powerStatDto);

}
