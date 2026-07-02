package net.skellatex.nethelurgy.content.entity;

import net.minecraft.core.Position;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.skellatex.nethelurgy.registry.NItems;
import net.skellatex.nethelurgy.registry.NSoundEvents;

public class TungstenBoltEntity extends AbstractArrow {

    public TungstenBoltEntity(EntityType<? extends TungstenBoltEntity> type, Level level) {
        super(type, level);
    }

    public TungstenBoltEntity(EntityType<? extends TungstenBoltEntity> type, Level level, Position pos) {
        super(type, pos.x(), pos.y(), pos.z(), level);
    }

    public TungstenBoltEntity(EntityType<? extends TungstenBoltEntity> type, Level level, LivingEntity user) {
        super(type, user, level);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(NItems.TUNGSTEN_BOLT.get());
    }


    @Override
    protected void onHitEntity(EntityHitResult result) {
        if (result.getEntity() instanceof LivingEntity livingTarget) {

            setBaseDamage(4);

            setSoundEvent(NSoundEvents.TUNGSTEN_BOLT_HIT.get());

            double armorValueD = livingTarget.getAttributeValue(Attributes.ARMOR);
            float armorValueF = (float) armorValueD;

            livingTarget.hurt(damageSources().generic(), armorValueF / 5);

        }

        super.onHitEntity(result);
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        setSoundEvent(NSoundEvents.TUNGSTEN_BOLT_HIT.get());
        super.onHitBlock(result);
    }

    @Override
    public void shoot(double p_37266_, double p_37267_, double p_37268_, float p_37269_, float p_37270_) {
        super.shoot(p_37266_, p_37267_, p_37268_, p_37269_ * 0.8F, p_37270_);
    }

}
