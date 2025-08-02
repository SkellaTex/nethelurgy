package net.skellatex.nethelurgy.potion;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.skellatex.nethelurgy.Nethelurgy;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS
            = DeferredRegister.create(ForgeRegistries.POTIONS, Nethelurgy.MOD_ID);

    public static final RegistryObject<Potion> DECAY_POTION = POTIONS.register("decay_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.WITHER, 800, 0)));


    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}
