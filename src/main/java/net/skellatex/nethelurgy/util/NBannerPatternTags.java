package net.skellatex.nethelurgy.util;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.skellatex.nethelurgy.Nethelurgy;

public final class NBannerPatternTags {
    public static final TagKey<BannerPattern> FLAME = createTag("flame");

    private static TagKey<BannerPattern> createTag(String name) {
        return TagKey.create(Registries.BANNER_PATTERN, new ResourceLocation(Nethelurgy.MOD_ID, "pattern_item/" +name));
    }

}
