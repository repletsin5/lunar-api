package com.github.repletsin5.lunar.mixins.core;

import com.esotericsoftware.minlog.Log;
import com.github.repletsin5.lunar.Lunar;
import com.github.repletsin5.lunar.api.blocks.BlocksRegistry;
import finalforeach.cosmicreach.BlockGame;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.github.repletsin5.lunar.api.resources.LoadAssetAPI.useAssetQueue;

@Mixin(BlockGame.class)
public class BlockGameMixin {
    @Inject(method = "create", at= @At(value = "INVOKE", target = "Lfinalforeach/cosmicreach/world/blockevents/BlockEvents;initBlockEvents()V", shift = At.Shift.BEFORE))
    void loadFiles(CallbackInfo ci) {
        Log.info("Loading assets from queue");
        Lunar.setGameLoaded();
        useAssetQueue();
    }

    @Inject(method = "create", at= @At(value = "INVOKE", target = "Lfinalforeach/cosmicreach/world/blockevents/BlockEvents;initBlockEvents()V", shift = At.Shift.AFTER))
    void loadBlocks(CallbackInfo ci) {
        Log.info("Loading Blocks from queue");
        BlocksRegistry.useBlockQueue();
    }
}
