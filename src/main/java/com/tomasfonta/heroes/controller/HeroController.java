package com.tomasfonta.heroes.controller;

import com.tomasfonta.heroes.error.HeroNotFoudException;
import com.tomasfonta.heroes.model.dto.HeroDto;
import com.tomasfonta.heroes.service.HeroService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/api/heroes")
public class HeroController {

    private final HeroService heroService;

    public HeroController(HeroService heroService) {
        this.heroService = heroService;
    }

    @GetMapping("/")
    @PreAuthorize("hasAuthority('HERO_READ')")
    public ResponseEntity<List<HeroDto>> getAll() {
        log.info(" ------------ getAll Heroes ------------ ");
        return new ResponseEntity(heroService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('HERO_CREATE')")
    public ResponseEntity<HeroDto> createHero(@RequestBody HeroDto heroDto) {
        log.info(" ------------ create Hero ------------ ");
        return ResponseEntity.ok(heroService.create(heroDto));
    }

    @PutMapping("/")
    @PreAuthorize("hasAuthority('HERO_UPDATE')")
    public ResponseEntity<HeroDto> updateHero(
            @RequestBody HeroDto heroDto) throws HeroNotFoudException {
        log.info(" ------------ update Hero ------------ ");
        return ResponseEntity.ok(heroService.updateHero(heroDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('HERO_DELETE')")
    public ResponseEntity removeHero(@PathVariable Long id) throws HeroNotFoudException {
        log.info(" ------------ remove Hero ------------ ");
        heroService.removeHero(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('HERO_READ')")
    public ResponseEntity<HeroDto> getHeroById(@PathVariable Long id) throws HeroNotFoudException {
        log.info(" ------------ getHeroById ------------ ");
        return ResponseEntity.ok(
                heroService.findById(id)
                        .orElseThrow(() -> new HeroNotFoudException()));
    }

    @GetMapping("/searchByName/{name}")
    @PreAuthorize("hasAuthority('HERO_READ')")
    public ResponseEntity<List<HeroDto>> findHeroByNameContainingIgnoreCase(
            @PathVariable String name) throws HeroNotFoudException {
        log.info(" ------------ findHeroByNameContaining ------------ ");
        return ResponseEntity.ok(
                heroService.findByNameContainingIgnoreCase(name));


    }

}
