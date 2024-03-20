package com.github.repletsin5.lunar.mixins.core;

import com.badlogic.gdx.files.FileHandle;
import finalforeach.cosmicreach.GameAssetLoader;
import finalforeach.cosmicreach.world.blocks.BlockModelJson;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.regex.Pattern;

import static com.github.repletsin5.lunar.api.resources.LoadAssetAPI.ASSET_KEY;

@Mixin(BlockModelJson.class)
public class BlockModelJsonMixin {
    @Redirect(method = "getInstance", at = @At(value = "INVOKE", target = "Lfinalforeach/cosmicreach/GameAssetLoader;loadAsset(Ljava/lang/String;)Lcom/badlogic/gdx/files/FileHandle;"))
    private static FileHandle getModelFromModID(String fileName) {
        String noFolder = fileName.replace("models/blocks/","");
        if (noFolder.contains(":")) {
            String[] split = noFolder.split(Pattern.quote(":"));
            if(split.length!= 2) {
                return null;
            }
            return GameAssetLoader.loadAsset(split[0] + ASSET_KEY + "models/blocks/" + split[1]);
        }
        return GameAssetLoader.loadAsset(fileName);
    }
}
