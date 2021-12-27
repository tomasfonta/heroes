package com.tomasfonta.heroes.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
@EqualsAndHashCode
public class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    @NotBlank
    @Size(min = 1, max = 50)
    private String name;
    @Column
    @NotBlank
    @Size(min = 1, max = 50)
    private String slug;

}
