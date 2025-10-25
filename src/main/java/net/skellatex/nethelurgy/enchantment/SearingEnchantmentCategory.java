package net.skellatex.nethelurgy.enchantment;

import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.ForgeRegistries;

public class SearingEnchantmentCategory {
    public static final EnchantmentCategory SMELTERS = EnchantmentCategory.create("smelters", item ->
            item instanceof PickaxeItem ||
                    item instanceof AxeItem ||
                    ForgeRegistries.ITEMS.tags().getTag(ItemTags.PICKAXES).contains(item) ||
            ForgeRegistries.ITEMS.tags().getTag(ItemTags.AXES).contains(item)
    );
}
