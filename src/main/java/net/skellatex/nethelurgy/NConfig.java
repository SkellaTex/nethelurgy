package net.skellatex.nethelurgy;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;

public class NConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.BooleanValue ENABLE_SEARING;
    public static final ForgeConfigSpec.BooleanValue NETHERITE_ARMOR_FIRE_RESISTANCE;
    public static final ForgeConfigSpec.BooleanValue RED_NETHER_BRICK_BLOCKSET;
    public static final ForgeConfigSpec.BooleanValue NETHER_BRICK_FENCE_GATE;
    public static final ForgeConfigSpec.BooleanValue TUNGSTEN_BLOCKSET;

    static {
        BUILDER.push("Nethelurgy Config");

        ENABLE_SEARING = BUILDER.comment("Whether Searing can be obtained in survival").define("Enable Searing", true);

        NETHERITE_ARMOR_FIRE_RESISTANCE = BUILDER.comment("Whether Netherite Armor has fire resistance").define("Netherite Armor tweak", true);

        RED_NETHER_BRICK_BLOCKSET = BUILDER.comment("Whether Red Nether Brick blocks added by the mod appear in creative tabs").define("Red Nether Brick Blockset", true);

        NETHER_BRICK_FENCE_GATE = BUILDER.comment("Whether Nether Brick Fence Gate appears in creative tabs").define("Nether Brick Fence Gate", true);

        TUNGSTEN_BLOCKSET = BUILDER.comment("Whether Cut Tungsten blocks appear in creative tabs").define("Tungsten Blockset", true);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}