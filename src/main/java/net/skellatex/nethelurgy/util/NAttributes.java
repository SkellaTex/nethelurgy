package net.skellatex.nethelurgy.util;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.skellatex.nethelurgy.Nethelurgy;

public class NAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, Nethelurgy.MOD_ID);

    public static final RegistryObject<Attribute> FIRE_RESISTANCE = register("fire_protection", 0.0D, 0.0D, 30.0D);

    private static RegistryObject<Attribute> register(String name, double defaultValue, double minimumValue, double maximumValue) {
        return ATTRIBUTES.register(name, () -> new RangedAttribute("attribute." + Nethelurgy.MOD_ID + ".name.generic." + name, defaultValue, minimumValue, maximumValue));
    }
}