package pv.springframework.bootstrap;

import pv.springframework.domain.*;
import pv.springframework.repositories.CategoryRepository;
import pv.springframework.repositories.RecipeRepository;
import pv.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pv.springframework.domain.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes() {

        List<Recipe> recipes = new ArrayList<>(2);

        //get UOMs
        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");

        if(!eachUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

        if(!tableSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        if(!teaSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");

        if(!dashUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");

        if(!pintUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> cupsUomOptional = unitOfMeasureRepository.findByDescription("Cup");

        if(!cupsUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> gramUomOptional = unitOfMeasureRepository.findByDescription("Gram");

        if(!gramUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        //get optionals
        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure teaSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure pintUom = dashUomOptional.get();
        UnitOfMeasure cupsUom = cupsUomOptional.get();
        UnitOfMeasure gramUom = gramUomOptional.get();

        //get Categories
        Optional<Category> spanishCategoryOptional = categoryRepository.findByDescription("Spanish");

        if(!spanishCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category Not Found");
        }

        Optional<Category> italianCategoryOptional = categoryRepository.findByDescription("Italian");

        if(!italianCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category Not Found");
        }

        Category spanishCategory = spanishCategoryOptional.get();
        Category italianCategory = italianCategoryOptional.get();

        //Spanish tuna pasta bake
        Recipe tunaRecipe = new Recipe();
        tunaRecipe.setDescription("Spanish tuna pasta bake");
        tunaRecipe.setPrepTime(10);
        tunaRecipe.setCookTime(35);
        tunaRecipe.setServings(4);
        tunaRecipe.setSource("https://www.taste.com.au");
        tunaRecipe.setUrl("https://www.taste.com.au/recipes/spanish-tuna-pasta-bake/925b912b-d556-4827-bae2-64e5bef17b23");
        tunaRecipe.setDifficulty(Difficulty.EASY);
        tunaRecipe.setDirections("Step 1\n" +
                "Preheat oven to 220°C/200°C fan-forced. Lightly grease an 8 cup-capacity baking dish. Cook pasta in a saucepan of boiling salted water, following packet directions, until tender. Drain.\n" +
                "Step 2\n" +
                "Meanwhile, heat oil in a large frying pan over medium heat. Add onion and capsicum. Cook, stirring, for 5 minutes or until onion has softened. Add garlic. Cook, stirring, for 1 minute or until fragrant.\n" +
                "Step 3\n" +
                "Add tomato. Bring to the boil. Reduce heat to medium. Cook for 10 minutes or until sauce has thickened. Add pasta, olive and tuna. Toss to combine. Season with pepper. Spoon mixture into prepared dish. Top with mozzarella and parmesan. Bake for 12 to 15 minutes or until cheese is melted and golden. Serve.");

        Notes tunaNotes = new Notes();
        tunaNotes.setRecipeNotes("To freeze: Cool. Cover with plastic wrap, then foil. Freeze for up to 2 months. Thaw in fridge overnight.");

        tunaRecipe.setNotes(tunaNotes);

        tunaRecipe.addIngredient(new Ingredient("dried large pasta shells", new BigDecimal(250), gramUom));
        tunaRecipe.addIngredient(new Ingredient("olive oil", new BigDecimal(1), tableSpoonUom));
        tunaRecipe.addIngredient(new Ingredient("medium brown onion, halved, thinly sliced", new BigDecimal(1), eachUom));
        tunaRecipe.addIngredient(new Ingredient("large red capsicum, thinly sliced", new BigDecimal(1), eachUom));
        tunaRecipe.addIngredient(new Ingredient("garlic cloves, crushed", new BigDecimal(2), eachUom));
        tunaRecipe.addIngredient(new Ingredient("chopped tomatoes", new BigDecimal(800), gramUom));
        tunaRecipe.addIngredient(new Ingredient("pitted green olives, chopped", new BigDecimal(".5"), cupsUom));
        tunaRecipe.addIngredient(new Ingredient("tuna in oil, drained, flaked", new BigDecimal(370), gramUom));
        tunaRecipe.addIngredient(new Ingredient("cup grated mozzarella cheese", new BigDecimal(".75"), cupsUom));
        tunaRecipe.addIngredient(new Ingredient("cup finely grated parmesan cheese", new BigDecimal(".33"), cupsUom));

        tunaRecipe.getCategories().add(spanishCategory);
        tunaRecipe.getCategories().add(italianCategory);

        //add to return list
        recipes.add(tunaRecipe);

        //Italian-style roast pork in milk
        Recipe porkRecipe = new Recipe();
        porkRecipe.setDescription("Italian-style roast pork in milk");
        porkRecipe.setCookTime(170);
        porkRecipe.setPrepTime(20);
        porkRecipe.setServings(8);
        porkRecipe.setSource("https://www.taste.com.au");
        porkRecipe.setUrl("https://www.taste.com.au/recipes/italian-style-roast-pork-milk/e713d29f-0681-4fbe-9592-1556e72907aa");
        porkRecipe.setDifficulty(Difficulty.KIND_OF_HARD);

        porkRecipe.setDirections("Step 1\n" +
                "Preheat oven to 240C. Heat the oil in a frying pan over medium heat. Cook pork, turning, for 5 mins or until browned. Transfer to a baking pan with garlic, thyme, shallot and lemon rind.\n" +
                "Step 2\n" +
                "Add enough milk to the baking pan to just cover the pork, leaving the rind uncovered (you may not need all the milk at this stage). Transfer pork to oven. Roast, uncovered, for 30 mins.\n" +
                "Step 3\n" +
                "Reduce oven to 170C. Roast, topping up milk when needed, for 2 hours or until meat is tender and the rind is crisp. Transfer to a serving plate. Cover loosely with foil and set aside for 10 mins to rest.\n" +
                "Step 4\n" +
                "Meanwhile, strain milk mixture into a saucepan, pressing garlic through sieve into milk mixture. Discard solids.\n" +
                "Step 5\n" +
                "Add the cream to the milk mixture. Bring to the boil over high heat. Cook, stirring occasionally, for 10 mins or until sauce thickens and reduces.\n" +
                "Step 6\n" +
                "Heat a barbecue grill or chargrill on high. Lightly brush pear wedges with a little extra oil. Cook for 2 mins each side or until lightly charred.\n" +
                "Step 7\n" +
                "Arrange the pork and pear on a serving platter. Sprinkle with sage. Cut into slices and serve with the sauce.");

        Notes porkNotes = new Notes();
        porkNotes.setRecipeNotes("Serves 8 with leftovers. Tip: You can also make this with a 1.8kg Coles Australian Pork Shoulder Roast – the cooking time remains the same.");

        porkRecipe.setNotes(porkNotes);

        porkRecipe.addIngredient(new Ingredient("olive oil", new BigDecimal(1), tableSpoonUom));
        porkRecipe.addIngredient(new Ingredient("Coles Australian Pork Loin Roast", new BigDecimal(1100), gramUom));
        porkRecipe.addIngredient(new Ingredient("whole garlic bulb, cloves separated, unpeeledo", new BigDecimal(1), eachUom));
        porkRecipe.addIngredient(new Ingredient("thyme sprigs", new BigDecimal(4), eachUom));
        porkRecipe.addIngredient(new Ingredient("6 shallots, halved", new BigDecimal(6), eachUom));
        porkRecipe.addIngredient(new Ingredient("2cm-wide strips lemon rind", new BigDecimal(3), eachUom));
        porkRecipe.addIngredient(new Ingredient("milk", new BigDecimal(2), cupsUom));
        porkRecipe.addIngredient(new Ingredient("Bulla Cooking Cream", new BigDecimal(2), tableSpoonUom));
        porkRecipe.addIngredient(new Ingredient("Packham pears, cut into wedges", new BigDecimal(2), eachUom));

        porkRecipe.getCategories().add(italianCategory);

        recipes.add(porkRecipe);
        return recipes;
    }
}
