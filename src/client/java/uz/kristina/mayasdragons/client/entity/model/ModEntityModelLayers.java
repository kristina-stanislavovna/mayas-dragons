package uz.kristina.mayasdragons.client.entity.model;

import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import uz.kristina.mayasdragons.MayaSDragons;

public class ModEntityModelLayers {

    public static final ModelLayerLocation DRAGON =
            new ModelLayerLocation(
                    MayaSDragons.id("dragon"),
                    "main"
            );

    public static void registerModelLayers() {
        ModelLayerRegistry.registerModelLayer(
                DRAGON,
                DragonEntityModel::createBodyLayer
        );
    }
}