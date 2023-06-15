package com.abn.recipes.service;

import com.abn.recipes.model.RecipeModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecipeService {

    RecipeModel createRecipe(RecipeModel recipeModel);

    Page<RecipeModel> getAllRecipes(Pageable pageable);
}
