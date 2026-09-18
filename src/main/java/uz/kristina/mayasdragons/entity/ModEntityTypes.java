package uz.kristina.mayasdragons.entity;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;

public class ModEntityTypes {

    public static final EntityType<DragonEntity> DRAGON =
            register(
                    ModEntityTypeIds.DRAGON,
                    EntityType.Builder.<DragonEntity>of(
                                    DragonEntity::new,
                                    MobCategory.CREATURE
                            )
                            .sized(0.75F, 0.9F)
            );

    private static <T extends Entity> EntityType<T> register(
            ResourceKey<EntityType<?>> key,
            EntityType.Builder<T> builder
    ) {
        return Registry.register(
                BuiltInRegistries.ENTITY_TYPE,
                key,
                builder.build(key)
        );
    }

    public static void registerModEntityTypes() {

    }
    public static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(
                DRAGON,
                DragonEntity.createDragonAttributes()
        );
    }
}