package uz.kristina.mayasdragons.entity.ai.goal;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import uz.kristina.mayasdragons.entity.DragonEntity;
import java.util.EnumSet;

public class DragonFollowOwnerGoal extends Goal {

    private final DragonEntity dragon;
    private LivingEntity owner;

    public DragonFollowOwnerGoal(DragonEntity dragon) {
        this.dragon = dragon;

        this.setFlags(
                EnumSet.of(Goal.Flag.MOVE)
        );
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
        System.out.println("FOLLOW OWNER: START");
        dragon.startFlying();
    }

    @Override
    public void tick() {

        if (owner == null) {
            return;
        }
        dragon.keepFlying();

        if (dragon.tickCount % 20 == 0) {
            System.out.println(
                    "FOLLOW OWNER: distance = " +
                            Math.sqrt(dragon.distanceToSqr(owner))
            );
        }

        dragon.setFlightTarget(
                owner.getX(),
                owner.getY() + 3.0D,
                owner.getZ()
        );
    }
    @Override
    public boolean canContinueToUse() {

        if (owner == null) {
            return false;
        }

        return dragon.distanceToSqr(owner) > 25.0D;
    }
    @Override
    public void stop() {
        System.out.println("FOLLOW OWNER: STOP");
    }
}