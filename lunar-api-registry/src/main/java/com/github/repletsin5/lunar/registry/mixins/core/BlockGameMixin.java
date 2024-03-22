package com.github.repletsin5.lunar.registry.mixins.core;

import com.esotericsoftware.minlog.Log;
import com.github.repletsin5.lunar.registry.api.blocks.BlocksRegistry;
import finalforeach.cosmicreach.BlockGame;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(BlockGame.class)
public class BlockGameMixin {

    @Inject(method = "create", at= @At(value = "INVOKE", target = "Lfinalforeach/cosmicreach/world/blockevents/BlockEvents;initBlockEvents()V", shift = At.Shift.AFTER))
    void loadBlocks(CallbackInfo ci) {
        Log.info("Loading Blocks from queue");
        BlocksRegistry.useBlockQueue();
    }
}
