package uz.kristina.mayasdragons.entity.ai.goal;

import net.minecraft.world.entity.ai.goal.Goal;
import uz.kristina.mayasdragons.entity.DragonEntity;
import java.util.EnumSet;


public class DragonRandomFlyGoal extends Goal {

    private final DragonEntity dragon;

    public DragonRandomFlyGoal(DragonEntity dragon) {
        this.dragon = dragon;

        this.setFlags(
                EnumSet.of(Goal.Flag.MOVE)
        );
    }

    @Override
    public boolean canUse() {
//        return dragon.onGround()
//                && !dragon.isFlying()
//                && dragon.getRandom().nextInt(200) == 0;
        return false;
    }
    @Override
    public void start() {
        System.out.println("DragonRandomFlyGoal START");
        dragon.startFlying();
    }
}