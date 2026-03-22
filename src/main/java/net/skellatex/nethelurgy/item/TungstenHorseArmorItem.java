package net.skellatex.nethelurgy.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.skellatex.nethelurgy.Nethelurgy;
import net.skellatex.nethelurgy.util.NAttributes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import java.util.UUID;

public class TungstenHorseArmorItem extends HorseArmorItem {

    public TungstenHorseArmorItem(Item.Properties builder) {
        super(9, new ResourceLocation(Nethelurgy.MOD_ID,"textures/entity/horse/armor/horse_armor_tungsten.png"), builder);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.putAll(super.getAttributeModifiers(slot, stack));
        UUID uuid = ArmorItem.ARMOR_MODIFIER_UUID_PER_TYPE.get(ArmorItem.Type.CHESTPLATE);
        builder.put(NAttributes.FIRE_RESISTANCE.get(), new AttributeModifier(uuid, "Fire Resistance", 0.5D, AttributeModifier.Operation.MULTIPLY_BASE));
        return slot == EquipmentSlot.CHEST ? builder.build() : super.getAttributeModifiers(slot, stack);
    }
}
