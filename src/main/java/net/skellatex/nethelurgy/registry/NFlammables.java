package net.skellatex.nethelurgy.registry;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;

public class NFlammables {
    public static void register() {
        FireBlock fireBlock = (FireBlock) Blocks.FIRE;

        fireBlock.setFlammable(NBlocks.FIREBOMB.get(), 15, 100);
        fireBlock.setFlammable(NBlocks.IGNITE_BLOCK.get(), 4, 4);
    }
}
