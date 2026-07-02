package net.skellatex.nethelurgy.mixin;

import java.util.function.Predicate;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.skellatex.nethelurgy.registry.NTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CrossbowItem.class)
public class CrossbowItemMixin {

    @Unique
    private static final Predicate<ItemStack> HELD_PREDICATE = ProjectileWeaponItem.ARROW_OR_FIREWORK.or(it -> it.is(NTags.Items.CROSSBOW_BOLTS));

    @Inject(cancellable = true, at = @At("HEAD"), method = "getSupportedHeldProjectiles()Ljava/util/function/Predicate;")
    public void injectBolt(CallbackInfoReturnable<Predicate<ItemStack>> cir) {
        cir.setReturnValue(HELD_PREDICATE);
    }

}
