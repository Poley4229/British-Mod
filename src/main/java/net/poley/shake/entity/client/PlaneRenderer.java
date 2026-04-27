package net.poley.shake.entity.client;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.poley.shake.ShakeMod;
import net.poley.shake.entity.custom.PlaneEntity;

public class PlaneRenderer extends MobEntityRenderer<PlaneEntity, PlaneModel<PlaneEntity>> {
    private static final Identifier TEXTURE = new Identifier(ShakeMod.MOD_ID, "textures/entity/plane.png");

    public PlaneRenderer(EntityRendererFactory.Context context) {
        super(context, new PlaneModel<>(context.getPart(ModModelLayers.PLANE)), 3f);
    }

    @Override
    public Identifier getTexture(PlaneEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(PlaneEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        //matrixStack.multiply(new Quaternionf(Vec3d.POSITIVE_Y.getDegreesQuaternion(45.0F))); // Rotate 45 degrees around the Y axis

        if(mobEntity.isBaby()) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrixStack.scale(3f, 3f, 3f);
        }

        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
        matrixStack.pop();
    }
}