package com.tomasfonta.heroes.model.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder

public class PowerStatDto implements Serializable {

    public Long id;
    public String name;
    public int level;
    public Long heroId;

}
