package net.skellatex.nethelurgy;

import net.skellatex.nethelurgy.block.ModBlocks;
import net.skellatex.nethelurgy.item.ModItems;
import java.util.function.Supplier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.util.MutableHashedLinkedMap;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Nethelurgy.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModTabs {

    @SubscribeEvent
    public static void buildCreativeModeTabContents(BuildCreativeModeTabContentsEvent event) {
        ResourceKey<CreativeModeTab> tab = event.getTabKey();
        MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> entries = event.getEntries();

        if (tab == CreativeModeTabs.BUILDING_BLOCKS) {
            putAfter(entries, Blocks.NETHERRACK, ModBlocks.PACKED_NETHERRACK);
            putAfter(entries, Blocks.NETHER_BRICK_FENCE, ModBlocks.NETHER_BRICK_FENCE_GATE);
            putAfter(entries, Blocks.RED_NETHER_BRICKS, ModBlocks.CRACKED_RED_NETHER_BRICKS);
            putAfter(entries, Blocks.RED_NETHER_BRICK_WALL, ModBlocks.RED_NETHER_BRICK_FENCE);
            putAfter(entries, ModBlocks.RED_NETHER_BRICK_FENCE.get(), ModBlocks.RED_NETHER_BRICK_FENCE_GATE);
            putAfter(entries, ModBlocks.RED_NETHER_BRICK_FENCE_GATE.get(), ModBlocks.CHISELED_RED_NETHER_BRICKS);
            putBefore(entries, Blocks.DIAMOND_BLOCK, ModBlocks.TUNGSTEN_BLOCK);
            putAfter(entries, Blocks.LAPIS_BLOCK, ModBlocks.IGNITE_BLOCK);
        }

        if (tab == CreativeModeTabs.NATURAL_BLOCKS) {
            putAfter(entries, Blocks.RAW_GOLD_BLOCK, ModBlocks.RAW_TUNGSTEN_BLOCK);
            putAfter(entries, Blocks.NETHER_QUARTZ_ORE, ModBlocks.NETHER_IGNITE_ORE);
            putAfter(entries, ModBlocks.NETHER_IGNITE_ORE.get(), ModBlocks.NETHER_TUNGSTEN_ORE);
            putAfter(entries, Blocks.DEEPSLATE_IRON_ORE, ModBlocks.BASALT_IRON_ORE);
        }

        if (tab == CreativeModeTabs.INGREDIENTS) {
            putAfter(entries, Items.NETHER_BRICK, ModItems.RED_NETHER_BRICK);
            putAfter(entries, Items.RAW_GOLD, ModItems.RAW_TUNGSTEN);
            putAfter(entries, Items.GOLD_NUGGET, ModItems.TUNGSTEN_NUGGET);
            putAfter(entries, Items.GOLD_INGOT, ModItems.TUNGSTEN_INGOT);
            putAfter(entries, Items.QUARTZ, ModItems.IGNITE);
        }

        if (tab == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            putBefore(entries, Items.DIAMOND_SHOVEL, ModItems.TUNGSTEN_SHOVEL);
            putAfter(entries, ModItems.TUNGSTEN_SHOVEL.get(), ModItems.TUNGSTEN_PICKAXE);
            putAfter(entries, ModItems.TUNGSTEN_PICKAXE.get(), ModItems.TUNGSTEN_AXE);
            putAfter(entries, ModItems.TUNGSTEN_AXE.get(), ModItems.TUNGSTEN_HOE);
        }

        if (tab == CreativeModeTabs.COMBAT) {
            putBefore(entries, Items.DIAMOND_SWORD, ModItems.TUNGSTEN_SWORD);
            putBefore(entries, Items.DIAMOND_AXE, ModItems.TUNGSTEN_AXE);
            putBefore(entries, Items.DIAMOND_HELMET, ModItems.TUNGSTEN_HELMET);
            putAfter(entries, ModItems.TUNGSTEN_HELMET.get(), ModItems.TUNGSTEN_CHESTPLATE);
            putAfter(entries, ModItems.TUNGSTEN_CHESTPLATE.get(), ModItems.TUNGSTEN_LEGGINGS);
            putAfter(entries, ModItems.TUNGSTEN_LEGGINGS.get(), ModItems.TUNGSTEN_BOOTS);
        }

    }

    private static void putAfter(MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> entries, ItemLike after, Supplier<? extends ItemLike> supplier) {
        ItemLike key = supplier.get();
        if (!entries.contains(new ItemStack(after))) return;
        entries.putAfter(new ItemStack(after), new ItemStack(key), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }

    private static void putBefore(MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> entries, ItemLike before, Supplier<? extends ItemLike> supplier) {
        ItemLike key = supplier.get();
        if (!entries.contains(new ItemStack(before))) return;
        entries.putBefore(new ItemStack(before), new ItemStack(key), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }

}