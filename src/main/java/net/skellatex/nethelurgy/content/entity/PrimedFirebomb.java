package net.skellatex.nethelurgy.content.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;
import net.skellatex.nethelurgy.registry.NEntityTypes;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PrimedFirebomb extends PrimedTnt {

    private final @Nullable LivingEntity owner;

    public PrimedFirebomb(EntityType<? extends PrimedFirebomb> type, Level level) {
        super(type, level);
        this.owner = null;
    }

    public PrimedFirebomb(Level level, double d, double e, double f, @Nullable LivingEntity igniter) {
        super(NEntityTypes.PRIMED_FIREBOMB.get(), level);
        this.setPos(d, e, f);
        double g = level.getRandom().nextDouble() * Math.PI * 2F;
        setDeltaMovement(-Math.sin(g) * 0.02D, 0.2F, -Math.cos(g) * 0.02D);
        setFuse(80);
        this.xo = d;
        this.yo = e;
        this.zo = f;
        this.owner = igniter;
    }

    @Override
    public @Nullable LivingEntity getOwner() {
        return owner;
    }

    protected void explode() {
        this.level().explode(this, this.getX(), this.getY(0.0625D), this.getZ(), 4.0F, true, Level.ExplosionInteraction.TNT);

        double radius = 8.0D;

        List<Entity> list = this.level().getEntities(this,
                this.getBoundingBox().inflate(radius));

        for (Entity entity : list) {
            if (entity instanceof LivingEntity livingEntity) {
                double dist = livingEntity.distanceTo(this);
                if (dist < radius) {
                    livingEntity.setSecondsOnFire(8);
                }
            }
        }
        this.discard();
    }
}
