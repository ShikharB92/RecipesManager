package com.abn.recipes.util;

import com.abn.recipes.entity.RecipeEntity;
import com.abn.recipes.model.SearchCriteria;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class RecipeSpecificationBuilderTest {

    @Test
    public void testRecipeSpecificationBuilder() {
        List<SearchCriteria> params = new ArrayList<>();
        String value = "test";
        Object object = value;
        SearchCriteria searchCriteria1 = new SearchCriteria("name", "eq", object);
        SearchCriteria searchCriteria2 = new SearchCriteria("category", "eq", object);
        params.add(searchCriteria1);
        params.add(searchCriteria2);
        RecipeSpecificationBuilder recipeSpecificationBuilder = new RecipeSpecificationBuilder();
        recipeSpecificationBuilder.with(params.get(0));
        Specification<RecipeEntity> result = recipeSpecificationBuilder.build();
        assert (result != null);
    }
}
