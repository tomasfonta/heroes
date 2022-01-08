package com.tomasfonta.heroes.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class PowerStatDto {

    public Long id;
    public String name;
    public int level;
    public Long heroId;

}
