package uz.kristina.mayasdragons.client.entity.state;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import uz.kristina.mayasdragons.entity.DragonGrowthStage;

public class DragonEntityRenderState extends LivingEntityRenderState {

    public DragonGrowthStage growthStage;
    public boolean flying;
}