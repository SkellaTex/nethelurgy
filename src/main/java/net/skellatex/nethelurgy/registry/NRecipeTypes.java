package net.skellatex.nethelurgy.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.skellatex.nethelurgy.Nethelurgy;
import net.skellatex.nethelurgy.content.misc.RecipeTungstenUpgrade;

public class NRecipeTypes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPES = DeferredRegister.create(Registries.RECIPE_SERIALIZER, Nethelurgy.MOD_ID);

    public static final RegistryObject<RecipeSerializer<?>> TUNGSTEN_UPGRADE = RECIPES.register("tungsten_upgrade", () -> new SimpleCraftingRecipeSerializer<>(RecipeTungstenUpgrade::new));

    public static void setup(){
    }
}
