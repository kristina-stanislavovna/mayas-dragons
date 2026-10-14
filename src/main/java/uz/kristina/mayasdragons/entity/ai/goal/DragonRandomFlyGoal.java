package uz.kristina.mayasdragons.entity.ai.goal;

import net.minecraft.world.entity.ai.goal.Goal;
import uz.kristina.mayasdragons.entity.DragonEntity;


public class DragonRandomFlyGoal extends Goal {

    private final DragonEntity dragon;

    public DragonRandomFlyGoal(DragonEntity dragon) {
        this.dragon = dragon;
    }

    @Override
    public boolean canUse() {
        return dragon.onGround()
                && !dragon.isFlying()
                && dragon.getRandom().nextInt(200) == 0;
    }
    @Override
    public void start() {
        System.out.println("DragonRandomFlyGoal START");
        dragon.startFlying();
    }
}