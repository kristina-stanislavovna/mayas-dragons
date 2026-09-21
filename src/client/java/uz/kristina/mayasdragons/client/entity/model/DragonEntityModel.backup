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

        PartDefinition body = root.addOrReplaceChild(
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

        PartDefinition neck = body.addOrReplaceChild(
                "neck",
                CubeListBuilder.create()
                        .texOffs(0, 22)
                        .addBox(
                                -2.5F,
                                -2.5F,
                                -5.0F,
                                5.0F,
                                5.0F,
                                5.0F
                        ),
                PartPose.offset(0.0F, -1.0F, -6.0F)
        );

        neck.addOrReplaceChild(
                "head",
                CubeListBuilder.create()
                        .texOffs(22, 22)
                        .addBox(
                                -3.0F,
                                -3.0F,
                                -5.0F,
                                6.0F,
                                6.0F,
                                6.0F
                        ),
                PartPose.offset(0.0F, -1.0F, -4.0F)
        );
        body.addOrReplaceChild(
                "front_left_leg",
                CubeListBuilder.create()
                        .texOffs(40, 0)
                        .addBox(
                                -1.5F,
                                0.0F,
                                -1.5F,
                                3.0F,
                                7.0F,
                                3.0F
                        ),
                PartPose.offset(3.0F, 3.0F, -4.0F)
        );

        body.addOrReplaceChild(
                "front_right_leg",
                CubeListBuilder.create()
                        .texOffs(40, 0)
                        .addBox(
                                -1.5F,
                                0.0F,
                                -1.5F,
                                3.0F,
                                7.0F,
                                3.0F
                        ),
                PartPose.offset(-3.0F, 3.0F, -4.0F)
        );

        body.addOrReplaceChild(
                "back_left_leg",
                CubeListBuilder.create()
                        .texOffs(40, 12)
                        .addBox(
                                -1.7F,
                                0.0F,
                                -1.7F,
                                3.4F,
                                7.0F,
                                3.4F
                        ),
                PartPose.offset(3.0F, 3.0F, 4.0F)
        );

        body.addOrReplaceChild(
                "back_right_leg",
                CubeListBuilder.create()
                        .texOffs(40, 12)
                        .addBox(
                                -1.7F,
                                0.0F,
                                -1.7F,
                                3.4F,
                                7.0F,
                                3.4F
                        ),
                PartPose.offset(-3.0F, 3.0F, 4.0F)
        );
        PartDefinition tailBase = body.addOrReplaceChild(
                "tail_base",
                CubeListBuilder.create()
                        .texOffs(0, 34)
                        .addBox(
                                -2.0F,
                                -2.0F,
                                0.0F,
                                4.0F,
                                4.0F,
                                7.0F
                        ),
                PartPose.offset(0.0F, 0.0F, 6.0F)
        );

        PartDefinition tailMiddle = tailBase.addOrReplaceChild(
                "tail_middle",
                CubeListBuilder.create()
                        .texOffs(22, 34)
                        .addBox(
                                -1.5F,
                                -1.5F,
                                0.0F,
                                3.0F,
                                3.0F,
                                7.0F
                        ),
                PartPose.offset(0.0F, 0.0F, 7.0F)
        );

        tailMiddle.addOrReplaceChild(
                "tail_tip",
                CubeListBuilder.create()
                        .texOffs(40, 34)
                        .addBox(
                                -1.0F,
                                -1.0F,
                                0.0F,
                                2.0F,
                                2.0F,
                                6.0F
                        ),
                PartPose.offset(0.0F, 0.0F, 7.0F)
        );
        PartDefinition leftWing = body.addOrReplaceChild(
                "left_wing",
                CubeListBuilder.create()
                        .texOffs(0, 46)
                        .addBox(
                                0.0F,
                                -1.0F,
                                -3.0F,
                                10.0F,
                                2.0F,
                                7.0F
                        ),
                PartPose.offsetAndRotation(
                        4.0F, -2.0F, -1.0F,
                        0.0F, -0.30F, -0.20F
                )
        );

        leftWing.addOrReplaceChild(
                "left_wing_tip",
                CubeListBuilder.create()
                        .texOffs(0, 55)
                        .addBox(
                                0.0F,
                                -0.5F,
                                -2.5F,
                                9.0F,
                                1.0F,
                                6.0F
                        ),
                PartPose.offset(10.0F, 0.0F, 0.0F)
        );
        leftWing.addOrReplaceChild(
                "left_membrane",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(
                                1.0F,
                                0.0F,
                                -1.0F,
                                9.0F,
                                1.0F,
                                10.0F
                        ),
                PartPose.offset(0.0F, 0.5F, 0.0F)
        );
        PartDefinition rightWing = body.addOrReplaceChild(
                "right_wing",
                CubeListBuilder.create()
                        .texOffs(0, 46)
                        .addBox(
                                -10.0F,
                                -1.0F,
                                -3.0F,
                                10.0F,
                                2.0F,
                                7.0F
                        ),
                PartPose.offsetAndRotation(
                        -4.0F, -2.0F, -1.0F,
                        0.0F, 0.30F, 0.20F
                )
        );

        rightWing.addOrReplaceChild(
                "right_wing_tip",
                CubeListBuilder.create()
                        .texOffs(0, 55)
                        .addBox(
                                -9.0F,
                                -0.5F,
                                -2.5F,
                                9.0F,
                                1.0F,
                                6.0F
                        ),
                PartPose.offset(-10.0F, 0.0F, 0.0F)
        );
        rightWing.addOrReplaceChild(
                "right_membrane",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(
                                -10.0F,
                                0.0F,
                                -1.0F,
                                9.0F,
                                1.0F,
                                10.0F
                        ),
                PartPose.offset(0.0F, 0.5F, 0.0F)
        );

        return LayerDefinition.create(mesh, 64, 64);
    }

}