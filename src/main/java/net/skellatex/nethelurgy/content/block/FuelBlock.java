package net.skellatex.nethelurgy.content.block;


import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;

import net.minecraft.world.item.ItemStack;
import net.skellatex.nethelurgy.registry.NBlocks;

@Mod.EventBusSubscriber
public class FuelBlock {
    @SubscribeEvent
    public static void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
        ItemStack itemstack = event.getItemStack();
        if (itemstack.getItem() == NBlocks.IGNITE_BLOCK.get().asItem())
            event.setBurnTime(20000);
    }
}
