package net.skellatex.nethelurgy.registry;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.skellatex.nethelurgy.Nethelurgy;
import net.skellatex.nethelurgy.content.entity.MinecartFirebomb;
import net.skellatex.nethelurgy.content.entity.PrimedFirebomb;
import net.skellatex.nethelurgy.content.entity.TungstenBoltEntity;

@Mod.EventBusSubscriber(modid = Nethelurgy.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Nethelurgy.MOD_ID);

    public static final RegistryObject<EntityType<TungstenBoltEntity>> TUNGSTEN_BOLT = ENTITIES.register("tungsten_bolt",
            () -> EntityType.Builder.<TungstenBoltEntity>of(TungstenBoltEntity::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("tungsten_bolt"));

    public static final RegistryObject<EntityType<PrimedFirebomb>> PRIMED_FIREBOMB = ENTITIES.register("primed_firebomb",
            () -> EntityType.Builder.<PrimedFirebomb>of(PrimedFirebomb::new, MobCategory.MISC).fireImmune().sized(1.0F, 1.0F).clientTrackingRange(10).updateInterval(10).build("primed_firebomb"));
    public static final RegistryObject<EntityType<MinecartFirebomb>> FIREBOMB_MINECART = ENTITIES.register("firebomb_minecart",
            () -> EntityType.Builder.<MinecartFirebomb>of(MinecartFirebomb::new, MobCategory.MISC).sized(0.98F, 0.7F).clientTrackingRange(8).build("firebomb_minecart"));


    public static void register(IEventBus modBus) {
        ENTITIES.register(modBus);
    }
}
