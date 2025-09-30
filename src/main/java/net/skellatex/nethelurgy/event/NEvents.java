package net.skellatex.nethelurgy.event;

import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.skellatex.nethelurgy.Nethelurgy;
import net.skellatex.nethelurgy.util.NAttributes;

import java.util.Collection;

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
    }
}