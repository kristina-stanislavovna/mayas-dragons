package uz.kristina.mayasdragons.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public class DragonEggItem extends BlockItem {

    public DragonEggItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {

        if (!level.isClientSide()) {
            player.sendSystemMessage(
                    Component.literal("The dragon egg feels warm...")
            );
        }

        return InteractionResult.SUCCESS;
    }
}