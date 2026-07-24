package net.skellatex.nethelurgy.registry;

import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.item.*;
import net.minecraftforge.fml.ModList;
import net.skellatex.nethelurgy.Nethelurgy;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.skellatex.nethelurgy.compat.FarmersDelightCompat;
import net.skellatex.nethelurgy.content.item.*;

import java.util.function.Function;
import java.util.function.Supplier;

import static net.skellatex.nethelurgy.compat.ModCompat.FARMERS_DELIGHT_ID;

public class NItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Nethelurgy.MOD_ID);

    // Materials
    public static final RegistryObject<Item> RAW_TUNGSTEN = ITEMS.register("raw_tungsten",
            () -> new Item(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> TUNGSTEN_INGOT = ITEMS.register("tungsten_ingot",
            () -> new Item(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> TUNGSTEN_NUGGET = ITEMS.register("tungsten_nugget",
            () -> new Item(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> IGNITE = ITEMS.register("ignite",
            () -> new FuelItem(new Item.Properties(), 2000));
    public static final RegistryObject<Item> RED_NETHER_BRICK = ITEMS.register("red_nether_brick",
            () -> new Item(new Item.Properties()));

    // Tungsten Equipment
    public static final RegistryObject<Item> TUNGSTEN_SWORD = ITEMS.register("tungsten_sword",
            () -> new SwordItem(NToolTiers.TUNGSTEN,6, -2.6F, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> TUNGSTEN_PICKAXE = ITEMS.register("tungsten_pickaxe",
            () -> new PickaxeItem(NToolTiers.TUNGSTEN,4, -3F, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> TUNGSTEN_AXE = ITEMS.register("tungsten_axe",
            () -> new AxeItem(NToolTiers.TUNGSTEN,9, -3.2F, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> TUNGSTEN_SHOVEL = ITEMS.register("tungsten_shovel",
            () -> new ShovelItem(NToolTiers.TUNGSTEN,4.5F, -3.1F, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> TUNGSTEN_HOE = ITEMS.register("tungsten_hoe",
            () -> new HoeItem(NToolTiers.TUNGSTEN,0, -2F, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> TUNGSTEN_HELMET = ITEMS.register("tungsten_helmet",
            () -> new TungstenArmorItem(NArmorMaterials.TUNGSTEN, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> TUNGSTEN_CHESTPLATE = ITEMS.register("tungsten_chestplate",
            () -> new TungstenArmorItem(NArmorMaterials.TUNGSTEN, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> TUNGSTEN_LEGGINGS = ITEMS.register("tungsten_leggings",
            () -> new TungstenArmorItem(NArmorMaterials.TUNGSTEN, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> TUNGSTEN_BOOTS = ITEMS.register("tungsten_boots",
            () -> new TungstenArmorItem(NArmorMaterials.TUNGSTEN, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> TUNGSTEN_HORSE_ARMOR = ITEMS.register("tungsten_horse_armor",
            () -> new TungstenHorseArmorItem(new Item.Properties().fireResistant().stacksTo(1)));

    public static final RegistryObject<Item> TUNGSTEN_BOLT = ITEMS.register("tungsten_bolt",
            () -> new TungstenBoltItem(new Item.Properties().fireResistant()));

    // Misc.
    public static final RegistryObject<Item> FLAME_BANNER_PATTERN = ITEMS.register("flame_banner_pattern",
            () -> new BannerPatternItem(NBannerPatternTags.FLAME, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> FIREBOMB_MINECART = ITEMS.register("firebomb_minecart",
            () -> new FirebombMinecartItem(AbstractMinecart.Type.TNT, NEntityTypes.FIREBOMB_MINECART));
    public static final RegistryObject<Item> LIGHTER = ITEMS.register("lighter",
            () -> new FlintAndSteelItem(new Item.Properties().durability(0).fireResistant()));

    // Compat
    public static final RegistryObject<Item> CRUSHED_RAW_TUNGSTEN = ITEMS.register("crushed_raw_tungsten",
            () -> new Item(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> RAW_TUNGSTEN_NUGGET = ITEMS.register("raw_tungsten_nugget",
            () -> new Item(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> TUNGSTEN_KNIFE = ITEMS.register("tungsten_knife",
            compat(FARMERS_DELIGHT_ID, it -> FarmersDelightCompat.KNIFE_FACTORY_TUNGSTEN.apply(it), new Item.Properties().fireResistant()));

    // Unused
    public static final RegistryObject<Item> SPECTER_INGOT = ITEMS.register("specter_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SPECTER_SWORD = ITEMS.register("specter_sword",
            () -> new SwordItem(NToolTiers.SPECTER,6, -2.4F, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> SPECTER_PICKAXE = ITEMS.register("specter_pickaxe",
            () -> new PickaxeItem(NToolTiers.SPECTER,4, -2.8F, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> SPECTER_AXE = ITEMS.register("specter_axe",
            () -> new AxeItem(NToolTiers.SPECTER,9, -3F, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> SPECTER_SHOVEL = ITEMS.register("specter_shovel",
            () -> new ShovelItem(NToolTiers.SPECTER,4.5F, -2.9F, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> SPECTER_HOE = ITEMS.register("specter_hoe",
            () -> new HoeItem(NToolTiers.SPECTER,0, 0, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> SPECTER_HELMET = ITEMS.register("specter_helmet",
            () -> new SpecterArmorItem(NArmorMaterials.SPECTER, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> SPECTER_CHESTPLATE = ITEMS.register("specter_chestplate",
            () -> new SpecterArmorItem(NArmorMaterials.SPECTER, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> SPECTER_LEGGINGS = ITEMS.register("specter_leggings",
            () -> new SpecterArmorItem(NArmorMaterials.SPECTER, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> SPECTER_BOOTS = ITEMS.register("specter_boots",
            () -> new SpecterArmorItem(NArmorMaterials.SPECTER, ArmorItem.Type.BOOTS, new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    public static Supplier<? extends Item> compat(String modid, Function<Item.Properties, ? extends Item> supplier, Item.Properties properties) {
        if (ModList.get().isLoaded(modid)) return () -> supplier.apply(properties);
        return () -> new Item(properties);
    }
}