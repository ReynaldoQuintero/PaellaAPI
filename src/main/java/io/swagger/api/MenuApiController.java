package io.swagger.api;

import io.swagger.model.Food;
import io.swagger.model.Ingredient;
import io.swagger.model.RestError;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.model.Category;
import io.swagger.model.FoodAndIngredients;
import io.swagger.model.IngredientFood;
import io.swagger.model.IngredientFoodKey;
import io.swagger.model.User;
import io.swagger.repository.CategoryRepository;
import io.swagger.repository.FoodRepository;
import io.swagger.repository.IngredientFoodRepository;
import io.swagger.repository.IngredientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Example;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-07T10:08:47.568Z[GMT]")
@Controller
public class MenuApiController implements MenuApi {

    private static final Logger log = LoggerFactory.getLogger(MenuApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    @Autowired
    private FoodRepository foodRepo;
    
    @Autowired
    private CategoryRepository categoryRepo;
    
    @Autowired
    private IngredientRepository ingredientRepo;
    
    @Autowired
    private IngredientFoodRepository ingredientFoodRepo;

    @org.springframework.beans.factory.annotation.Autowired
    public MenuApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<Food>> menuFoodGet(@ApiParam(value = "The name of the food to return.") @Valid @RequestParam(value = "name", required = false) String name
,@ApiParam(value = "The description of the food to return.") @Valid @RequestParam(value = "description", required = false) String description
,@ApiParam(value = "The ID of the category of the food to return.") @Valid @RequestParam(value = "categoryId", required = false) Integer categoryId
,@ApiParam(value = "The access token given to the authenticated user.", required = false) @Valid @RequestParam(value = "access_token", required = false) String accessToken
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            Food food = new Food();
            Authentication authentication = SecurityContextHolder.getContext()
            .getAuthentication();
            boolean flag = false;
            for(GrantedAuthority authority : authentication.getAuthorities()){
                if("ADMIN".equals(authority.getAuthority())){
                    flag = true;
                    break;
                }
            }
            if(!flag){
                food.setActive(true);
            }
            if(name != null){
                food.setName(name);
            }
            if(description != null){
                food.setName(name);
            }
            if(categoryId != null){
                food.setCategoryId(categoryId);
            }
            
            Example<Food> foodExample = Example.of(food);
            
            return new ResponseEntity<>(this.foodRepo.findAll(foodExample), HttpStatus.OK);
            
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

        public ResponseEntity<List<Food>> menuDishOfDayGet()
 {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
             return new ResponseEntity<>(foodRepo.findByIsDishOfDay(true), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Transactional
    public ResponseEntity<Void> menuFoodIdDelete(@ApiParam(value = "The ID of the food to delete.",required=true) @PathVariable("id") Integer id
,@NotNull @ApiParam(value = "The access token given to the authenticated user.", required = true) @Valid @RequestParam(value = "access_token", required = true) String accessToken
) {
        
        Authentication authentication = SecurityContextHolder.getContext()
        .getAuthentication();
        boolean flag = false;
        for(GrantedAuthority authority : authentication.getAuthorities()){
            if("ADMIN".equals(authority.getAuthority())){
                flag = true;
                break;
            }
        }
        if(!flag){
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        }
        
        Food toDelete = this.foodRepo.findOne(id);
        
        if(toDelete == null){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        
        this.ingredientFoodRepo.deleteByFoodId(id);
        
        this.foodRepo.delete(id);
        
        
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Food> menuFoodIdGet(@ApiParam(value = "The ID of the food to return.",required=true) @PathVariable("id") Integer id
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            Food food = this.foodRepo.findOne(id);
            
            if(food == null){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); 
            }

            return new ResponseEntity<Food>(food, HttpStatus.OK);

        }

        return new ResponseEntity<Food>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Integer> menuFoodQuantityGet(@ApiParam(value = "The ID of the food to return.",required=true) @PathVariable("id") Integer id
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {

            return new ResponseEntity<Integer>(this.foodRepo.findQuantitySoldByFoodId(id), HttpStatus.OK);

        }

        return new ResponseEntity<Integer>(HttpStatus.NOT_IMPLEMENTED);
    }
    
    @Transactional
    public ResponseEntity<Food> menuFoodIdPut(@ApiParam(value = "" ,required=true )  @Valid @RequestBody FoodAndIngredients body
,@NotNull @ApiParam(value = "The access token given to the authenticated user.", required = true) @Valid @RequestParam(value = "access_token", required = true) String accessToken
,@ApiParam(value = "The ID of the food to update.",required=true) @PathVariable("id") Integer id
) {
        
        Authentication authentication = SecurityContextHolder.getContext()
        .getAuthentication();
        boolean flag = false;
        for(GrantedAuthority authority : authentication.getAuthorities()){
            if("ADMIN".equals(authority.getAuthority())){
                flag = true;
                break;
            }
        }
        if(!flag){
            return new ResponseEntity<Food>(HttpStatus.UNAUTHORIZED);
        }
        
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            Food update = this.foodRepo.findOne(id);
            
            if(update == null){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); 
            }
            
            body.getFood().setFoodId(id);
            
            List<Ingredient> ingredients = new ArrayList<Ingredient>();
            
            for (int i = 0; i < body.getIngredientFoods().size(); i++) {
                Ingredient newIngredient = this.ingredientRepo.findOne(body.getIngredientFoods().get(i).getIngredientId());
                if(newIngredient == null){
                    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                }
                ingredients.add(newIngredient);
            }
            
            this.ingredientFoodRepo.deleteByFoodId(id);
            
            update = this.foodRepo.save(body.getFood());
            for (int i = 0; i < body.getIngredientFoods().size(); i++) {
                this.ingredientFoodRepo.save(body.getIngredientFoods().get(i));
            }
            
            return new ResponseEntity<Food>(update, HttpStatus.OK); 
        }

        return new ResponseEntity<Food>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Food> menuFoodPost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody FoodAndIngredients body
,@NotNull @ApiParam(value = "The access token given to the authenticated user.", required = true) @Valid @RequestParam(value = "access_token", required = true) String accessToken
) {
        
        System.out.println(body.getFood());
        
        Authentication authentication = SecurityContextHolder.getContext()
        .getAuthentication();
        boolean flag = false;
        for(GrantedAuthority authority : authentication.getAuthorities()){
            if("ADMIN".equals(authority.getAuthority())){
                flag = true;
                break;
            }
        }
        if(!flag){
            return new ResponseEntity<Food>(HttpStatus.UNAUTHORIZED);
        }
        
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            body.getFood().setFoodId(null);
            List<Ingredient> ingredients = new ArrayList<Ingredient>();
            
            for (int i = 0; i < body.getIngredientFoods().size(); i++) {
                Ingredient newIngredient = this.ingredientRepo.getOne(body.getIngredientFoods().get(i).getIngredientId());
                if(newIngredient == null){
                    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                }
                ingredients.add(newIngredient);
            }
            
            Food created = this.foodRepo.save(body.getFood());
            
            for (int i = 0; i <  body.getIngredientFoods().size(); i++) {
                body.getIngredientFoods().get(i).setFoodId(created.getFoodId());
                this.ingredientFoodRepo.save(body.getIngredientFoods().get(i));
            }
            
            return new ResponseEntity<Food>(created, HttpStatus.OK); 
        }

        return new ResponseEntity<Food>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Ingredient>> menuIngredientsGet(@ApiParam(value = "The name of the ingredient to return.") @Valid @RequestParam(value = "name", required = false) String name
,@ApiParam(value = "The description of the ingredient to return.") @Valid @RequestParam(value = "description", required = false) String description
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            if(name != null && description != null){
                return new ResponseEntity<>(ingredientRepo.findByNameAndDescription(name, description), HttpStatus.OK);
            } else if(name != null){
                return new ResponseEntity<>(ingredientRepo.findByName(name), HttpStatus.OK);
            } else if(description != null){
                return new ResponseEntity<>(ingredientRepo.findByDescription(description), HttpStatus.OK);
            } else {
             return new ResponseEntity<>(ingredientRepo.findAll(), HttpStatus.OK);   
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> menuIngredientsIdDelete(@ApiParam(value = "The ID of the ingredient to delete.",required=true) @PathVariable("id") Integer id
,@NotNull @ApiParam(value = "The access token given to the authenticated user.", required = true) @Valid @RequestParam(value = "access_token", required = true) String accessToken
) {
        
        Authentication authentication = SecurityContextHolder.getContext()
        .getAuthentication();
        boolean flag = false;
        for(GrantedAuthority authority : authentication.getAuthorities()){
            if("ADMIN".equals(authority.getAuthority())){
                flag = true;
                break;
            }
        }
        if(!flag){
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        }
        Ingredient toDelete = this.ingredientRepo.findOne(id);
        
        if(toDelete == null){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        
        this.ingredientRepo.delete(id);
        this.ingredientFoodRepo.deleteByIngredientId(id);
        
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Ingredient> menuIngredientsIdGet(@ApiParam(value = "The ID of the ingredient to return.",required=true) @PathVariable("id") Integer id
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            Ingredient ingredient = this.ingredientRepo.findOne(id);
            
            if(ingredient == null){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); 
            }

            return new ResponseEntity<Ingredient>(ingredient, HttpStatus.OK);

        }

        return new ResponseEntity<Ingredient>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Ingredient> menuIngredientsIdPut(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Ingredient body
,@NotNull @ApiParam(value = "The access token given to the authenticated user.", required = true) @Valid @RequestParam(value = "access_token", required = true) String accessToken
,@ApiParam(value = "The ID of the ingredient to update.",required=true) @PathVariable("id") Integer id
) {
        Authentication authentication = SecurityContextHolder.getContext()
        .getAuthentication();
        boolean flag = false;
        for(GrantedAuthority authority : authentication.getAuthorities()){
            if("ADMIN".equals(authority.getAuthority())){
                flag = true;
                break;
            }
        }
        if(!flag){
            return new ResponseEntity<Ingredient>(HttpStatus.UNAUTHORIZED);
        }
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            Ingredient update = this.ingredientRepo.findOne(id);
            
            if(update == null){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); 
            }
            
            body.setIngredientID(id);
            update = this.ingredientRepo.save(body);
            
            return new ResponseEntity<Ingredient>(update, HttpStatus.OK); 
        }

        return new ResponseEntity<Ingredient>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Ingredient> menuIngredientsPost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Ingredient body
,@NotNull @ApiParam(value = "The access token given to the authenticated user.", required = true) @Valid @RequestParam(value = "access_token", required = true) String accessToken
) {
        Authentication authentication = SecurityContextHolder.getContext()
        .getAuthentication();
        boolean flag = false;
        for(GrantedAuthority authority : authentication.getAuthorities()){
            if("ADMIN".equals(authority.getAuthority())){
                flag = true;
                break;
            }
        }
        if(!flag){
            return new ResponseEntity<Ingredient>(HttpStatus.UNAUTHORIZED);
        }
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            body.setIngredientID(null);
            
            Ingredient created = this.ingredientRepo.save(body);
            return new ResponseEntity<Ingredient>(created, HttpStatus.OK); 
        }

        return new ResponseEntity<Ingredient>(HttpStatus.NOT_IMPLEMENTED);
    }
    
      public ResponseEntity<List<IngredientFood>> menuIngredientFoodsGet(@ApiParam(value = "The name of the ingredient to return.") @Valid @RequestParam(value = "nameIngredient", required = false) String nameIngredient
,@ApiParam(value = "The description of the ingredient to return.") @Valid @RequestParam(value = "descriptionIngredient", required = false) String descriptionIngredient
,@ApiParam(value = "The id of the food to return.") @Valid @RequestParam(value = "foodId", required = false) Integer foodId
,@ApiParam(value = "The name of the food to return.") @Valid @RequestParam(value = "nameFood", required = false) String nameFood
,@ApiParam(value = "The description of the food to return.") @Valid @RequestParam(value = "descriptionFood", required = false) String descriptionFood
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            List<Ingredient> ingredients = new ArrayList();
            List<Food> foods = new ArrayList();
            
            if(nameIngredient != null && descriptionIngredient != null){
                ingredients = ingredientRepo.findByNameAndDescription(nameIngredient, descriptionIngredient);
            } else if(nameIngredient != null){
                ingredients = ingredientRepo.findByName(nameIngredient);
            } else if(descriptionIngredient != null){
                ingredients = ingredientRepo.findByDescription(descriptionIngredient);
            }
            
            if(nameFood != null || descriptionFood != null || foodId != null){
                Food food = new Food();
                
                if(nameFood != null){
                    food.setName(nameFood);
                }
                
                if(descriptionFood != null){
                    food.setDescription(descriptionFood);
                }
                
                if(foodId != null){
                    food.setFoodId(foodId);
                }

                foods = foodRepo.findAll(Example.of(food));
            }
            
            List<Integer> foodIds = new ArrayList();
            List<Integer> ingredientIds = new ArrayList();
            
            if(foods.size() != 0){
                for (int i = 0; i < foods.size(); i++) {
                    foodIds.add(foods.get(i).getFoodId());
                }
            }
            if(ingredients.size() != 0){
                for (int i = 0; i < ingredients.size(); i++) {
                    foodIds.add(ingredients.get(i).getIngredientID());
                }
            }
            
            if(ingredientIds.size() != 0 && foodIds.size() != 0){
                return new ResponseEntity<>(ingredientFoodRepo.findByFoodIdInAndIngredientIdIn(foodIds, ingredientIds), HttpStatus.OK);
            } else if (ingredientIds.size() != 0) {
                return new ResponseEntity<>(ingredientFoodRepo.findByIngredientIdIn(ingredientIds), HttpStatus.OK);
            } else if (foodIds.size() != 0) {
                return new ResponseEntity<>(ingredientFoodRepo.findByFoodIdIn(foodIds), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(ingredientFoodRepo.findAll(), HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> menuIngredientFoodsIdDelete(@ApiParam(value = "The ID of the link to delete.",required=true) @PathVariable("id") Integer id
,@NotNull @ApiParam(value = "The access token given to the authenticated user.", required = true) @Valid @RequestParam(value = "access_token", required = true) String accessToken
) {
        Authentication authentication = SecurityContextHolder.getContext()
        .getAuthentication();
        boolean flag = false;
        for(GrantedAuthority authority : authentication.getAuthorities()){
            if("ADMIN".equals(authority.getAuthority())){
                flag = true;
                break;
            }
        }
        if(!flag){
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        }
        IngredientFood toDelete = this.ingredientFoodRepo.findOne(id);
        
        if(toDelete == null){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        
        this.ingredientFoodRepo.delete(id);
        
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<IngredientFood> menuIngredientFoodsIdGet(@ApiParam(value = "The ID of the link to return.",required=true) @PathVariable("id") Integer id
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            IngredientFood ingredient = this.ingredientFoodRepo.findOne(id);
            
            if(ingredient == null){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); 
            }

            return new ResponseEntity<IngredientFood>(ingredient, HttpStatus.OK);

        }

        return new ResponseEntity<IngredientFood>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<IngredientFood> menuIngredientFoodsIdPut(@ApiParam(value = "" ,required=true )  @Valid @RequestBody IngredientFood body
,@NotNull @ApiParam(value = "The access token given to the authenticated user.", required = true) @Valid @RequestParam(value = "access_token", required = true) String accessToken
,@ApiParam(value = "The ID of the link to update.",required=true) @PathVariable("id") Integer id
) {
        Authentication authentication = SecurityContextHolder.getContext()
        .getAuthentication();
        boolean flag = false;
        for(GrantedAuthority authority : authentication.getAuthorities()){
            if("ADMIN".equals(authority.getAuthority())){
                flag = true;
                break;
            }
        }
        if(!flag){
            return new ResponseEntity<IngredientFood>(HttpStatus.UNAUTHORIZED);
        }
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            IngredientFood update = this.ingredientFoodRepo.findOne(id);
            
            if(update == null){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); 
            }
            
            body.setId(id);
            update = this.ingredientFoodRepo.save(body);
            
            return new ResponseEntity<IngredientFood>(update, HttpStatus.OK); 
        }

        return new ResponseEntity<IngredientFood>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<IngredientFood> menuIngredientFoodsPost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody IngredientFood body
,@NotNull @ApiParam(value = "The access token given to the authenticated user.", required = true) @Valid @RequestParam(value = "access_token", required = true) String accessToken
) {
        Authentication authentication = SecurityContextHolder.getContext()
        .getAuthentication();
        boolean flag = false;
        for(GrantedAuthority authority : authentication.getAuthorities()){
            if("ADMIN".equals(authority.getAuthority())){
                flag = true;
                break;
            }
        }
        if(!flag){
            return new ResponseEntity<IngredientFood>(HttpStatus.UNAUTHORIZED);
        }
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            body.setId(null);
            
            IngredientFood created = this.ingredientFoodRepo.save(body);
            return new ResponseEntity<IngredientFood>(created, HttpStatus.OK); 
        }

        return new ResponseEntity<IngredientFood>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<List<Category>> menuCategoriesGet(@ApiParam(value = "The name of the category to return.") @Valid @RequestParam(value = "name", required = false) String name
,@ApiParam(value = "The description of the category to return.") @Valid @RequestParam(value = "description", required = false) String description
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            Category category = new Category();
            
            if(name != null){
                category.setName(name);
            }
            if(description != null){
                category.setName(name);
            }
            
            Example<Category> categoryExample = Example.of(category);
            
            return new ResponseEntity<>(this.categoryRepo.findAll(categoryExample), HttpStatus.OK);
            
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Void> menuCategoriesIdDelete(@ApiParam(value = "The ID of the category to delete.",required=true) @PathVariable("id") Integer id
,@NotNull @ApiParam(value = "The access token given to the authenticated user.", required = true) @Valid @RequestParam(value = "access_token", required = true) String accessToken
) {
        Authentication authentication = SecurityContextHolder.getContext()
        .getAuthentication();
        boolean flag = false;
        for(GrantedAuthority authority : authentication.getAuthorities()){
            if("ADMIN".equals(authority.getAuthority())){
                flag = true;
                break;
            }
        }
        if(!flag){
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        }
        
        Category toDelete = this.categoryRepo.findOne(id);
        
        if(toDelete == null){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        
        this.categoryRepo.delete(id);
        
        List<Food> foods = this.foodRepo.findByCategoryId(id);
        
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Category> menuCategoriesIdGet(@ApiParam(value = "The ID of the category to return.",required=true) @PathVariable("id") Integer id
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            Category category = this.categoryRepo.findOne(id);
            
            if(category == null){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); 
            }

            return new ResponseEntity<Category>(category, HttpStatus.OK);

        }

        return new ResponseEntity<Category>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Category> menuCategoriesIdPut(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Category body
,@NotNull @ApiParam(value = "The access token given to the authenticated user.", required = true) @Valid @RequestParam(value = "access_token", required = true) String accessToken
,@ApiParam(value = "The ID of the category to update.",required=true) @PathVariable("id") Integer id
) {
        Authentication authentication = SecurityContextHolder.getContext()
        .getAuthentication();
        boolean flag = false;
        for(GrantedAuthority authority : authentication.getAuthorities()){
            if("ADMIN".equals(authority.getAuthority())){
                flag = true;
                break;
            }
        }
        if(!flag){
            return new ResponseEntity<Category>(HttpStatus.UNAUTHORIZED);
        }
        
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            Category update = this.categoryRepo.findOne(id);
            
            if(update == null){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); 
            }
            
            body.setCategoryId(id);
            
            update = this.categoryRepo.save(body);
            
            return new ResponseEntity<Category>(update, HttpStatus.OK); 
        }

        return new ResponseEntity<Category>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Category> menuCategoriesPost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Category body
,@NotNull @ApiParam(value = "The access token given to the authenticated user.", required = true) @Valid @RequestParam(value = "access_token", required = true) String accessToken
) {
        Authentication authentication = SecurityContextHolder.getContext()
        .getAuthentication();
        boolean flag = false;
        for(GrantedAuthority authority : authentication.getAuthorities()){
            if("ADMIN".equals(authority.getAuthority())){
                flag = true;
                break;
            }
        }
        if(!flag){
            return new ResponseEntity<Category>(HttpStatus.UNAUTHORIZED);
        }
        
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            body.setCategoryId(null);
            
            Category created = this.categoryRepo.save(body);
            
            return new ResponseEntity<Category>(created, HttpStatus.OK); 
        }

        return new ResponseEntity<Category>(HttpStatus.NOT_IMPLEMENTED);
    }

}
