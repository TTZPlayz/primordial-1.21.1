package net.ttzplayz.primordial.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.ttzplayz.primordial.Primordial;
import net.ttzplayz.primordial.entity.custom.BlubEntity;

// Made with Blockbench 4.12.2
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class BlubModel<T extends BlubEntity> extends SinglePartEntityModel<T> {
	public static final EntityModelLayer BLUB = new EntityModelLayer(Identifier.of(Primordial.MOD_ID, "blub"), "main");
	private final ModelPart blub;
	private final ModelPart head;
	public BlubModel(ModelPart root) {
		this.blub = root.getChild("blub");
		this.head = this.blub.getChild("head");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData blub = modelPartData.addChild("blub", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData head = blub.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -20.0F, 0.0F));

		ModelPartData eye1 = head.addChild("eye1", ModelPartBuilder.create().uv(0, 4).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, 1.0F, -1.0F));

		ModelPartData eye2 = head.addChild("eye2", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, -1.0F, -1.0F));

		ModelPartData rods = blub.addChild("rods", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -15.0F, 0.0F));

		ModelPartData core = rods.addChild("core", ModelPartBuilder.create().uv(0, 16).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData rod1 = rods.addChild("rod1", ModelPartBuilder.create().uv(16, 16).cuboid(-1.0F, 0.0F, -8.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData rod2 = rods.addChild("rod2", ModelPartBuilder.create().uv(16, 16).cuboid(-1.0F, 0.0F, 6.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData rod3 = rods.addChild("rod3", ModelPartBuilder.create().uv(16, 16).cuboid(6.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData rod4 = rods.addChild("rod4", ModelPartBuilder.create().uv(16, 16).cuboid(-8.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}
	@Override
	public void setAngles(BlubEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.setHeadAngles(netHeadYaw, headPitch);

		this.animateMovement(BlubAnimations.BLUB_AMBIENT, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.updateAnimation(entity.idleAnimationState, BlubAnimations.BLUB_AMBIENT, ageInTicks, 1f);
	}

	private void setHeadAngles(float headYaw, float headPitch) {
		headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
		headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

		this.head.yaw = headYaw * 0.017453292F;
		this.head.pitch = headPitch * 0.017453292F;
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
		blub.render(matrices, vertexConsumer, light, overlay, color);
	}

	public ModelPart getPart() {
		return blub;
	}
}