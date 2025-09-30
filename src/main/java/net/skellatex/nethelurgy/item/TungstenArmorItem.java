package net.skellatex.nethelurgy.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;
import net.skellatex.nethelurgy.util.NAttributes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;

import java.util.UUID;

public class TungstenArmorItem extends ArmorItem {

    public TungstenArmorItem(ArmorMaterial material, ArmorItem.Type slot, Properties properties) {
        super(material, slot, properties);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.putAll(super.getAttributeModifiers(slot, stack));
        UUID uuid = ArmorItem.ARMOR_MODIFIER_UUID_PER_TYPE.get(this.type);
        builder.put(NAttributes.FIRE_RESISTANCE.get(), new AttributeModifier(uuid, "Fire resistance", 0.15D, AttributeModifier.Operation.MULTIPLY_BASE));
        return slot == this.getEquipmentSlot() ? builder.build() : super.getAttributeModifiers(slot, stack);
    }
}
