package uz.kristina.mayasdragons.entity;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import uz.kristina.mayasdragons.MayaSDragons;

public class ModEntityTypeIds {

    public static final ResourceKey<EntityType<?>> DRAGON =
            create("dragon");

    private static ResourceKey<EntityType<?>> create(String name) {
        return ResourceKey.create(
                Registries.ENTITY_TYPE,
                MayaSDragons.id(name)
        );
    }
}