package net.skellatex.nethelurgy.enchantment;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.skellatex.nethelurgy.Nethelurgy;

public class NEnchantments {

    private static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Nethelurgy.MOD_ID);

    public static RegistryObject<Enchantment> SEARING = ENCHANTMENTS.register("searing", SearingEnchantment::new);

    public static void register(IEventBus bus) {
        ENCHANTMENTS.register(bus);
    }
}
