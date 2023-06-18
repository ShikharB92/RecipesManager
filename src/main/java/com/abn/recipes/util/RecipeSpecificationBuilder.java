package com.abn.recipes.util;

import com.abn.recipes.entity.RecipeEntity;
import com.abn.recipes.model.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class RecipeSpecificationBuilder {
    private final List<SearchCriteria> params;

    public RecipeSpecificationBuilder() {
        this.params = new ArrayList<>();
    }

    public final RecipeSpecificationBuilder with(SearchCriteria searchCriteria) {
        params.add(searchCriteria);
        return this;
    }

    public Specification<RecipeEntity> build() {
        if (params.size() == 0) {
            return null;
        }

        Specification<RecipeEntity> result = new RecipeSpecification(params.get(0));
        for (int i = 1; i < params.size(); i++) {
            SearchCriteria criteria = params.get(i);
            result = Specification.where(result).and(new RecipeSpecification(criteria));
        }
        return result;
    }
}
