package me.replet.lunar.mixins.core;

import com.esotericsoftware.minlog.Log;
import finalforeach.cosmicreach.BlockGame;
import net.fabricmc.loader.impl.util.log.LogHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static me.replet.lunar.api.resources.LoadAssetAPI.UseAssetQueue;

@Mixin(BlockGame.class)
public class BlockGameMixin {
    @Inject(method = "create",at= @At(value = "INVOKE", target = "Lfinalforeach/cosmicreach/world/blockevents/BlockEvents;initBlockEvents()V",shift = At.Shift.BEFORE))
    void loadFiles(CallbackInfo ci){
        Log.info("Loading assets from queue");
        UseAssetQueue();
    }
}
