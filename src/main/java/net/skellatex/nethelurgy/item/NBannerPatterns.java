package net.skellatex.nethelurgy.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.skellatex.nethelurgy.Nethelurgy;

public final class NBannerPatterns {
    public static final DeferredRegister<BannerPattern> BANNER_PATTERNS = DeferredRegister.create(Registries.BANNER_PATTERN, Nethelurgy.MOD_ID);

    public static final RegistryObject<BannerPattern> FLAME = BANNER_PATTERNS.register("flame", () -> new BannerPattern("fla"));
}
