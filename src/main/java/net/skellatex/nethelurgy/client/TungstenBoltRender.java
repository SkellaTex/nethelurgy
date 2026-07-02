package net.skellatex.nethelurgy.client;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.skellatex.nethelurgy.Nethelurgy;

public class TungstenBoltRender extends ArrowRenderer {

    public static final ResourceLocation TUNGSTEN_BOLT_TEXTURE = new ResourceLocation(Nethelurgy.MOD_ID,"textures/item/tungsten_bolt_projectile.png");

    public TungstenBoltRender(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(Entity entity) {
        return TUNGSTEN_BOLT_TEXTURE;
    }

}
