package net.ttzplayz.primordial.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.ttzplayz.primordial.Primordial;
import net.ttzplayz.primordial.entity.custom.BlubEntity;

public class BlubRenderer extends MobEntityRenderer<BlubEntity, BlubModel<BlubEntity>> {
    public BlubRenderer(EntityRendererFactory.Context context) {
        super(context, new BlubModel<>(context.getPart(BlubModel.BLUB)), 0.75f);
    }

    @Override
    public Identifier getTexture(BlubEntity entity) {
        return Identifier.of(Primordial.MOD_ID, "textures/entity/blub/blub.png");
    }

    @Override
    public void render(BlubEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if (livingEntity.isBaby()) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrixStack.scale(1f, 1f, 1f);
        }

        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
