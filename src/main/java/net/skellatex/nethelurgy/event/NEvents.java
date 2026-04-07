package net.skellatex.nethelurgy.event;

import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.skellatex.nethelurgy.NConfig;
import net.skellatex.nethelurgy.Nethelurgy;
import net.skellatex.nethelurgy.util.NAttributes;
import net.skellatex.nethelurgy.util.NTags;

import java.util.Collection;
import java.util.UUID;

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
    }
}