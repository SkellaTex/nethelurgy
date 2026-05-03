package net.skellatex.nethelurgy.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageType;
import net.skellatex.nethelurgy.Nethelurgy;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class NTags {

    public static class Blocks {
        public static final TagKey<Block> NEEDS_TUNGSTEN_TOOL = registerBlockTag("needs_tungsten_tool");
        public static final TagKey<Block> BURN_ON_BREAK = registerBlockTag("burn_on_break");
    }

    public static class DamageTypes {
        public static final TagKey<DamageType> IS_MAGIC = registerDamageTypeTag("is_magic");
    }

    public static class Items {
        public static final TagKey<Item> TUNGSTEN_ARMOR = registerItemTag("tungsten_armor");
    }

    private static TagKey<Item> registerItemTag(String name) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Nethelurgy.MOD_ID, name));
    }

    private static TagKey<Block> registerBlockTag(String name) {
        return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Nethelurgy.MOD_ID, name));
    }

    private static TagKey<DamageType> registerDamageTypeTag(String name) {
        return TagKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(Nethelurgy.MOD_ID, name));
    }
}