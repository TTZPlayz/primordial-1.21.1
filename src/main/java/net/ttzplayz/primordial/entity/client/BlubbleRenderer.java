package net.ttzplayz.primordial.entity.client;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.ttzplayz.primordial.Primordial;
import net.ttzplayz.primordial.entity.custom.BlubbleProjectileEntity;

public class BlubbleRenderer extends EntityRenderer<BlubbleProjectileEntity> {
    protected BlubbleModel model;
    public Identifier blubble = Identifier.of(Primordial.MOD_ID, "textures/entity/blubble/blubble_entity.png");

    public BlubbleRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.model = new BlubbleModel(ctx.getPart(BlubbleModel.BLUBBLE));
    }

    public void render(BlubbleProjectileEntity entity, float yaw, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();

        if(!entity.isGrounded()) {
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(tickDelta, entity.prevYaw, entity.getYaw())));
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(entity.getRenderingRotation() * 5f));
            matrices.translate(0,-1.0f, 0);
        } else {
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(entity.groundedOffset.getY()));
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(entity.groundedOffset.getX()));
            matrices.translate(0, -1.0f, 0);
        }

        VertexConsumer vertexConsumer = ItemRenderer.getDirectItemGlintConsumer(vertexConsumers, this.model.getLayer(blubble), false, false);
        this.model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);

        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(BlubbleProjectileEntity entity) {
        return Identifier.of(Primordial.MOD_ID, "textures/entity/blubble/blubble_entity.png");
    }
}
