package uz.kristina.mayasdragons.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.tags.BlockTags;
import uz.kristina.mayasdragons.MayaSDragons;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;


public class DragonEggBlockEntity extends BlockEntity {
    private static final int REQUIRED_FLOWERS = 3;
    private static final int FLOWER_RADIUS = 2;
    private static final int HATCH_TIME = 200;
    private int hatchTicks = 0;


    public DragonEggBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DRAGON_EGG, pos, state);
    }
    public static void tick(
            Level level,
            BlockPos pos,
            BlockState state,
            DragonEggBlockEntity entity
    ) {
        if (level.isClientSide()) {
            return;
        }
        if (hasEnoughFlowers(level, pos)) {
            if (entity.hatchTicks < HATCH_TIME) {
                entity.hatchTicks++;

                if (entity.hatchTicks % 20 == 0) {
                    MayaSDragons.LOGGER.info(
                            "Dragon egg is growing! hatchTicks = {}",
                            entity.hatchTicks
                    );
                }
                if (entity.hatchTicks == HATCH_TIME) {
                    MayaSDragons.LOGGER.info(
                            "The dragon is ready to hatch!"
                    );
                }
            }
        }if (entity.hatchTicks >= HATCH_TIME
                && level instanceof ServerLevel serverLevel) {

            if (level.getGameTime() % 10 == 0) {
                serverLevel.sendParticles(
                        ParticleTypes.ENCHANT,
                        pos.getX() + 0.5,
                        pos.getY() + 1.0,
                        pos.getZ() + 0.5,
                        6,
                        0.4,
                        0.5,
                        0.4,
                        0.04
                );
            }
            if (level.getGameTime() % 60 == 0) {
                serverLevel.playSound(
                        null,
                        pos,
                        SoundEvents.AMETHYST_BLOCK_CHIME,
                        SoundSource.BLOCKS,
                        0.35F,
                        0.8F
                );
            }
        }

    }
    private static boolean hasEnoughFlowers(Level level, BlockPos eggPos) {

        int flowers = 0;

        for (BlockPos checkPos : BlockPos.betweenClosed(
                eggPos.offset(-FLOWER_RADIUS, -1, -FLOWER_RADIUS),
                eggPos.offset(FLOWER_RADIUS, 1, FLOWER_RADIUS)
        )) {

            if (level.getBlockState(checkPos).is(BlockTags.FLOWERS)) {
                flowers++;

                if (flowers >= REQUIRED_FLOWERS) {
                    return true;
                }
            }
        }

        return false;
    }
}