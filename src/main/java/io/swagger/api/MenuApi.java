/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.19).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.Food;
import io.swagger.model.Ingredient;
import io.swagger.model.RestError;
import io.swagger.annotations.*;
import io.swagger.model.Category;
import io.swagger.model.FoodAndIngredients;
import io.swagger.model.IngredientFood;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CookieValue;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-07T10:08:47.568Z[GMT]")
@Api(value = "menu", description = "the menu API")
public interface MenuApi {

    @ApiOperation(value = "Returns all food.", nickname = "menuFoodGet", notes = "Endpoint where all the food can be obtained.", response = Food.class, responseContainer = "List", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A list of JSON representations of the food", response = Food.class, responseContainer = "List") })
    @RequestMapping(value = "/menu/food",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Food>> menuFoodGet(@ApiParam(value = "The name of the food to return.") @Valid @RequestParam(value = "name", required = false) String name
,@ApiParam(value = "The description of the food to return.") @Valid @RequestParam(value = "description", required = false) String description
,@ApiParam(value = "The ID of the category of the food to return.") @Valid @RequestParam(value = "categoryId", required = false) Integer categoryId
);
    
    @ApiOperation(value = "Returns all foods listed as dish of the day", nickname = "menuFoodDishOfDayGet", notes = "Endpoint where the featured food can be obtained.", response = Food.class, responseContainer = "List", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A list of JSON representations of the food", response = Food.class, responseContainer = "List") })
    @RequestMapping(value = "/menu/dishOfDay",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Food>> menuDishOfDayGet();


    @ApiOperation(value = "Deletes a food by ID.", nickname = "menuFoodIdDelete", notes = "Endpoint where a food can be deleted by specifying its ID.", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Empty response"),
        @ApiResponse(code = 404, message = "Could not find the food requested", response = RestError.class) })
    @RequestMapping(value = "/menu/food/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> menuFoodIdDelete(@ApiParam(value = "The ID of the food to delete.",required=true) @PathVariable("id") Integer id
,@NotNull @ApiParam(value = "The access token given to the authenticated user.", required = true) @Valid @RequestParam(value = "access_token", required = true) String accessToken
);


    @ApiOperation(value = "Returns a food by ID.", nickname = "menuFoodIdGet", notes = "Endpoint where a food can be obtained by specifying its ID.", response = Food.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A JSON representation of the food", response = Food.class),
        @ApiResponse(code = 404, message = "Could not find the food requested", response = RestError.class) })
    @RequestMapping(value = "/menu/food/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Food> menuFoodIdGet(@ApiParam(value = "The ID of the food to return.",required=true) @PathVariable("id") Integer id
);
    
    @ApiOperation(value = "Returns the number of times a food has been bought.", nickname = "menuFoodQuantityGet", notes = "Endpoint where, by specifying it's ID, the amount of times the food has been bought is returned.", response = Integer.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "An integer with the amount", response = Integer.class),
        @ApiResponse(code = 404, message = "Could not find the food requested", response = RestError.class) })
    @RequestMapping(value = "/menu/foodQuantity/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Integer> menuFoodQuantityGet(@ApiParam(value = "The ID of the food to return.",required=true) @PathVariable("id") Integer id
);


