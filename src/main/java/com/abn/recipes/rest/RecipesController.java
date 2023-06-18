package com.abn.recipes.rest;

import com.abn.recipes.model.RecipeModel;
import com.abn.recipes.model.RecipeSearchDto;
import com.abn.recipes.model.SearchCriteria;
import com.abn.recipes.service.RecipeService;
import com.abn.recipes.util.RecipeSpecificationBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    //POST mapping to search recipe
    @PostMapping("/search")
    public Page<RecipeModel> searchEmployees(@PageableDefault Pageable pageable, @RequestBody RecipeSearchDto recipeSearchDto) {
//        APIResponse apiResponse = new APIResponse();
        RecipeSpecificationBuilder builder = new RecipeSpecificationBuilder();
        List<SearchCriteria> criteriaList = recipeSearchDto.getSearchCriteriaList();
        if (criteriaList != null) {
            criteriaList.forEach(criteria -> {
                builder.with(criteria);
            });
        }

        return recipeService.findBySearchCriteria(builder.build(), pageable);
//        apiResponse.setData(employeePage.toList());
//        apiResponse.setResponseCode(HttpStatus.OK);
//        apiResponse.setMessage("Successfully retrieved employee
//                record");
//
//        return new ResponseEntity<>(apiResponse,
//                apiResponse.getResponseCode());
    }
}
