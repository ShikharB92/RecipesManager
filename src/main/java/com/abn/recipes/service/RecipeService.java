package com.abn.recipes.service;

import com.abn.recipes.model.RecipeModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecipeService {

    Page<RecipeModel> getAllRecipes(Pageable pageable);

    RecipeModel saveRecipe(RecipeModel recipeModel);

    RecipeModel getRecipe(Long id);

    void deleteRecipe(Long id);

    RecipeModel updateRecipe(Long id, RecipeModel recipeModel);

    
}
