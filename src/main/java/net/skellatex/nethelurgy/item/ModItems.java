package net.skellatex.nethelurgy.item;

import net.minecraft.world.item.*;
import net.skellatex.nethelurgy.Nethelurgy;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Nethelurgy.MOD_ID);

    public static final RegistryObject<Item> RAW_TUNGSTEN = ITEMS.register("raw_tungsten",
            () -> new Item(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> TUNGSTEN_INGOT = ITEMS.register("tungsten_ingot",
            () -> new Item(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> TUNGSTEN_NUGGET = ITEMS.register("tungsten_nugget",
            () -> new Item(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> IGNITE = ITEMS.register("ignite",
            () -> new FuelItem(new Item.Properties(), 2000));
    public static final RegistryObject<Item> CRUSHED_RAW_TUNGSTEN = ITEMS.register("crushed_raw_tungsten",
            () -> new Item(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> RED_NETHER_BRICK = ITEMS.register("red_nether_brick",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TUNGSTEN_SWORD = ITEMS.register("tungsten_sword",
            () -> new SwordItem(ModToolTiers.TUNGSTEN,6, -2.6F, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> TUNGSTEN_PICKAXE = ITEMS.register("tungsten_pickaxe",
            () -> new PickaxeItem(ModToolTiers.TUNGSTEN,4, -3F, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> TUNGSTEN_AXE = ITEMS.register("tungsten_axe",
            () -> new AxeItem(ModToolTiers.TUNGSTEN,9, -3.2F, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> TUNGSTEN_SHOVEL = ITEMS.register("tungsten_shovel",
            () -> new ShovelItem(ModToolTiers.TUNGSTEN,4.5F, -3.1F, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> TUNGSTEN_HOE = ITEMS.register("tungsten_hoe",
            () -> new HoeItem(ModToolTiers.TUNGSTEN,0, -2F, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> TUNGSTEN_HELMET = ITEMS.register("tungsten_helmet",
            () -> new ArmorItem(ModArmorMaterials.TUNGSTEN, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> TUNGSTEN_CHESTPLATE = ITEMS.register("tungsten_chestplate",
            () -> new ArmorItem(ModArmorMaterials.TUNGSTEN, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> TUNGSTEN_LEGGINGS = ITEMS.register("tungsten_leggings",
            () -> new ArmorItem(ModArmorMaterials.TUNGSTEN, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> TUNGSTEN_BOOTS = ITEMS.register("tungsten_boots",
            () -> new ArmorItem(ModArmorMaterials.TUNGSTEN, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}