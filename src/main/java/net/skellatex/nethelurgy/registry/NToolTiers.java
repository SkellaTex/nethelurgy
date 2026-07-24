package net.skellatex.nethelurgy.registry;

import net.skellatex.nethelurgy.Nethelurgy;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class NToolTiers {
    public static final Tier TUNGSTEN = TierSortingRegistry.registerTier(
            new ForgeTier(3, 750, 5f, 0f, 12,
                    NTags.Blocks.NEEDS_TUNGSTEN_TOOL, () -> Ingredient.of(NItems.TUNGSTEN_INGOT.get())),
            new ResourceLocation(Nethelurgy.MOD_ID, "tungsten"), List.of(Tiers.DIAMOND), List.of());

    public static final Tier SPECTER = TierSortingRegistry.registerTier(
            new ForgeTier(3, 900, 5f, 0f, 25,
                    NTags.Blocks.NEEDS_TUNGSTEN_TOOL, () -> Ingredient.of(NItems.SPECTER_INGOT.get())),
            new ResourceLocation(Nethelurgy.MOD_ID, "specter"), List.of(Tiers.DIAMOND), List.of());
}