package net.skellatex.nethelurgy;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.skellatex.nethelurgy.block.NBlocks;
import net.skellatex.nethelurgy.item.NItems;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.skellatex.nethelurgy.potion.NMobEffects;
import net.skellatex.nethelurgy.util.NAttributes;
import org.slf4j.Logger;

@Mod(Nethelurgy.MOD_ID)
public class Nethelurgy {
    public static final String MOD_ID = "nethelurgy";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static ResourceLocation modLoc(String location) {
        return new ResourceLocation(MOD_ID, location);
    }

    public Nethelurgy() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        NItems.register(modEventBus);
        NBlocks.register(modEventBus);
        NAttributes.ATTRIBUTES.register(modEventBus);
        NMobEffects.DEF_REG.register(modEventBus);
        NMobEffects.POTION_DEF_REG.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            NMobEffects.setup();
        });
    }


    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }

    }
}