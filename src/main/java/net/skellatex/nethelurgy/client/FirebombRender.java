package net.skellatex.nethelurgy.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.TntMinecartRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.skellatex.nethelurgy.content.entity.PrimedFirebomb;
import net.skellatex.nethelurgy.registry.NBlocks;

public class FirebombRender extends EntityRenderer<PrimedFirebomb> {

    private final BlockRenderDispatcher blockRenderer;

    public FirebombRender(EntityRendererProvider.Context context) {
        super(context);
        this.blockRenderer = context.getBlockRenderDispatcher();
    }

    public void render(PrimedFirebomb entity, float yaw, float paritalTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        poseStack.translate(0.0D, 0.5D, 0.0D);
        int fuse = entity.getFuse();
        if ((float)fuse - paritalTicks + 1.0F < 10.0F) {
            float f = 1.0F - ((float)fuse - paritalTicks + 1.0F) / 10.0F;
            f = Mth.clamp(f, 0.0F, 1.0F);
            f *= f;
            f *= f;
            float f1 = 1.0F + f * 0.3F;
            poseStack.scale(f1, f1, f1);
        }
        poseStack.mulPose(Axis.YP.rotationDegrees(-90.0F));
        poseStack.translate(-0.5D, -0.5D, 0.5D);
        poseStack.mulPose(Axis.YP.rotationDegrees(90.0F));
        TntMinecartRenderer.renderWhiteSolidBlock(this.blockRenderer, NBlocks.FIREBOMB.get().defaultBlockState(), poseStack, buffer, packedLight, fuse / 5 % 2 == 0);
        poseStack.popPose();
        super.render(entity, yaw, paritalTicks, poseStack, buffer, packedLight);
    }

    public ResourceLocation getTextureLocation(PrimedFirebomb entity) {
        return TextureAtlas.LOCATION_BLOCKS;
    }
}