    @ApiOperation(value = "Updates a food by ID.", nickname = "menuFoodIdPut", notes = "Endpoint where a food can be updated by specifying its ID.", response = Food.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A JSON representation of the updated food", response = Food.class),
        @ApiResponse(code = 404, message = "Could not find the food requested", response = RestError.class) })
    @RequestMapping(value = "/menu/food/{id}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Food> menuFoodIdPut(@ApiParam(value = "" ,required=true )  @Valid @RequestBody FoodAndIngredients body
,@NotNull @ApiParam(value = "The access token given to the authenticated user.", required = true) @Valid @RequestParam(value = "access_token", required = true) String accessToken
,@ApiParam(value = "The ID of the food to update.",required=true) @PathVariable("id") Integer id
);


    @ApiOperation(value = "Creates a food.", nickname = "menuFoodPost", notes = "", response = Food.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Created", response = Food.class) })
    @RequestMapping(value = "/menu/food",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Food> menuFoodPost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody FoodAndIngredients body
,@NotNull @ApiParam(value = "The access token given to the authenticated user.", required = true) @Valid @RequestParam(value = "access_token", required = true) String accessToken
);


    @ApiOperation(value = "Returns all ingredients.", nickname = "menuIngredientsGet", notes = "Endpoint where all the ingredients can be obtained.", response = Ingredient.class, responseContainer = "List", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A list of JSON representations of the food", response = Ingredient.class, responseContainer = "List") })
    @RequestMapping(value = "/menu/ingredients",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Ingredient>> menuIngredientsGet(@ApiParam(value = "The name of the ingredient to return.") @Valid @RequestParam(value = "name", required = false) String name
,@ApiParam(value = "The description of the ingredient to return.") @Valid @RequestParam(value = "description", required = false) String description
);


    @ApiOperation(value = "Deletes an ingredient by ID.", nickname = "menuIngredientsIdDelete", notes = "Endpoint where an ingredient can be deleted by specifying its ID.", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Empty response"),
        @ApiResponse(code = 404, message = "Could not find the ingredient requested", response = RestError.class) })
    @RequestMapping(value = "/menu/ingredients/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> menuIngredientsIdDelete(@ApiParam(value = "The ID of the ingredient to delete.",required=true) @PathVariable("id") Integer id
,@NotNull @ApiParam(value = "The access token given to the authenticated user.", required = true) @Valid @RequestParam(value = "access_token", required = true) String accessToken
);


    @ApiOperation(value = "Returns an ingredient by ID.", nickname = "menuIngredientsIdGet", notes = "Endpoint where an ingredient can be obtained by specifying its ID.", response = Ingredient.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A JSON representation of the ingredient", response = Ingredient.class),
        @ApiResponse(code = 404, message = "Could not find the ingredient requested", response = RestError.class) })
    @RequestMapping(value = "/menu/ingredients/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Ingredient> menuIngredientsIdGet(@ApiParam(value = "The ID of the ingredient to return.",required=true) @PathVariable("id") Integer id
);


    @ApiOperation(value = "Updates an ingredient by ID.", nickname = "menuIngredientsIdPut", notes = "Endpoint where an ingredient can be updated by specifying its ID.", response = Ingredient.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A JSON representation of the updated ingredient", response = Ingredient.class),
        @ApiResponse(code = 404, message = "Could not find the ingredient requested", response = RestError.class) })
    @RequestMapping(value = "/menu/ingredients/{id}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Ingredient> menuIngredientsIdPut(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Ingredient body
,@NotNull @ApiParam(value = "The access token given to the authenticated user.", required = true) @Valid @RequestParam(value = "access_token", required = true) String accessToken
,@ApiParam(value = "The ID of the ingredient to update.",required=true) @PathVariable("id") Integer id
);


    @ApiOperation(value = "Creates an ingredient.", nickname = "menuIngredientsPost", notes = "", response = Ingredient.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Created", response = Ingredient.class) })
    @RequestMapping(value = "/menu/ingredients",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Ingredient> menuIngredientsPost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Ingredient body
,@NotNull @ApiParam(value = "The access token given to the authenticated user.", required = true) @Valid @RequestParam(value = "access_token", required = true) String accessToken
);
    
    @ApiOperation(value = "Returns all links between ingredients and food.", nickname = "menuIngredientFoodsGet", notes = "Endpoint where all links between ingredients and food can be obtained.", response = IngredientFood.class, responseContainer = "List", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A list of JSON representations of the link", response = IngredientFood.class, responseContainer = "List") })
    @RequestMapping(value = "/menu/ingredientFoods",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<IngredientFood>> menuIngredientFoodsGet(@ApiParam(value = "The name of the ingredient to return.") @Valid @RequestParam(value = "nameIngredient", required = false) String nameIngredient
,@ApiParam(value = "The description of the ingredient to return.") @Valid @RequestParam(value = "descriptionIngredient", required = false) String descriptionIngredient
,@ApiParam(value = "The name of the food to return.") @Valid @RequestParam(value = "nameFood", required = false) String nameFood
,@ApiParam(value = "The description of the food to return.") @Valid @RequestParam(value = "descriptionFood", required = false) String descriptionFood
);

    @ApiOperation(value = "Deletes a link between a food and an ingredient by ID.", nickname = "menuIngredientFoodsIdDelete", notes = "Endpoint where a link between a food and an ingredient can be deleted by specifying its ID.", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Empty response"),
        @ApiResponse(code = 404, message = "Could not find the link requested", response = RestError.class) })
    @RequestMapping(value = "/menu/ingredientFoods/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> menuIngredientFoodsIdDelete(@ApiParam(value = "The ID of the link to delete.",required=true) @PathVariable("id") Integer id
,@NotNull @ApiParam(value = "The access token given to the authenticated user.", required = true) @Valid @RequestParam(value = "access_token", required = true) String accessToken
);

    @ApiOperation(value = "Returns a link between ingredients and food by ID.", nickname = "menuIngredientFoodsIdGet", notes = "Endpoint where a link between ingredients and food can be obtained by specifying its ID.", response = IngredientFood.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A JSON representation of the link", response = Ingredient.class),
        @ApiResponse(code = 404, message = "Could not find the ingredient requested", response = RestError.class) })
    @RequestMapping(value = "/menu/ingredientFoods/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<IngredientFood> menuIngredientFoodsIdGet(@ApiParam(value = "The ID of the link to return.",required=true) @PathVariable("id") Integer id
);

    @ApiOperation(value = "Updates a link between ingredients and food by ID.", nickname = "menuIngredientFoodsIdPut", notes = "Endpoint where a link between ingredients and food can be updated by specifying its ID.", response = IngredientFood.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A JSON representation of the updated ingredient", response = IngredientFood.class),
        @ApiResponse(code = 404, message = "Could not find the ingredient requested", response = RestError.class) })
    @RequestMapping(value = "/menu/ingredientFoods/{id}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<IngredientFood> menuIngredientFoodsIdPut(@ApiParam(value = "" ,required=true )  @Valid @RequestBody IngredientFood body
,@NotNull @ApiParam(value = "The access token given to the authenticated user.", required = true) @Valid @RequestParam(value = "access_token", required = true) String accessToken
,@ApiParam(value = "The ID of the link to update.",required=true) @PathVariable("id") Integer id
);

    @ApiOperation(value = "Creates a link between ingredients and food", nickname = "menuIngredientFoodsPost", notes = "", response = IngredientFood.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Created", response = IngredientFood.class) })
    @RequestMapping(value = "/menu/IngredientFoods",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<IngredientFood> menuIngredientFoodsPost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody IngredientFood body
,@NotNull @ApiParam(value = "The access token given to the authenticated user.", required = true) @Valid @RequestParam(value = "access_token", required = true) String accessToken
);
    
    
    
    @ApiOperation(value = "Returns all categories.", nickname = "menuCategoriesGet", notes = "Endpoint where all categories can be obtained.", response = Category.class, responseContainer = "List", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A list of JSON representations of the link", response = Category.class, responseContainer = "List") })
    @RequestMapping(value = "/menu/categories",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Category>> menuCategoriesGet(@ApiParam(value = "The name of the category to return.") @Valid @RequestParam(value = "name", required = false) String name
,@ApiParam(value = "The description of the category to return.") @Valid @RequestParam(value = "description", required = false) String description
);

    @ApiOperation(value = "Deletes a category by ID.", nickname = "menuCategoriesIdDelete", notes = "Endpoint where a category can be deleted by specifying its ID.", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Empty response"),
        @ApiResponse(code = 404, message = "Could not find the category requested", response = RestError.class) })
    @RequestMapping(value = "/menu/categories/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> menuCategoriesIdDelete(@ApiParam(value = "The ID of the category to delete.",required=true) @PathVariable("id") Integer id
,@NotNull @ApiParam(value = "The access token given to the authenticated user.", required = true) @Valid @RequestParam(value = "access_token", required = true) String accessToken
);

    @ApiOperation(value = "Returns a category by ID.", nickname = "menuCategoriesIdGet", notes = "Endpoint where a category can be obtained by specifying its ID.", response = Category.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A JSON representation of the category", response = Category.class),
        @ApiResponse(code = 404, message = "Could not find the ingredient requested", response = RestError.class) })
    @RequestMapping(value = "/menu/categories/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Category> menuCategoriesIdGet(@ApiParam(value = "The ID of the category to return.",required=true) @PathVariable("id") Integer id
);

    @ApiOperation(value = "Updates a category by ID.", nickname = "menuCategoriesIdPut", notes = "Endpoint where a category can be updated by specifying its ID.", response = Category.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A JSON representation of the updated category", response = Category.class),
        @ApiResponse(code = 404, message = "Could not find the category requested", response = RestError.class) })
    @RequestMapping(value = "/menu/categories/{id}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Category> menuCategoriesIdPut(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Category body
,@NotNull @ApiParam(value = "The access token given to the authenticated user.", required = true) @Valid @RequestParam(value = "access_token", required = true) String accessToken
,@ApiParam(value = "The ID of the category to update.",required=true) @PathVariable("id") Integer id
);

    @ApiOperation(value = "Creates a category", nickname = "menuCategoriesPost", notes = "", response = Category.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Created", response = IngredientFood.class) })
    @RequestMapping(value = "/menu/categories",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Category> menuCategoriesPost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Category body
,@NotNull @ApiParam(value = "The access token given to the authenticated user.", required = true) @Valid @RequestParam(value = "access_token", required = true) String accessToken
);

}
