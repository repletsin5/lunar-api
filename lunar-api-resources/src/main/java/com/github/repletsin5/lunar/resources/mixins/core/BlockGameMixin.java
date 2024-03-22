package com.github.repletsin5.lunar.resources.mixins.core;

import com.esotericsoftware.minlog.Log;
import finalforeach.cosmicreach.BlockGame;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.github.repletsin5.lunar.core.Lunar.setGameLoaded;
import static com.github.repletsin5.lunar.resources.api.resources.LoadAssetAPI.useAssetQueue;

@Mixin(BlockGame.class)
public class BlockGameMixin {
    @Inject(method = "create", at= @At(value = "INVOKE", target = "Lfinalforeach/cosmicreach/world/blockevents/BlockEvents;initBlockEvents()V", shift = At.Shift.BEFORE))
    void loadFiles(CallbackInfo ci) {
        Log.info("Loading assets from queue");
        setGameLoaded();
        useAssetQueue();
    }
}
