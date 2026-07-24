package net.skellatex.nethelurgy.world.event;

import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.skellatex.nethelurgy.NConfig;
import net.skellatex.nethelurgy.Nethelurgy;
import net.skellatex.nethelurgy.registry.NAttributes;
import net.skellatex.nethelurgy.registry.NItems;
import net.skellatex.nethelurgy.registry.NTags;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = Nethelurgy.MOD_ID)
public class NEvents {

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        LivingEntity target = event.getEntity();
        DamageSource source = event.getSource();

        if (source.is(DamageTypeTags.IS_FIRE)) {
            float fireResistance = 0.0F;
            for (EquipmentSlot slot : EquipmentSlot.values()) {
                if (slot.getType() == EquipmentSlot.Type.ARMOR) {
                    ItemStack stack = target.getItemBySlot(slot);
                    Collection<AttributeModifier> fireRes = stack.getAttributeModifiers(slot).get(NAttributes.FIRE_RESISTANCE.get());
                    if (!fireRes.isEmpty()) {
                        fireResistance += fireRes.stream().mapToDouble(AttributeModifier::getAmount).sum();
                    }
                }
            }
            if (fireResistance > 0.0F) {
                event.setAmount(event.getAmount() - event.getAmount() * fireResistance);
                if (fireResistance > 0.55F) {
                    event.getEntity().clearFire();
                }
            }
        }

        if (source.is(NTags.DamageTypes.IS_MAGIC)) {
            float magicResistance = 0.0F;
            for (EquipmentSlot slot : EquipmentSlot.values()) {
                if (slot.getType() == EquipmentSlot.Type.ARMOR) {
                    ItemStack stack = target.getItemBySlot(slot);
                    Collection<AttributeModifier> fireRes = stack.getAttributeModifiers(slot).get(NAttributes.MAGIC_RESISTANCE.get());
                    if (!fireRes.isEmpty()) {
                        magicResistance += fireRes.stream().mapToDouble(AttributeModifier::getAmount).sum();
                    }
                }
            }
            if (magicResistance > 0.0F) {
                event.setAmount(event.getAmount() - event.getAmount() * magicResistance);
            }
        }
    }

    @SubscribeEvent
    public static void onBlockBreak(final BlockEvent.BreakEvent event) {
        if (NConfig.HOT_BLOCK_BURNING.get() && (event.getState().is(NTags.Blocks.BURN_ON_BREAK)))
            event.getPlayer().setSecondsOnFire(4);
    }

    @SubscribeEvent
    public static void onLivingAttack(LivingAttackEvent event) {
        LivingEntity entity = event.getEntity();
        if (event.getSource().is(DamageTypes.HOT_FLOOR)) {
            ItemStack boots = entity.getItemBySlot(EquipmentSlot.FEET);
            if (boots.is(NTags.Items.HOT_FLOOR_IMMUNE)) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onItemModify(ItemAttributeModifierEvent event) {
        ItemStack stack = event.getItemStack();
        EquipmentSlot slot = event.getSlotType();

        ArmorItem.Type type = switch (slot) {
            case HEAD -> ArmorItem.Type.HELMET;
            case CHEST -> ArmorItem.Type.CHESTPLATE;
            case LEGS -> ArmorItem.Type.LEGGINGS;
            case FEET -> ArmorItem.Type.BOOTS;
            case MAINHAND, OFFHAND -> null;
        };

        if (type != null) {
            UUID uuid = ArmorItem.ARMOR_MODIFIER_UUID_PER_TYPE.get(type);

            if (NConfig.NETHERITE_ARMOR_FIRE_RESISTANCE.get() && (stack.is(Items.NETHERITE_HELMET) && slot == EquipmentSlot.HEAD || stack.is(Items.NETHERITE_BOOTS) && slot == EquipmentSlot.FEET || stack.is(Items.NETHERITE_CHESTPLATE) && slot == EquipmentSlot.CHEST || stack.is(Items.NETHERITE_LEGGINGS) && slot == EquipmentSlot.LEGS)) {
                event.addModifier(NAttributes.FIRE_RESISTANCE.get(), new AttributeModifier(uuid, "Fire Resistance", 0.1D, AttributeModifier.Operation.MULTIPLY_BASE));
            }

            if (NConfig.GOLD_ARMOR_MAGIC_RESISTANCE.get() && (stack.is(Items.GOLDEN_HELMET) && slot == EquipmentSlot.HEAD || stack.is(Items.GOLDEN_BOOTS) && slot == EquipmentSlot.FEET || stack.is(Items.GOLDEN_CHESTPLATE) && slot == EquipmentSlot.CHEST || stack.is(Items.GOLDEN_LEGGINGS) && slot == EquipmentSlot.LEGS)) {
                event.addModifier(NAttributes.MAGIC_RESISTANCE.get(), new AttributeModifier(uuid, "Magic Resistance", 0.15D, AttributeModifier.Operation.MULTIPLY_BASE));
            }
        }
        if (slot == EquipmentSlot.MAINHAND) {
            if (stack.is(NTags.Items.SPECTER_TOOLS)) {
            event.addModifier(ForgeMod.ENTITY_REACH.get(),  new AttributeModifier(UUID.fromString("39cb4eb4-d06d-47a4-b374-afb0adf642e3"), "Entity Reach", 1.0F, AttributeModifier.Operation.ADDITION));
            event.addModifier(ForgeMod.BLOCK_REACH.get(),  new AttributeModifier(UUID.fromString("d5ca2e42-ba52-4615-bfbe-3d7048861a17"), "Block Reach", 1.0F, AttributeModifier.Operation.ADDITION));
            }
        }
    }

    private static final Supplier<Item> HELMET = NItems.SPECTER_HELMET;
    private static final Supplier<Item> CHESTPLATE = NItems.SPECTER_CHESTPLATE;
    private static final Supplier<Item> LEGGINGS = NItems.SPECTER_LEGGINGS;
    private static final Supplier<Item> BOOTS = NItems.SPECTER_BOOTS;

    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.level().isClientSide()) return;
        EquipmentSlot[] slots = {EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};
        Item[] requiredItems = {HELMET.get(), CHESTPLATE.get(), LEGGINGS.get(), BOOTS.get()};

        boolean hasFullSet = true;
        for (int i = 0; i < slots.length; i++) {
            ItemStack stack = entity.getItemBySlot(slots[i]);
            if (stack.getItem() != requiredItems[i]) {
                hasFullSet = false;
                break;
            }
        }
        if (hasFullSet) {
            List<MobEffectInstance> effectsToRemove = new ArrayList<>();
            for (MobEffectInstance effect : entity.getActiveEffects()) {
                if (!effect.getEffect().isBeneficial()) {
                    effectsToRemove.add(effect);
                }
            }
            for (MobEffectInstance effect : effectsToRemove) {
                entity.removeEffect(effect.getEffect());
            }
        }
    }
}