package net.skellatex.nethelurgy.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.List;

public class SearingEnchantment extends Enchantment {
    protected SearingEnchantment() {
        super(Rarity.VERY_RARE, SearingEnchantmentCategory.SMELTERS, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    public int getMinCost(int p_45264_) {
        return 15;
    }

    public int getMaxCost(int p_45268_) {
        return super.getMinCost(p_45268_) + 50;
    }

    public int getMaxLevel() {
        return 1;
    }

    @Override
    protected boolean checkCompatibility(Enchantment enchantment) {
        return super.checkCompatibility(enchantment) && !List.of(Enchantments.SILK_TOUCH, Enchantments.BLOCK_FORTUNE).contains(enchantment);
    }
    public boolean isTreasureOnly() {
        return true;
    }
}