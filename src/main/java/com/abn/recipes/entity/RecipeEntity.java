package com.abn.recipes.entity;

import com.abn.recipes.common.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class RecipeEntity extends AbstractBaseEntity {

    private String name;
    private Set<String> ingredients;
    private String instructions;

    @Enumerated(EnumType.STRING)
    private Category category;
    private Integer servings;
}
