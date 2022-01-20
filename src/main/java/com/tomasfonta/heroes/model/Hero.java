package com.tomasfonta.heroes.model;

import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonSerializableSchema
@ToString
@Entity
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

    @OneToMany(mappedBy = "hero")
    @Singular
    @ToString.Exclude
    List<PowerStat> powerStats;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Hero hero = (Hero) o;
        return id != null && Objects.equals(id, hero.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

