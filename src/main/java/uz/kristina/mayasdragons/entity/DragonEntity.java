package uz.kristina.mayasdragons.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;

public class DragonEntity extends TamableAnimal {
    private int growthTicks = 0;
    private static final EntityDataAccessor<Integer> DATA_GROWTH_TICKS =
            SynchedEntityData.defineId(
                    DragonEntity.class,
                    EntityDataSerializers.INT
            );

    private static final int JUVENILE_TIME = 20 * 30;
    private static final int ADULT_TIME = 20 * 60;

    public DragonEntity(EntityType<? extends DragonEntity> type, Level level) {
        super(type, level);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);

        builder.define(DATA_GROWTH_TICKS, 0);
    }

    public static AttributeSupplier.Builder createDragonAttributes() {
        return TamableAnimal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25);
    }

    @Override
    public boolean isFood(ItemStack itemStack) {
        return false;
    }

    @Nullable
    @Override
    public DragonEntity getBreedOffspring(ServerLevel level, AgeableMob partner) {
        return null;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(
                1,
                new RandomStrollGoal(this, 0.8)
        );

        this.goalSelector.addGoal(
                2,
                new LookAtPlayerGoal(this, Player.class, 6.0F)
        );

        this.goalSelector.addGoal(
                3,
                new RandomLookAroundGoal(this)
        );
    }

    public DragonGrowthStage getGrowthStage() {

            int age = getGrowthTicks();

            if (age < JUVENILE_TIME) {
                return DragonGrowthStage.HATCHLING;
            }

            if (age < ADULT_TIME) {
                return DragonGrowthStage.JUVENILE;
            }

            return DragonGrowthStage.ADULT;
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.level().isClientSide()) {
            growthTicks++;

            this.entityData.set(
                    DATA_GROWTH_TICKS,
                    growthTicks
            );
        }
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);

        output.putInt("GrowthTicks", growthTicks);
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);

        growthTicks = input.getIntOr("GrowthTicks", 0);
        this.entityData.set(DATA_GROWTH_TICKS, growthTicks);
    }

    public int getGrowthTicks() {
        return this.entityData.get(DATA_GROWTH_TICKS);
    }
}