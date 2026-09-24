package uz.kristina.mayasdragons.client.entity.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import uz.kristina.mayasdragons.MayaSDragons;
import uz.kristina.mayasdragons.client.entity.model.DragonEntityModel;
import uz.kristina.mayasdragons.client.entity.model.ModEntityModelLayers;
import uz.kristina.mayasdragons.client.entity.state.DragonEntityRenderState;
import uz.kristina.mayasdragons.entity.DragonEntity;
import uz.kristina.mayasdragons.entity.DragonGrowthStage;
import com.mojang.blaze3d.vertex.PoseStack;

public class DragonEntityRenderer extends MobRenderer<
        DragonEntity,
        DragonEntityRenderState,
        DragonEntityModel> {


    private static final Identifier TEXTURE =
            MayaSDragons.id("textures/entity/dragon.png");



    public DragonEntityRenderer(EntityRendererProvider.Context context) {
        super(
                context,
                new DragonEntityModel(
                        context.bakeLayer(ModEntityModelLayers.DRAGON)
                ),
                0.6F
        );
    }
    @Override
    public void extractRenderState(
            DragonEntity entity,
            DragonEntityRenderState state,
            float tickProgress
    ) {
        super.extractRenderState(entity, state, tickProgress);

        state.growthStage = entity.getGrowthStage();
        state.flying = entity.isFlying();
    }


    @Override
    public DragonEntityRenderState createRenderState() {
        return new DragonEntityRenderState();
    }

    @Override
    public Identifier getTextureLocation(DragonEntityRenderState state) {
        return TEXTURE;
    }
    @Override
    protected void scale(
            DragonEntityRenderState state,
            PoseStack poseStack
    ) {

        float scale = switch (state.growthStage) {
            case HATCHLING -> 0.4F;
            case JUVENILE -> 0.7F;
            case ADULT -> 1.0F;
        };

        poseStack.scale(scale, scale, scale);
    }
}