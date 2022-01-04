package com.tomasfonta.heroes.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import lombok.*;

import javax.annotation.Nullable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class HeroDto implements Serializable {
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