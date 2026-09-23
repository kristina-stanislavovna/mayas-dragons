package uz.kristina.mayasdragons.client.entity.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import uz.kristina.mayasdragons.client.entity.state.DragonEntityRenderState;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.CubeDeformation;

public class DragonEntityModel extends EntityModel<DragonEntityRenderState> {

    private final ModelPart leftWing;
    private final ModelPart rightWing;
    private final ModelPart tail;
    private final ModelPart tailMiddle;
    private final ModelPart tailTip;
    private final ModelPart neck;
    private final ModelPart head;

    public DragonEntityModel(ModelPart root) {
        super(root);

        ModelPart body = root.getChild("body");
        this.neck = body.getChild("neck");
        this.head = this.neck.getChild("head");
        this.leftWing = body.getChild("left_wing");
        this.rightWing = body.getChild("right_wing");
        this.tail = body.getChild("tail");
        this.tailMiddle = this.tail.getChild("tail_middle");
        this.tailTip = this.tailMiddle.getChild("tail_tip");


    }


    @Override
    public void setupAnim(DragonEntityRenderState state) {
        super.setupAnim(state);
        float flap = (float) Math.sin(state.ageInTicks * 0.12F) * 0.08F;
        this.leftWing.zRot = flap;
        this.rightWing.zRot = -flap;
        float tailSwing = (float) Math.sin(state.ageInTicks * 0.08F);
        this.tail.yRot = tailSwing * 0.04F;
        this.tailMiddle.yRot = tailSwing * 0.07F;
        this.tailTip.yRot = tailSwing * 0.10F;
        float breathe = (float) Math.sin(state.ageInTicks * 0.05F);
        this.neck.xRot = breathe * 0.015F;
        this.head.xRot = breathe * 0.025F;
        this.head.yRot = (float) Math.sin(state.ageInTicks * 0.035F) * 0.02F;

    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -16.0F, -8.0F, 10.0F, 8.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(0, 24).addBox(-6.0F, -16.0F, -9.0F, 12.0F, 9.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition neck = body.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(0, 45).addBox(-3.0F, -4.0F, -7.0F, 6.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -14.0F, -7.0F));

        PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(36, 24).addBox(-4.0F, -4.0F, -8.0F, 8.0F, 7.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(52, 7).addBox(-3.0F, -2.0F, -13.0F, 6.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -6.0F));

        PartDefinition right_horn_r1 = head.addOrReplaceChild("right_horn_r1", CubeListBuilder.create().texOffs(16, 67).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -3.0F, -2.0F, 0.4363F, -0.2618F, 0.1745F));

        PartDefinition left_horn_r1 = head.addOrReplaceChild("left_horn_r1", CubeListBuilder.create().texOffs(62, 60).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -3.0F, -2.0F, 0.4363F, 0.2618F, -0.1745F));

        PartDefinition lower_jaw = head.addOrReplaceChild("lower_jaw", CubeListBuilder.create().texOffs(50, 52).addBox(-3.0F, 0.0F, -6.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, -7.0F));

        PartDefinition front_left_leg = body.addOrReplaceChild("front_left_leg", CubeListBuilder.create().texOffs(32, 67).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.5F, -8.0F, -5.5F));

        PartDefinition front_right_leg = body.addOrReplaceChild("front_right_leg", CubeListBuilder.create().texOffs(62, 68).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(4.5F, -8.0F, -5.5F));

        PartDefinition back_left_leg = body.addOrReplaceChild("back_left_leg", CubeListBuilder.create().texOffs(0, 58).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -8.0F, 7.0F));

        PartDefinition back_right_leg = body.addOrReplaceChild("back_right_leg", CubeListBuilder.create().texOffs(46, 60).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -8.0F, 7.0F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(26, 45).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -10.0F, 7.0F));

        PartDefinition tail_middle = tail.addOrReplaceChild("tail_middle", CubeListBuilder.create().texOffs(26, 57).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 8.0F));

        PartDefinition tail_tip = tail_middle.addOrReplaceChild("tail_tip", CubeListBuilder.create().texOffs(68, 23).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 7.0F));

        PartDefinition left_wing = body.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(68, 31).addBox(-8.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(50, 45).addBox(-8.0F, 1.0F, 0.0F, 8.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -14.0F, -4.0F));

        PartDefinition left_wing_tip = left_wing.addOrReplaceChild("left_wing_tip", CubeListBuilder.create().texOffs(0, 39).addBox(-12.0F, 2.0F, 0.0F, 12.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, 0.0F, 0.0F, 0.0F, 0.3491F, 0.0F));

        PartDefinition left_wing_tip_main_r1 = left_wing_tip.addOrReplaceChild("left_wing_tip_main_r1", CubeListBuilder.create().texOffs(52, 17).addBox(-12.0F, 1.0F, -1.0F, 12.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.3491F, 0.0F));

//		PartDefinition right_wing = body.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(68, 35).addBox(-16.0F, -3.0F, 0.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
//		.texOffs(52, 0).addBox(-16.0F, -1.0F, 1.0F, 8.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(21.0F, -12.0F, -5.0F));
        PartDefinition right_wing = body.addOrReplaceChild(
                "right_wing",
                CubeListBuilder.create()
                        .texOffs(68, 35)
                        .addBox(
                                0.0F, -1.0F, -1.0F,
                                8.0F, 2.0F, 2.0F,
                                new CubeDeformation(0.0F)
                        )
                        .texOffs(52, 0)
                        .addBox(
                                0.0F, 1.0F, 0.0F,
                                8.0F, 1.0F, 6.0F,
                                new CubeDeformation(0.0F)
                        ),
                PartPose.offset(5.0F, -14.0F, -4.0F)
        );

        PartDefinition right_wing_tip = right_wing.addOrReplaceChild("right_wing_tip", CubeListBuilder.create().texOffs(34, 39).addBox(0.0F, 2.0F, 0.0F, 12.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F,
                0.0F,
                0.0F,
                0.0F,
                -0.3491F,
                0.0F));

        PartDefinition right_wing_tip_main_r1 = right_wing_tip.addOrReplaceChild("right_wing_tip_main_r1", CubeListBuilder.create().texOffs(52, 20).addBox(0.0F, 1.0F, -1.0F, 12.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3491F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }


}