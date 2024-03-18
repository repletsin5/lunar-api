package me.replet.lunar.mixins.core;

import com.esotericsoftware.minlog.Log;
import finalforeach.cosmicreach.BlockGame;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static me.replet.lunar.Lunar.setGameLoaded;
import static me.replet.lunar.api.blocks.BlocksRegistry.UseBlockQueue;
import static me.replet.lunar.api.resources.LoadAssetAPI.UseAssetQueue;

@Mixin(BlockGame.class)
public class BlockGameMixin {
    @Inject(method = "create",at= @At(value = "INVOKE", target = "Lfinalforeach/cosmicreach/world/blockevents/BlockEvents;initBlockEvents()V",shift = At.Shift.BEFORE))
    void LoadFiles(CallbackInfo ci){
        Log.info("Loading assets from queue");
        setGameLoaded();
        UseAssetQueue();
        UseBlockQueue();
    }
    @Inject(method = "create",at= @At(value = "INVOKE", target = "Lfinalforeach/cosmicreach/world/blockevents/BlockEvents;initBlockEvents()V",shift = At.Shift.AFTER))
    void LoadBlocks(CallbackInfo ci){
        Log.info("Loading Blocks from queue");
        UseBlockQueue();
    }
}
