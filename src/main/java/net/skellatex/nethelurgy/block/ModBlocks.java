package net.skellatex.nethelurgy.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.*;
import net.skellatex.nethelurgy.Nethelurgy;
import net.skellatex.nethelurgy.item.FuelItem;
import net.skellatex.nethelurgy.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Nethelurgy.MOD_ID);

    public static final RegistryObject<Block> NETHER_IGNITE_ORE = registerBlock("nether_ignite_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.NETHER_QUARTZ_ORE).sound(SoundType.NETHER_ORE)
                    .strength(3f, 3f).requiresCorrectToolForDrops(), UniformInt.of(1, 3)));

    public static final RegistryObject<Block> PACKED_NETHERRACK = registerBlock("packed_netherrack",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERRACK)
                    .strength(3.6f, 3.6f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> NETHER_TUNGSTEN_ORE = registerBlock("nether_tungsten_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.NETHER_GOLD_ORE).sound(SoundType.NETHER_GOLD_ORE)
                    .strength(3f, 3f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> BASALT_IRON_ORE = registerBlock("basalt_iron_ore",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.NETHER_GOLD_ORE).sound(SoundType.NETHER_GOLD_ORE)
                    .strength(3f, 3f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> IGNITE_BLOCK = registerBlock("ignite_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).sound(SoundType.METAL)
                    .strength(5f, 6f).lightLevel((state) -> 10).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> RAW_TUNGSTEN_BLOCK = registerItemPropertiesBlock("raw_tungsten_block", () ->
            new Block(BlockBehaviour.Properties.of().strength(5f, 6f).requiresCorrectToolForDrops().sound(SoundType.METAL)), new Item.Properties().fireResistant());

    public static final RegistryObject<Block> TUNGSTEN_BLOCK = registerItemPropertiesBlock("tungsten_block", () ->
            new Block(BlockBehaviour.Properties.of().strength(5f, 6f).requiresCorrectToolForDrops().sound(SoundType.METAL)), new Item.Properties().fireResistant());

    public static final RegistryObject<Block> RED_NETHER_BRICK_FENCE = registerBlock("red_nether_brick_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.RED_NETHER_BRICKS).strength(2f, 6f).requiresCorrectToolForDrops()));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<T> registerItemPropertiesBlock(String name, Supplier<T> block, Item.Properties properties) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        ModItems.ITEMS.register(name, () -> new BlockItem(toReturn.get(), properties));
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}