package net.skellatex.nethelurgy.compat;

import java.util.function.Function;
import net.minecraft.world.item.Item;
import net.skellatex.nethelurgy.registry.NToolTiers;
import vectorwing.farmersdelight.common.item.KnifeItem;

public class FarmersDelightCompat {

    public static final Function<Item.Properties, ? extends Item> KNIFE_FACTORY_TUNGSTEN = (it) ->
            new KnifeItem(NToolTiers.TUNGSTEN, 3.5F, -2.2F, it);

}