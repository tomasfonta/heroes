package com.tomasfonta.heroes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@JsonSerializableSchema
@Entity
@Table(name = "power_stats")
public class PowerStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int level;

    @ManyToOne
    @JoinColumn(name = "hero_id", nullable = false)
    @JsonIgnore
    Hero hero;

}
