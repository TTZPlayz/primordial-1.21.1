package net.ttzplayz.primordial.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.ttzplayz.primordial.Primordial;
import net.ttzplayz.primordial.entity.custom.BlubbleProjectileEntity;

public class BlubbleModel extends EntityModel<BlubbleProjectileEntity> {
	public static final EntityModelLayer BLUBBLE = new EntityModelLayer(Identifier.of(Primordial.MOD_ID, "blubble"), "main");
	private final ModelPart blubble;
	public BlubbleModel(ModelPart root) {
		this.blubble = root.getChild("blubble");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData blubble = modelPartData.addChild("blubble", ModelPartBuilder.create().uv(0, 8).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 14.0F, 0.0F));
		return TexturedModelData.of(modelData, 16, 16);
	}
	@Override
	public void setAngles(BlubbleProjectileEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
		blubble.render(matrices, vertexConsumer, light, overlay, color);
	}
}