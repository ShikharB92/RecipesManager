package com.abn.recipes.mapper;

import com.abn.recipes.entity.RecipeEntity;
import com.abn.recipes.model.RecipeModel;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class RecipeEntityToModelTransformer implements Function<RecipeEntity, RecipeModel> {

            @Override
            public RecipeModel apply(RecipeEntity recipeEntity) {
                return RecipeModel.builder()
                        .id(recipeEntity.getId())
                        .name(recipeEntity.getName())
                        .ingredients(recipeEntity.getIngredients())
                        .instructions(recipeEntity.getInstructions())
                        .category(recipeEntity.getCategory())
                        .servings(recipeEntity.getServings())
                        .build();
            }
}
