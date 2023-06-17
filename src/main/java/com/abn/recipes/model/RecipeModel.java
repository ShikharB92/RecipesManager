package com.abn.recipes.model;

import com.abn.recipes.common.Category;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class RecipeModel {

    private Long id;
    private String name;
    private Set<String> ingredients;
    private String instructions;
    private Category category;
    private Integer servings;
}
