package com.abn.recipes.mapper;

import com.abn.recipes.entity.RecipeEntity;
import com.abn.recipes.model.RecipeModel;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class RecipeModelToEntityTransformer implements Function<RecipeModel, RecipeEntity> {

        @Override
        public RecipeEntity apply(RecipeModel recipeModel) {
            return RecipeEntity.builder()
                    .name(recipeModel.getName())
                    .ingredients(recipeModel.getIngredients())
                    .instructions(recipeModel.getInstructions())
                    .category(recipeModel.getCategory())
                    .servings(recipeModel.getServings())
                    .build();
        }
}
