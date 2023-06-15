package com.abn.recipes.service;

import com.abn.recipes.mapper.RecipeEntityToModelTransformer;
import com.abn.recipes.model.RecipeModel;
import com.abn.recipes.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeEntityToModelTransformer recipeEntityToModelTransformer;

    @Override
    public RecipeModel createRecipe(RecipeModel recipeModel) {
        return null;
    }

    @Override
    public Page<RecipeModel> getAllRecipes(Pageable pageable) {
        return recipeRepository.findAll(pageable).map(recipeEntityToModelTransformer);
    }
}
