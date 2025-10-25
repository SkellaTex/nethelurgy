package net.skellatex.nethelurgy.world.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.NotNull;

public class SearingLootModifier extends LootModifier {
    public static final Codec<SearingLootModifier> CODEC = RecordCodecBuilder.create(inst -> codecStart(inst).apply(inst, SearingLootModifier::new));

    public SearingLootModifier(LootItemCondition[] conditions) {
        super(conditions);
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        return generatedLoot.stream().map(stack -> {
            var smelted = context.getLevel().getRecipeManager()
                    .getRecipeFor(RecipeType.SMELTING, new SimpleContainer(stack), context.getLevel())
                    .map(smeltingRecipe -> smeltingRecipe.getResultItem(context.getLevel().registryAccess()))
                    .filter(itemStack -> !itemStack.isEmpty())
                    .map(itemStack -> ItemHandlerHelper.copyStackWithSize(itemStack, stack.getCount() * itemStack.getCount()))
                    .orElse(stack);
            return smelted;
        }).collect(ObjectArrayList.toList());
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return NLootModifiers.SEARING_LOOT_MODIFIER.get();
    }
}
