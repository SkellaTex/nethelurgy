package net.skellatex.nethelurgy;

import net.minecraftforge.fml.ModList;
import net.skellatex.nethelurgy.block.NBlocks;
import net.skellatex.nethelurgy.item.NItems;
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

import static net.skellatex.nethelurgy.ModCompat.CREATE_ID;

@EventBusSubscriber(modid = Nethelurgy.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class NTabs {

    @SubscribeEvent
    public static void buildCreativeModeTabContents(BuildCreativeModeTabContentsEvent event) {
        ResourceKey<CreativeModeTab> tab = event.getTabKey();
        MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> entries = event.getEntries();

        if (tab == CreativeModeTabs.BUILDING_BLOCKS) {
            putAfter(entries, Blocks.NETHERRACK, NBlocks.PACKED_NETHERRACK);
            putAfter(entries, Blocks.NETHER_BRICK_FENCE, NBlocks.NETHER_BRICK_FENCE_GATE);
            putAfter(entries, Blocks.RED_NETHER_BRICKS, NBlocks.CRACKED_RED_NETHER_BRICKS);
            putAfter(entries, Blocks.RED_NETHER_BRICK_WALL, NBlocks.RED_NETHER_BRICK_FENCE);
            putAfter(entries, NBlocks.RED_NETHER_BRICK_FENCE.get(), NBlocks.RED_NETHER_BRICK_FENCE_GATE);
            putAfter(entries, NBlocks.RED_NETHER_BRICK_FENCE_GATE.get(), NBlocks.CHISELED_RED_NETHER_BRICKS);
            putBefore(entries, Blocks.DIAMOND_BLOCK, NBlocks.TUNGSTEN_BLOCK);
            putAfter(entries, NBlocks.TUNGSTEN_BLOCK.get(), NBlocks.CUT_TUNGSTEN);
            putAfter(entries, NBlocks.CUT_TUNGSTEN.get(), NBlocks.CUT_TUNGSTEN_STAIRS);
            putAfter(entries, NBlocks.CUT_TUNGSTEN_STAIRS.get(), NBlocks.CUT_TUNGSTEN_SLAB);
            putAfter(entries, Blocks.LAPIS_BLOCK, NBlocks.IGNITE_BLOCK);
        }

        if (tab == CreativeModeTabs.NATURAL_BLOCKS) {
            putAfter(entries, Blocks.RAW_GOLD_BLOCK, NBlocks.RAW_TUNGSTEN_BLOCK);
            putAfter(entries, Blocks.NETHER_QUARTZ_ORE, NBlocks.NETHER_IGNITE_ORE);
            putAfter(entries, NBlocks.NETHER_IGNITE_ORE.get(), NBlocks.NETHER_TUNGSTEN_ORE);
            putAfter(entries, Blocks.DEEPSLATE_IRON_ORE, NBlocks.BASALT_IRON_ORE);
        }

        if (tab == CreativeModeTabs.INGREDIENTS) {
            putAfter(entries, Items.NETHER_BRICK, NItems.RED_NETHER_BRICK);
            putAfter(entries, Items.RAW_GOLD, NItems.RAW_TUNGSTEN);
            putAfter(entries, Items.GOLD_NUGGET, NItems.TUNGSTEN_NUGGET);
            putAfter(entries, Items.GOLD_INGOT, NItems.TUNGSTEN_INGOT);
            putAfter(entries, Items.QUARTZ, NItems.IGNITE);
            if (ModList.get().isLoaded(CREATE_ID))  {
                putAfter(entries, NItems.RAW_TUNGSTEN.get(), NItems.CRUSHED_RAW_TUNGSTEN);
            }
        }

        if (tab == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            putBefore(entries, Items.DIAMOND_SHOVEL, NItems.TUNGSTEN_SHOVEL);
            putAfter(entries, NItems.TUNGSTEN_SHOVEL.get(), NItems.TUNGSTEN_PICKAXE);
            putAfter(entries, NItems.TUNGSTEN_PICKAXE.get(), NItems.TUNGSTEN_AXE);
            putAfter(entries, NItems.TUNGSTEN_AXE.get(), NItems.TUNGSTEN_HOE);
        }

        if (tab == CreativeModeTabs.COMBAT) {
            putBefore(entries, Items.DIAMOND_SWORD, NItems.TUNGSTEN_SWORD);
            putBefore(entries, Items.DIAMOND_AXE, NItems.TUNGSTEN_AXE);
            putBefore(entries, Items.DIAMOND_HELMET, NItems.TUNGSTEN_HELMET);
            putAfter(entries, NItems.TUNGSTEN_HELMET.get(), NItems.TUNGSTEN_CHESTPLATE);
            putAfter(entries, NItems.TUNGSTEN_CHESTPLATE.get(), NItems.TUNGSTEN_LEGGINGS);
            putAfter(entries, NItems.TUNGSTEN_LEGGINGS.get(), NItems.TUNGSTEN_BOOTS);
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