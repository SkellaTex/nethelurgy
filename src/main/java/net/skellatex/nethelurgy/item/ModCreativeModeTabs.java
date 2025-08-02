package net.skellatex.nethelurgy.item;

import net.skellatex.nethelurgy.Nethelurgy;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.skellatex.nethelurgy.block.ModBlocks;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Nethelurgy.MOD_ID);

    public static final RegistryObject<CreativeModeTab> NETHELURGY_TAB = CREATIVE_MODE_TABS.register("nethelurgy_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.NETHER_IGNITE_ORE.get()))
                    .title(Component.translatable("creativetab.nethelurgy_tab"))
                    .displayItems((pParameters, pOutput) -> {

                        pOutput.accept(ModBlocks.PACKED_NETHERRACK.get());
                        pOutput.accept(ModBlocks.BASALT_IRON_ORE.get());
                        pOutput.accept(ModBlocks.NETHER_IGNITE_ORE.get());
                        pOutput.accept(ModItems.IGNITE.get());
                        pOutput.accept(ModBlocks.IGNITE_BLOCK.get());
                        pOutput.accept(ModBlocks.NETHER_TUNGSTEN_ORE.get());
                        pOutput.accept(ModItems.RAW_TUNGSTEN.get());
                        pOutput.accept(ModBlocks.RAW_TUNGSTEN_BLOCK.get());
                        pOutput.accept(ModItems.TUNGSTEN_NUGGET.get());
                        pOutput.accept(ModItems.TUNGSTEN_INGOT.get());
                        pOutput.accept(ModBlocks.TUNGSTEN_BLOCK.get());
                        pOutput.accept(ModItems.TUNGSTEN_SWORD.get());
                        pOutput.accept(ModItems.TUNGSTEN_AXE.get());
                        pOutput.accept(ModItems.TUNGSTEN_PICKAXE.get());
                        pOutput.accept(ModItems.TUNGSTEN_SHOVEL.get());
                        pOutput.accept(ModItems.TUNGSTEN_HOE.get());
                        pOutput.accept(ModItems.TUNGSTEN_HELMET.get());
                        pOutput.accept(ModItems.TUNGSTEN_CHESTPLATE.get());
                        pOutput.accept(ModItems.TUNGSTEN_LEGGINGS.get());
                        pOutput.accept(ModItems.TUNGSTEN_BOOTS.get());

                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}