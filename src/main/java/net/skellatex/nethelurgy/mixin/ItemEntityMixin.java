package net.skellatex.nethelurgy.mixin;

import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemEntity.class)
public class ItemEntityMixin {

    @Inject(at = @At("HEAD"), method = "hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z", cancellable = true)
    private void onHurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (source.is(DamageTypeTags.IS_FIRE)) {
            ItemEntity itemEntity = (ItemEntity) (Object) this;
            ItemStack stack = itemEntity.getItem();
            if (stack.getOrCreateTag().contains("Fireproof") && stack.getOrCreateTag().getBoolean("Fireproof")) {
                cir.setReturnValue(false);
            }
        }
    }
}
