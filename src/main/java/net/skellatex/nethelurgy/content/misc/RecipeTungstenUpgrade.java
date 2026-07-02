package net.skellatex.nethelurgy.content.misc;

import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.skellatex.nethelurgy.registry.NItems;
import net.skellatex.nethelurgy.registry.NRecipeTypes;
import net.skellatex.nethelurgy.registry.NTags;

public class RecipeTungstenUpgrade extends CustomRecipe {

    public RecipeTungstenUpgrade(ResourceLocation idIn, CraftingBookCategory category) {
        super(idIn, category);
    }


    private ItemStack createEquipment(Container container){
        ItemStack equipment = ItemStack.EMPTY;
        int fireproof = 0;
        for (int j = 0; j < container.getContainerSize(); ++j) {
            ItemStack itemstack1 = container.getItem(j);
            if (itemstack1.is(NItems.TUNGSTEN_INGOT.get())) {
                fireproof++;
            }
        }
        if(fireproof == 1){
            for (int j = 0; j < container.getContainerSize(); ++j) {
                ItemStack itemstack1 = container.getItem(j);
                boolean notFireproof = !itemstack1.hasTag() || itemstack1.getTag() != null && !itemstack1.getTag().getBoolean("Fireproof");
                if (!itemstack1.isEmpty() && notFireproof && itemstack1.isDamageableItem() && !itemstack1.is(NTags.Items.TUNGSTEN_UPGRADE_INCOMPATIBLE)) {
                    equipment = itemstack1;
                }
            }
            if(!equipment.isEmpty()){
                ItemStack stack = equipment.copy();
                CompoundTag tag = stack.getOrCreateTag();
                tag.putBoolean("Fireproof", true);
                stack.setTag(tag);
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean matches(CraftingContainer inv, Level worldIn) {
        return !createEquipment(inv).isEmpty();
    }

    @Override
    public ItemStack assemble(CraftingContainer container, RegistryAccess registryAccess) {
        return createEquipment(container);
    }

    @Override
    public boolean canCraftInDimensions(int x, int y) {
        return x * y >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return NRecipeTypes.TUNGSTEN_UPGRADE.get();
    }
}
