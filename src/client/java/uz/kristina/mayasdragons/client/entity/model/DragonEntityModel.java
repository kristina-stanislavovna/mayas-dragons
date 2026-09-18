package uz.kristina.mayasdragons.client.entity.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import uz.kristina.mayasdragons.client.entity.state.DragonEntityRenderState;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class DragonEntityModel extends EntityModel<DragonEntityRenderState> {

    public DragonEntityModel(ModelPart root) {
        super(root);
    }
    public static LayerDefinition createBodyLayer() {

        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild(
                "body",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(
                                -4.0F,
                                -4.0F,
                                -6.0F,
                                8.0F,
                                8.0F,
                                12.0F
                        ),
                PartPose.offset(0.0F, 16.0F, 0.0F)
        );

        return LayerDefinition.create(mesh, 64, 64);
    }
}