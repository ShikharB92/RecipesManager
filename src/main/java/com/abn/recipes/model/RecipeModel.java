package com.abn.recipes.model;

import com.abn.recipes.common.Category;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class RecipeModel {

    private UUID id;
    private String name;
    private Set<String> ingredients;
    private String instructions;
    private Category category;
    private Integer servings;
}
