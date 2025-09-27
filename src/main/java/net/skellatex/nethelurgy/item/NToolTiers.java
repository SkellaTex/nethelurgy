package net.skellatex.nethelurgy.item;

import net.skellatex.nethelurgy.Nethelurgy;
import net.skellatex.nethelurgy.util.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class NToolTiers {
    public static final Tier TUNGSTEN = TierSortingRegistry.registerTier(
            new ForgeTier(3, 750, 4f, 0f, 12,
                    ModTags.Blocks.NEEDS_TUNGSTEN_TOOL, () -> Ingredient.of(NItems.TUNGSTEN_INGOT.get())),
            new ResourceLocation(Nethelurgy.MOD_ID, "tungsten"), List.of(Tiers.DIAMOND), List.of());
}