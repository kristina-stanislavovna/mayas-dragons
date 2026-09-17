package uz.kristina.mayasdragons.block;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import uz.kristina.mayasdragons.MayaSDragons;

public class ModBlockIds {

    public static final ResourceKey<Block> DRAGON_EGG =
            create("dragon_egg");

    public static ResourceKey<Block> create(String name) {
        return ResourceKey.create(
                Registries.BLOCK,
                MayaSDragons.id(name)
        );
    }
}