package net.skellatex.nethelurgy.world.loot;

import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.skellatex.nethelurgy.Nethelurgy;

public class NLootModifiers {

    private static final DeferredRegister<Codec<? extends IGlobalLootModifier>> GLM = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Nethelurgy.MOD_ID);

    public static final RegistryObject<Codec<SearingLootModifier>> SEARING_LOOT_MODIFIER = GLM.register("searing_loot_modifier", () -> SearingLootModifier.CODEC);

    static {
        GLM.register("chest_loot_modifier", ChestLootModifier.CODEC);
    }

    private NLootModifiers() {}

    public static void register(IEventBus bus) {
        GLM.register(bus);
    }
}
