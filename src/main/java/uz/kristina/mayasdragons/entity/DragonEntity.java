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
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

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

        this.moveControl = new FlyingMoveControl(
                this,
                20,
                true
        );
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_GROWTH_TICKS, 0);
        builder.define(DATA_FLYING, false);
    }

    public static AttributeSupplier.Builder createDragonAttributes() {
        return TamableAnimal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.FLYING_SPEED, 0.4);

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

            // Страховка после перезапуска мира
            if (!this.isFlying()) {
                this.setNoGravity(false);
            }

            growthTicks++;

            this.entityData.set(
                    DATA_GROWTH_TICKS,
                    growthTicks
            );

            // -------------------------
            // TEST: запуск полёта
            // -------------------------

            if (!this.isFlying() && this.onGround()) {

                flightCooldown--;

                if (flightCooldown <= 0) {
                    this.startFlying();
                    flightCooldown = 200;
                }
            }

            // -------------------------
            // Полёт
            // -------------------------

            if (this.isFlying()) {

                // Наземная навигация сейчас не вмешивается
                this.getNavigation().stop();

                this.setXxa(0.0F);
                this.setZza(0.0F);

                if (this.flightTicks > 0) {

                    this.flightTicks--;

                    // ВАЖНО:
                    // цель фиксированная, выбранная в startFlying()
                    this.getMoveControl().setWantedPosition(
                            this.flightTargetX,
                            this.flightTargetY,
                            this.flightTargetZ,
                            1.0D
                    );

                    // Полёт закончился -> возвращаем гравитацию
                    if (this.flightTicks == 0) {
                        this.landingTicks = 40; // примерно 2 секунды на снижение
                    }

                }
                else if (this.landingTicks > 0) {

                    this.landingTicks--;

                    this.setNoGravity(true);

                    this.setDeltaMovement(
                            this.getDeltaMovement().x * 0.8D,
                            -0.08D,
                            this.getDeltaMovement().z * 0.8D
                    );

                    if (this.onGround()) {
                        this.landingTicks = 0;
                        this.setNoGravity(false);
                        this.setFlying(false);
                    }
                }
                else if (this.onGround()) {
                    this.setNoGravity(false);
                    this.setFlying(false);
                }
            }
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
    private static final EntityDataAccessor<Boolean> DATA_FLYING =
            SynchedEntityData.defineId(
                    DragonEntity.class,
                    EntityDataSerializers.BOOLEAN
            );


    private int flightTicks = 0;
    private int flightCooldown = 100;
    private int landingTicks = 0;
    private double flightTargetX;
    private double flightTargetY;
    private double flightTargetZ;
    private static final int TEST_FLIGHT_TIME = 20 * 3;


    public boolean isFlying() {
        return this.entityData.get(DATA_FLYING);
    }

    public void setFlying(boolean flying) {
        this.entityData.set(DATA_FLYING, flying);
    }

    public void startFlying() {

        this.setFlying(true);
        this.setNoGravity(true);

        this.flightTicks = TEST_FLIGHT_TIME;

        double angle = Math.toRadians(this.getYRot());

        this.flightTargetX =
                this.getX() - Math.sin(angle) * 8.0D;

        this.flightTargetZ =
                this.getZ() + Math.cos(angle) * 8.0D;

        this.flightTargetY =
                this.getY() + 2.0D;

        // Небольшой первоначальный толчок вверх
        this.setDeltaMovement(
                this.getDeltaMovement().x,
                0.15D,
                this.getDeltaMovement().z
        );
    }
    @Override
    public boolean causeFallDamage(
            double fallDistance,
            float damageMultiplier,
            DamageSource damageSource
    ) {
        if (this.isFlying()) {
            return false;
        }

        return super.causeFallDamage(
                fallDistance,
                damageMultiplier,
                damageSource
        );
    }
    @Override
    protected void checkFallDamage(
            double heightDifference,
            boolean onGround,
            BlockState state,
            BlockPos landedPosition
    ) {
        if (this.isFlying() || this.landingTicks > 0) {
            this.fallDistance = 0.0F;
            return;
        }

        super.checkFallDamage(
                heightDifference,
                onGround,
                state,
                landedPosition
        );
    }

}
