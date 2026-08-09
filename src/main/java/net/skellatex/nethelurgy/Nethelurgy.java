package net.skellatex.nethelurgy;

import com.mojang.logging.LogUtils;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.skellatex.nethelurgy.content.entity.TungstenBoltEntity;
import net.skellatex.nethelurgy.content.misc.CustomTNTDispenseBehavior;
import net.skellatex.nethelurgy.registry.*;
import net.skellatex.nethelurgy.content.enchantment.NEnchantments;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.skellatex.nethelurgy.content.potion.NMobEffects;
import net.skellatex.nethelurgy.world.event.ForgeEvents;
import net.skellatex.nethelurgy.world.loot.NLootModifiers;
import org.slf4j.Logger;

@Mod(Nethelurgy.MOD_ID)
public class Nethelurgy {
    public static final String MOD_ID = "nethelurgy";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Nethelurgy() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        NItems.register(modEventBus);
        NBlocks.register(modEventBus);
        NAttributes.ATTRIBUTES.register(modEventBus);
        NMobEffects.MOB_EFFECTS.register(modEventBus);
        NMobEffects.POTIONS.register(modEventBus);
        NEnchantments.register(modEventBus);
        NLootModifiers.register(modEventBus);
        NBannerPatterns.BANNER_PATTERNS.register(modEventBus);
        NEntityTypes.register(modEventBus);
        NSoundEvents.register(modEventBus);
        NRecipeTypes.RECIPES.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new ForgeEvents());

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, NConfig.SPEC, "nethelurgy-common.toml");
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            NMobEffects.setup();
            NFlammables.register();
            NRecipeTypes.setup();
            DispenserBlock.registerBehavior(NItems.TUNGSTEN_BOLT.get(), new AbstractProjectileDispenseBehavior() {
                protected Projectile getProjectile(Level level, Position pos, ItemStack stack) {
                    var entity = new TungstenBoltEntity(NEntityTypes.TUNGSTEN_BOLT.get(), level, pos);
                    entity.pickup = AbstractArrow.Pickup.ALLOWED;
                    return entity;
                }
            });
            DispenserBlock.registerBehavior(
                    NBlocks.FIREBOMB.get(), // Register against the ITEM, not the Block
                    new CustomTNTDispenseBehavior()
            );
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