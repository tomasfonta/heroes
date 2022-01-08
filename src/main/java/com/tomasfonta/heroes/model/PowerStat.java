package com.tomasfonta.heroes.model;

import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "power_stats")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSerializableSchema
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class PowerStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int level;

    @ManyToOne
    @JoinColumn(name = "hero_id", nullable = false)
    Hero hero;

}
