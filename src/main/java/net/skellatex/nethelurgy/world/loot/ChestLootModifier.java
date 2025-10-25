package net.skellatex.nethelurgy.world.loot;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.Supplier;

public final class ChestLootModifier extends LootModifier {

    public static final Supplier<Codec<ChestLootModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(instance -> codecStart(instance).and(
            Addition.CODEC.listOf().fieldOf("additions").forGetter(modifier -> modifier.additions)
    ).apply(instance, ChestLootModifier::new)));

    private final List<Addition> additions;

    private ChestLootModifier(LootItemCondition[] conditionsIn, List<Addition> additions) {
        super(conditionsIn);
        this.additions = additions;
    }

    @Override
    @Nonnull
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        if (generatedLoot == null) {
            generatedLoot = new ObjectArrayList<>();
        }
        for (Addition addition : additions) {
            if (context.getRandom().nextFloat() <= addition.chance) {
                generatedLoot.add(addition.getEnchantedBook());
            }
        }
            return generatedLoot;
        }


    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }

    private static final class Addition {

        public static final Codec<Addition> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                ForgeRegistries.ENCHANTMENTS.getCodec().fieldOf("enchantment").forGetter(addition -> addition.enchantment),
                Codec.FLOAT.fieldOf("chance").forGetter(addition -> addition.chance),
                Codec.INT.fieldOf("level").forGetter(addition -> addition.level)
        ).apply(instance, Addition::new));

        private final Enchantment enchantment;

        private final Float chance;

        private final Integer level;

        private Addition(Enchantment item, Float chance, Integer quantity) {
            this.enchantment = item;
            this.chance = chance;
            this.level = quantity;
        }

        private ItemStack getEnchantedBook() {
            ItemStack book = new ItemStack(Items.ENCHANTED_BOOK);
            EnchantedBookItem.addEnchantment(book, new EnchantmentInstance(enchantment, Math.min(enchantment.getMaxLevel(), level)));
            return book;
        }
    }
}