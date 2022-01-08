package com.tomasfonta.heroes.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.annotation.Nullable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Data
public class HeroDto implements Serializable {
    @Nullable
    public Long id;
    @NotBlank
    @NotNull
    @Size(min = 1, max = 50)
    public String name;
    @NotBlank
    @NotNull
    @Size(min = 1, max = 50)
    public String slug;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    List<PowerStatDto> powerStats = Collections.emptyList();
}