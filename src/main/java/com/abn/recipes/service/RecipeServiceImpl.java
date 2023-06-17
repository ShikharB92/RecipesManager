package com.abn.recipes.service;

import com.abn.recipes.entity.RecipeEntity;
import com.abn.recipes.exception.RecipeNotFoundException;
import com.abn.recipes.mapper.RecipeEntityToModelTransformer;
import com.abn.recipes.mapper.RecipeModelToEntityTransformer;
import com.abn.recipes.model.RecipeModel;
import com.abn.recipes.repository.RecipeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeEntityToModelTransformer recipeEntityToModelTransformer;
    private final RecipeModelToEntityTransformer recipeModelToEntityTransformer;

    @Override
    public Page<RecipeModel> getAllRecipes(Pageable pageable) {
        return recipeRepository.findAll(pageable).map(recipeEntityToModelTransformer);
    }

    @Override
    @Transactional
    public RecipeModel saveRecipe(RecipeModel recipeModel) {
        RecipeEntity recipeEntity = recipeModelToEntityTransformer.apply(recipeModel);
        return recipeEntityToModelTransformer.apply(recipeRepository.save(recipeEntity));
    }

    @Override
    public RecipeModel getRecipe(Long id) {
        return recipeEntityToModelTransformer.apply(recipeRepository.findById(id).orElseThrow(() -> new RecipeNotFoundException("No Recipe found with the id: " + id)));
    }

    @Override
    @Transactional
    public void deleteRecipe(Long id) {
        this.recipeRepository.findById(id).ifPresentOrElse(recipeRepository::delete, () -> {
            throw new RecipeNotFoundException("No Recipe found with the id: " + id);
        });
    }

    @Override
    @Transactional
    public RecipeModel updateRecipe(Long id, RecipeModel recipeModel) {
        RecipeEntity recipeEntity = recipeRepository.findById(id).orElseThrow(() -> new RecipeNotFoundException("No Recipe found with the id: " + id));
        recipeEntity.setName(recipeModel.getName());
        recipeEntity.setIngredients(recipeModel.getIngredients());
        recipeEntity.setInstructions(recipeModel.getInstructions());
        recipeEntity.setCategory(recipeModel.getCategory());
        recipeEntity.setServings(recipeModel.getServings());
        return recipeEntityToModelTransformer.apply(recipeRepository.save(recipeEntity));
    }
}
