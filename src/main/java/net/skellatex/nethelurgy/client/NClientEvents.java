package net.skellatex.nethelurgy.client;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.skellatex.nethelurgy.Nethelurgy;
import net.skellatex.nethelurgy.registry.NEntityTypes;
import net.skellatex.nethelurgy.registry.NItems;

@Mod.EventBusSubscriber(modid = Nethelurgy.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class NClientEvents {

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(NEntityTypes.TUNGSTEN_BOLT.get(), TungstenBoltRender::new);
        event.registerEntityRenderer(NEntityTypes.PRIMED_FIREBOMB.get(), FirebombRender::new);
        event.registerEntityRenderer(NEntityTypes.FIREBOMB_MINECART.get(), FirebombMinecartRender::new);
    }

    @SubscribeEvent
    public static void setup(FMLClientSetupEvent event) {
        event.enqueueWork(NClientEvents::registerItemProperties);
    }

    private static void registerItemProperties() {

        ItemProperties.register(Items.CROSSBOW, new ResourceLocation(Nethelurgy.MOD_ID, "tungsten_bolt"), (stack, level, user, i) ->
                CrossbowItem.isCharged(stack) && CrossbowItem.containsChargedProjectile(stack, NItems.TUNGSTEN_BOLT.get()) ? 1.0F : 0.0F
        );

    }

}
