package net.skellatex.nethelurgy.potion;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.skellatex.nethelurgy.Nethelurgy;

public class NMobEffects {

    public static final DeferredRegister<MobEffect> DEF_REG = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Nethelurgy.MOD_ID);
    public static final DeferredRegister<Potion> POTION_DEF_REG = DeferredRegister.create(ForgeRegistries.POTIONS, Nethelurgy.MOD_ID);

    public static final RegistryObject<Potion> DECAY_POTION = POTION_DEF_REG.register("decay", () -> new Potion(new MobEffectInstance(MobEffects.WITHER, 900)));
    public static final RegistryObject<Potion> LONG_DECAY_POTION = POTION_DEF_REG.register("long_decay", () -> new Potion(new MobEffectInstance(MobEffects.WITHER, 1800)));
    public static final RegistryObject<Potion> STRONG_DECAY_POTION = POTION_DEF_REG.register("strong_decay", () -> new Potion(new MobEffectInstance(MobEffects.WITHER, 420, 1)));

    public static void setup() {

        BrewingRecipeRegistry.addRecipe(new ProperBrewingRecipe(Ingredient.of(createPotion(Potions.AWKWARD)), Ingredient.of(Items.WITHER_ROSE), createPotion(DECAY_POTION)));
        BrewingRecipeRegistry.addRecipe(new ProperBrewingRecipe(Ingredient.of(createPotion(DECAY_POTION)), Ingredient.of(Items.REDSTONE), createPotion(LONG_DECAY_POTION)));
        BrewingRecipeRegistry.addRecipe(new ProperBrewingRecipe(Ingredient.of(createPotion(DECAY_POTION)), Ingredient.of(Items.GLOWSTONE_DUST), createPotion(STRONG_DECAY_POTION)));

    }

    public static ItemStack createPotion(RegistryObject<Potion> potion) {
        return createPotion(potion.get());
    }

    public static ItemStack createPotion(Potion potion) {
        return PotionUtils.setPotion(new ItemStack(Items.POTION), potion);
    }

    public static ItemStack createSplashPotion(Potion potion) {
        return PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), potion);
    }

    public static ItemStack createLingeringPotion(Potion potion) {
        return PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION), potion);
    }
}