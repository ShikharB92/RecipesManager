package com.abn.recipes.testing;

import com.abn.recipes.RecipesApplication;
import com.abn.recipes.common.Category;
import com.abn.recipes.exception.RecipeNotFoundException;
import com.abn.recipes.model.RecipeModel;
import com.abn.recipes.model.RecipeSearchDto;
import com.abn.recipes.model.SearchCriteria;
import com.abn.recipes.repository.RecipeRepository;
import com.abn.recipes.util.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = RecipesApplication.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude=SecurityAutoConfiguration.class)
@AutoConfigureTestDatabase
public class RecipesRestControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private RecipeRepository repository;

    @Test
    public void getAllRecipes_thenStatus200() throws Exception {
        mvc.perform(get("/api/recipes/all").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()", greaterThanOrEqualTo(4)));
    }

    @Test
    public void get1Recipe_thenStatus200() throws Exception {
        mvc.perform(get("/api/recipes/1").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()", greaterThanOrEqualTo(1)));
    }

    @Test
    public void updateRecipe_thenStatus404() throws Exception {
        RecipeModel recipe = RecipeModel.builder().category(Category.VEGETARIAN).servings(3).build();
            mvc.perform(put("/api/recipes/10").contentType(MediaType.APPLICATION_JSON)
                            .content(JsonUtil.toJson(recipe)))
                    .andDo(print())
                    .andExpect(result -> assertTrue(result.getResolvedException() instanceof RecipeNotFoundException));
    }

    @Test
    public void updateRecipe_thenStatus200() throws Exception {
        RecipeModel recipe = RecipeModel.builder().category(Category.VEGETARIAN).servings(3).build();
        mvc.perform(put("/api/recipes/1").contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(recipe)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()", greaterThanOrEqualTo(1)));
    }


    @Test
    public void createRecipe_thenStatus200() throws Exception {
        RecipeModel recipe = RecipeModel.builder().category(Category.VEGETARIAN).servings(3).build();
        mvc.perform(post("/api/recipes").contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(recipe)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()", greaterThanOrEqualTo(1)));
    }

    @Test
    public void deleteRecipe_thenStatus200() throws Exception {
        RecipeModel recipe = RecipeModel.builder().category(Category.VEGETARIAN).servings(3).build();
        mvc.perform(post("/api/recipes/1").contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(recipe)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void searchRecipe_thenStatus200() throws Exception {
        String searchKey = "VEGETARIAN";
        Object searchObject = searchKey;
        SearchCriteria searchCriteria1 = new SearchCriteria("category", "cn", searchObject);
        SearchCriteria searchCriteria2 = new SearchCriteria("servings", "eq", (Object) "4");
        List<SearchCriteria> searchCriteriaList = List.of(searchCriteria1, searchCriteria2);
        RecipeSearchDto recipeSearchDto = new RecipeSearchDto(searchCriteriaList);
        mvc.perform(post("/api/recipes/search").contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(recipeSearchDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id", greaterThanOrEqualTo(2)));
        ;
    }
}
