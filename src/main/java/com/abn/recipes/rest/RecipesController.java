package com.abn.recipes.rest;

import com.abn.recipes.model.RecipeModel;
import com.abn.recipes.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/{id}")
    public RecipeModel getRecipe(@PathVariable Long id) {
        return recipeService.getRecipe(id);
    }

    //POST mapping to add recipe
    @PostMapping
    public RecipeModel addRecipe(@RequestBody RecipeModel recipeModel) {
        return recipeService.saveRecipe(recipeModel);
    }

    //PUT mapping to update recipe
    @PutMapping("/{id}")
    public RecipeModel updateRecipe(@PathVariable Long id, @RequestBody RecipeModel recipeModel) {
        return recipeService.updateRecipe(id, recipeModel);
    }

    //DELETE mapping to delete recipe by id
    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
    }
    

}
