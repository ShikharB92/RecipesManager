package com.abn.recipes.rest;

import com.abn.recipes.model.RecipeModel;
import com.abn.recipes.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Rest Controller for Recipes
@RestController
@RequestMapping("/api/recipes")
@RequiredArgsConstructor
public class RecipesController {

    private final RecipeService recipeService;

    //GET mapping to get list of recipes
    @GetMapping("/all")
    public Page<RecipeModel> getAllRecipes(@PageableDefault Pageable pageable) {
        return recipeService.getAllRecipes(pageable);
    }

    //GET mapping to get recipe by id
    //POST mapping to add recipe
    //PUT mapping to update recipe
    //DELETE mapping to delete recipe

}
