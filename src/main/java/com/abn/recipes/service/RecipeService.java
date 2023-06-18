package com.abn.recipes.service;

import com.abn.recipes.entity.RecipeEntity;
import com.abn.recipes.model.RecipeModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface RecipeService {

    Page<RecipeModel> getAllRecipes(Pageable pageable);

    RecipeModel saveRecipe(RecipeModel recipeModel);

    RecipeModel getRecipe(Long id);

    void deleteRecipe(Long id);

    RecipeModel updateRecipe(Long id, RecipeModel recipeModel);

    Page<RecipeModel> findBySearchCriteria(Specification<RecipeEntity> spec, Pageable page);
}
