package uz.kristina.mayasdragons.block;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;
public class ModBlocks {
    public static final Block DRAGON_EGG =
            register(
                    ModBlockIds.DRAGON_EGG,
                    DragonEggBlock::new,
                    BlockBehaviour.Properties.of()
            );
    public static Block register(
            ResourceKey<Block> blockKey,
            Function<BlockBehaviour.Properties, Block> blockFactory,
            BlockBehaviour.Properties properties) {

        Block block = blockFactory.apply(properties.setId(blockKey));

        return Registry.register(
                BuiltInRegistries.BLOCK,
                blockKey,
                block
        );
    }
    public static void registerModBlocks() {

    }
}
