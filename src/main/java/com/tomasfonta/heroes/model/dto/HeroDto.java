package com.tomasfonta.heroes.model.dto;

import lombok.*;

import javax.annotation.Nullable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class HeroDto {
    @Nullable
    public  Long id;

    @NotBlank
    @NotNull
    @Size(min = 1, max = 50)
    public String name;
    @NotBlank
    @NotNull
    @Size(min = 1, max = 50)
    public String slug;
}