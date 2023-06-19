package com.abn.recipes.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class RecipeSpecificationTest {

    @InjectMocks
    private RecipeSpecification recipeSpecification;

    @Test
    public void testRecipeSpecificationError() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            recipeSpecification.toPredicate(null, null, null);
        });

    }
}
