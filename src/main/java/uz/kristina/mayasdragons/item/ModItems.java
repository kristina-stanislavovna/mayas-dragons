package uz.kristina.mayasdragons.item;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.registries.BuiltInRegistries;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import net.minecraft.core.Registry;
import net.minecraft.world.item.ItemStack;
import uz.kristina.mayasdragons.MayaSDragons;


import java.util.function.Function;


public class ModItems {
    public static void registerModItems() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
                DRAGONS_TAB_KEY, DRAGONS_TAB);

    }

    public static final ResourceKey<CreativeModeTab> DRAGONS_TAB_KEY =
            ResourceKey.create(
                    BuiltInRegistries.CREATIVE_MODE_TAB.key(),
                    MayaSDragons.id("dragons_tab")
            );

    public static final Item DRAGON_SCALE =
            register(ModItemIds.DRAGON_SCALE, Item::new, new Item.Properties());

    public static final CreativeModeTab DRAGONS_TAB = FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(DRAGON_SCALE))
            .title(Component.translatable("creativeTab.mayas_dragons"))
            .displayItems(((parameters, output) -> {
                output.accept(DRAGON_SCALE);
            })).build();


    public static Item register(
            ResourceKey<Item> itemKey,
            Function<Item.Properties, Item> itemFactory,
            Item.Properties properties) {

        Item item = itemFactory.apply(properties.setId(itemKey));

        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }
}
