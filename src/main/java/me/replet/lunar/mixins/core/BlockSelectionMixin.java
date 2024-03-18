package me.replet.lunar.mixins.core;

import finalforeach.cosmicreach.world.BlockPosition;
import finalforeach.cosmicreach.world.BlockSelection;
import finalforeach.cosmicreach.world.World;
import finalforeach.cosmicreach.world.blockevents.BlockEventTrigger;
import finalforeach.cosmicreach.world.blocks.BlockState;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Map;

import static me.replet.lunar.api.Blocks.BlockEvents.AFTER_BLOCK_BREAK;

@Mixin(BlockSelection.class)
public class BlockSelectionMixin {
    @Inject(method = "breakBlock",at=@At("TAIL"),locals = LocalCapture.CAPTURE_FAILHARD)
    void afterBreakCallback(World world, BlockPosition blockPos, double timeSinceLastInteract, CallbackInfo ci, BlockState blockState, BlockEventTrigger[] triggers, Map args, int i){
        AFTER_BLOCK_BREAK.invoker().afterBlockBreak(world,blockPos,timeSinceLastInteract);
    }
}
