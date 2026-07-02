package net.skellatex.nethelurgy.content.misc;

import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.gameevent.GameEvent;
import net.skellatex.nethelurgy.content.entity.PrimedFirebomb;

public class CustomTNTDispenseBehavior extends DefaultDispenseItemBehavior {

    @Override
    public ItemStack execute(BlockSource source, ItemStack stack) {
        ServerLevel level = source.getLevel();
        Direction direction = source.getBlockState().getValue(DispenserBlock.FACING);

        double x = source.x() + (double) direction.getStepX() * 1.125;
        double y = source.y() + (double) direction.getStepY() * 1.125;
        double z = source.z() + (double) direction.getStepZ() * 1.125;

        PrimedFirebomb primed = new PrimedFirebomb(level, x, y, z, null);

        primed.setFuse(80);

        level.addFreshEntity(primed);

        level.gameEvent(null, GameEvent.ENTITY_PLACE, source.getPos().relative(direction));
        level.playSound(null, primed.getX(), primed.getY(), primed.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
        stack.shrink(1);
        return stack;
    }
}


