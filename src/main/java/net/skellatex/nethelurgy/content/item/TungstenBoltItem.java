package net.skellatex.nethelurgy.content.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.skellatex.nethelurgy.content.entity.TungstenBoltEntity;
import net.skellatex.nethelurgy.registry.NEntityTypes;

public class TungstenBoltItem extends ArrowItem {

    public TungstenBoltItem(Properties properties) {
        super(properties);
    }

    @Override
    public AbstractArrow createArrow(Level level, ItemStack stack, LivingEntity user) {
        return new TungstenBoltEntity(NEntityTypes.TUNGSTEN_BOLT.get(), level, user);
    }


}
