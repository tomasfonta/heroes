package com.tomasfonta.heroes.controller;

import com.tomasfonta.heroes.config.aspect.LogElapsedTimeConfig;
import com.tomasfonta.heroes.error.HeroNotFoundException;
import com.tomasfonta.heroes.model.dto.HeroDto;
import com.tomasfonta.heroes.service.HeroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/heroes")
@RequiredArgsConstructor

public class HeroController {

    private final HeroService heroService;

    @GetMapping("/")
    @PreAuthorize("hasAuthority('HERO_READ')")
    @LogElapsedTimeConfig
    @Operation(summary = "Get All Heroes")
    @ApiResponse(responseCode = "200", description = "Found Heroes",
            content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = HeroDto.class)))})
    public ResponseEntity<List<HeroDto>> getAll() {
        return new ResponseEntity(heroService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('HERO_CREATE')")
    @LogElapsedTimeConfig
    @Operation(summary = "Save New Hero")
    @ApiResponse(responseCode = "200", description = "Hero Created",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = HeroDto.class))})
    public ResponseEntity<HeroDto> createHero(@RequestBody HeroDto heroDto) {
        return ResponseEntity.ok(heroService.create(heroDto));
    }

    @PutMapping("/")
    @PreAuthorize("hasAuthority('HERO_UPDATE')")
    @LogElapsedTimeConfig
    @Operation(summary = "Update Hero")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hero Updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = HeroDto.class))}),
            @ApiResponse(responseCode = "404", description = "Hero Not Found",
                    content = @Content)})
    public ResponseEntity<HeroDto> updateHero(
            @RequestBody HeroDto heroDto) throws HeroNotFoundException {
        return ResponseEntity.ok(heroService.updateHero(heroDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('HERO_DELETE')")
    @LogElapsedTimeConfig
    @Operation(summary = "Remove Hero")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Hero Removed",
                    content = {@Content}),
            @ApiResponse(responseCode = "404", description = "Hero Not Found",
                    content = @Content)})
    public ResponseEntity removeHero(@PathVariable Long id) throws HeroNotFoundException {
        heroService.removeHero(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('HERO_READ')")
    @LogElapsedTimeConfig
    @Operation(summary = "Get Hero By Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hero Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = HeroDto.class))),
            @ApiResponse(responseCode = "404", description = "Hero Not Found",
                    content = @Content)})
    public ResponseEntity<HeroDto> getHeroById(@PathVariable Long id)
            throws HeroNotFoundException {
        return ResponseEntity.ok(heroService.findById(id));

    }

    @GetMapping("/searchByName/{name}")
    @PreAuthorize("hasAuthority('HERO_READ')")
    @LogElapsedTimeConfig
    @Operation(summary = "Search Hero by containing Name",
            description = "This request will return all heroes that contain " +
                    "the search parameter in his name, note that the search will " +
                    "IGNORE CASE of the search parameter and the hero name.")
    @ApiResponse(responseCode = "200",
            description = "Hero Found",
            content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = HeroDto.class)))})
    public ResponseEntity<List<HeroDto>> findHeroByNameContainingIgnoreCase(
            @PathVariable String name) {
        return ResponseEntity.ok(heroService.findByNameContainingIgnoreCase(name));
    }

}
