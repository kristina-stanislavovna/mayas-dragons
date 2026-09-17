package uz.kristina.mayasdragons.item;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import uz.kristina.mayasdragons.MayaSDragons;



public class ModItemIds {
    public static final ResourceKey<Item> DRAGON_SCALE = create("dragon_scale");

    public static ResourceKey<Item> create(String name) {
        return ResourceKey.create(Registries.ITEM, MayaSDragons.id(name));
    }
    public static final ResourceKey<Item> DRAGON_EGG =
            create("dragon_egg");


}
