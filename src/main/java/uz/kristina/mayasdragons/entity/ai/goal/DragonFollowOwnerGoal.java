package uz.kristina.mayasdragons.entity.ai.goal;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import uz.kristina.mayasdragons.entity.DragonEntity;

public class DragonFollowOwnerGoal extends Goal {

    private final DragonEntity dragon;
    private LivingEntity owner;

    public DragonFollowOwnerGoal(DragonEntity dragon) {
        this.dragon = dragon;
    }

    @Override
    public boolean canUse() {

        LivingEntity foundOwner = dragon.getOwner();

        if (foundOwner == null) {
            return false;
        }

        if (dragon.isFlying()) {
            return false;
        }

        double distanceToOwner = dragon.distanceToSqr(foundOwner);

        if (distanceToOwner < 100.0D) {
            return false;
        }

        this.owner = foundOwner;
        return true;

    }
    @Override
    public void start() {
        dragon.startFlying();
    }
}