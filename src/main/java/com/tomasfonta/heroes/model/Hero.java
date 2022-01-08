package com.tomasfonta.heroes.model;

import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
@JsonSerializableSchema
@ToString
@EqualsAndHashCode
@Table(name = "heroes")
public class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank
    @Size(min = 1, max = 50)
    private String name;

    @Column
    @NotBlank
    @Size(min = 1, max = 50)
    private String slug;

    @OneToMany(
            mappedBy = "hero",
            cascade = CascadeType.MERGE,
            orphanRemoval = true)
    @Singular
    List<PowerStat> powerStats;
}

