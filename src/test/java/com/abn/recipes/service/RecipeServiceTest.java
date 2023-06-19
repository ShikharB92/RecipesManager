package com.abn.recipes.service;

import com.abn.recipes.entity.RecipeEntity;
import com.abn.recipes.exception.RecipeNotFoundException;
import com.abn.recipes.mapper.RecipeEntityToModelTransformer;
import com.abn.recipes.mapper.RecipeModelToEntityTransformer;
import com.abn.recipes.model.RecipeModel;
import com.abn.recipes.repository.RecipeRepository;
import com.abn.recipes.util.DataGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RecipeServiceTest {

    @InjectMocks
    private RecipeServiceImpl recipeService;

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private RecipeEntityToModelTransformer recipeEntityToModelTransformer;

    @Mock
    private RecipeModelToEntityTransformer recipeModelToEntityTransformer;

    @InjectMocks
    private DataGenerator dataGenerator;


    //Success scenario to get all recipes
    @Test
    public void testGetAllRecipes() {
        Pageable pageable = Pageable.ofSize(10);
        when(recipeRepository.findAll(pageable)).thenReturn(new PageImpl<>(new ArrayList<>()));
        Page<RecipeModel> result = recipeService.getAllRecipes(pageable);
        assert (result != null);
    }

    //Success scenario to save recipe
    @Test
    public void testSaveRecipe() {
        when(recipeModelToEntityTransformer.apply(any())).thenReturn(dataGenerator.getRecipeEntityData());
        when(recipeRepository.save(any())).thenReturn(dataGenerator.getRecipeEntityData());
        when(recipeEntityToModelTransformer.apply(any())).thenReturn(dataGenerator.getRecipeModelData());
        RecipeModel result = recipeService.saveRecipe(dataGenerator.getRecipeModelData());
        assert (result != null);
        assert (result.getId() == 1L);
    }

    //Success scenario to get recipe by id
    @Test
    public void testGetRecipeById() {
        when(recipeRepository.findById(any())).thenReturn(Optional.ofNullable(dataGenerator.getRecipeEntityData()));
        when(recipeEntityToModelTransformer.apply(any())).thenReturn(dataGenerator.getRecipeModelData());
        RecipeModel result = recipeService.getRecipe(1L);
        assert (result != null);
        assert (result.getId() == 1L);
    }

    //Error scenario to get recipe by id
    @Test()
    public void testGetRecipeByIdError() {
        Exception exception = assertThrows(RecipeNotFoundException.class, () -> {
            recipeService.getRecipe(3l);
        });

        String expectedMessage = "No Recipe found with the id: 3";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    //Success scenario to delete recipe by id
    @Test()
    public void testDeleteRecipeById() {
        when(recipeRepository.findById(any())).thenReturn(Optional.ofNullable(dataGenerator.getRecipeEntityData()));
        recipeService.deleteRecipe(1L);
        verify(recipeRepository, times(1)).delete((RecipeEntity) any());
    }

    //Error scenario to delete recipe by id
    @Test()
    public void testDeleteRecipeByIdError() {
        Exception exception = assertThrows(RecipeNotFoundException.class, () -> {
            recipeService.deleteRecipe(3l);
        });

        String expectedMessage = "No Recipe found with the id: 3";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    //Error scenario to update recipe by id
    @Test()
    public void testUpdateRecipeByIdError() {
        Exception exception = assertThrows(RecipeNotFoundException.class, () -> {
            recipeService.updateRecipe(3l, dataGenerator.getRecipeModelData());
        });

        String expectedMessage = "No Recipe found with the id: 3";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    //Success scenario to update recipe by id
    @Test()
    public void testUpdateRecipeById() {
        when(recipeRepository.findById(any())).thenReturn(Optional.ofNullable(dataGenerator.getRecipeEntityData()));
        when(recipeRepository.save(any())).thenReturn(dataGenerator.getRecipeEntityData());
        when(recipeEntityToModelTransformer.apply(any())).thenReturn(dataGenerator.getRecipeModelData());
        RecipeModel result = recipeService.updateRecipe(1L, dataGenerator.getRecipeModelData());
        assert (result != null);
        assert (result.getId() == 1L);
    }

    //Success scenario to search recipe
    @Test
    public void testSearchRecipe() {
        Pageable pageable = Pageable.ofSize(10);
        when(recipeRepository.findAll(Specification.anyOf(), pageable)).thenReturn(new PageImpl<>(new ArrayList<>()));
        Page<RecipeModel> result = recipeService.findBySearchCriteria(Specification.anyOf(), pageable);
        assert (result != null);
    }
}
