package uz.kristina.mayasdragons.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import uz.kristina.mayasdragons.MayaSDragons;
import uz.kristina.mayasdragons.block.ModBlocks;

public class ModBlockEntities {

    public static final BlockEntityType<DragonEggBlockEntity> DRAGON_EGG =
            register(
                    "dragon_egg",
                    DragonEggBlockEntity::new,
                    ModBlocks.DRAGON_EGG
            );

    private static <T extends BlockEntity> BlockEntityType<T> register(
            String name,
            FabricBlockEntityTypeBuilder.Factory<? extends T> entityFactory,
            Block... blocks
    ) {
        Identifier id = MayaSDragons.id(name);

        return Registry.register(
                BuiltInRegistries.BLOCK_ENTITY_TYPE,
                id,
                FabricBlockEntityTypeBuilder.<T>create(entityFactory, blocks).build()
        );
    }

    public static void registerModBlockEntities() {

    }
}