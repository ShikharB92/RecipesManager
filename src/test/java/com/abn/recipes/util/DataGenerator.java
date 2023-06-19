package com.abn.recipes.util;

import com.abn.recipes.common.Category;
import com.abn.recipes.entity.RecipeEntity;
import com.abn.recipes.model.RecipeModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataGenerator {

    public RecipeModel getRecipeModelData() {
        return RecipeModel.builder().id(1l).name("First Recipe")
                .servings(4).category(Category.VEGETARIAN).build();
    }

    public List<RecipeModel> getRecipeDTODataList() {
        List<RecipeModel> recipeDTOList = new ArrayList<>();
        recipeDTOList.add(RecipeModel.builder().id(1l).name("First Recipe")
                .servings(4).category(Category.VEGETARIAN).build());
        recipeDTOList.add(RecipeModel.builder().id(2l).name("Second Recipe")
                .servings(2).category(Category.NONVEG).build());
        return recipeDTOList;
    }


    public RecipeEntity getRecipeEntityData() {
        return RecipeEntity.builder().name("First Recipe")
                .servings(4).category(Category.VEGETARIAN).build();
    }

    public RecipeEntity getRecipeEntityDataForPersistent(String recipeName) {
        return RecipeEntity.builder().name(recipeName)
                .servings(4).category(Category.VEGETARIAN).build();
    }


    public List<RecipeEntity> getRecipeEntityDataList() {
        List<RecipeEntity> RecipeEntityList = new ArrayList<>();
        RecipeEntityList.add(RecipeEntity.builder().name("First Recipe")
                .servings(4).category(Category.VEGETARIAN).build());
        RecipeEntityList.add(RecipeEntity.builder().name("Second Recipe")
                .servings(2).category(Category.NONVEG).build());
        return RecipeEntityList;
    }
}
