package net.ttzplayz.primordial;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.ttzplayz.primordial.entity.ModEntities;
import net.ttzplayz.primordial.entity.client.BlubModel;
import net.ttzplayz.primordial.entity.client.BlubRenderer;
import net.ttzplayz.primordial.entity.client.BlubbleModel;
import net.ttzplayz.primordial.entity.client.BlubbleRenderer;

public class PrimordialClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(BlubModel.BLUB, BlubModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.BLUB, BlubRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(BlubbleModel.BLUBBLE, BlubbleModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.BLUBBLE, BlubbleRenderer::new);
    }
}
