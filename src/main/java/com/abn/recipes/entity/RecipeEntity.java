package com.abn.recipes.entity;

import com.abn.recipes.common.Category;
import jakarta.persistence.Entity;
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
    private Category category;
    private Integer servings;
}
