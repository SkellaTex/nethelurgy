package net.skellatex.nethelurgy.world.event;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.skellatex.nethelurgy.Nethelurgy;

@Mod.EventBusSubscriber(modid = Nethelurgy.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEvents {

    @SubscribeEvent
    public void onTooltip(ItemTooltipEvent event) {
        CompoundTag tag = event.getItemStack().getTag();
        if (tag != null && tag.contains("Fireproof") && tag.getBoolean("Fireproof")) {
            event.getToolTip().add(Component.translatable("item.nethelurgy.fireproof").withStyle(ChatFormatting.DARK_RED));
        }
    }
}
