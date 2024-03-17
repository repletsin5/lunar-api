package me.replet.lunar.mixins.resources;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import finalforeach.cosmicreach.io.SaveLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.PrintStream;
import java.util.regex.Pattern;

import static finalforeach.cosmicreach.GameAssetLoader.ALL_ASSETS;
import static me.replet.lunar.api.resources.LoadAssetAPI.ASSET_KEY;

@Mixin(finalforeach.cosmicreach.GameAssetLoader.class)
public class AssetLoaderMixin {

    /**
     * @author replet
     * @reason So mods can load assets
     **/
    @Inject(method = "loadAsset(Ljava/lang/String;Z)Lcom/badlogic/gdx/files/FileHandle;",at= @At(value = "INVOKE_ASSIGN", target = "Lcom/badlogic/gdx/Files;absolute(Ljava/lang/String;)Lcom/badlogic/gdx/files/FileHandle;",shift = At.Shift.BEFORE),cancellable = true)
    private static void file(String fileName, boolean forceReload, CallbackInfoReturnable<FileHandle> cir){
        String arr[]= null;
        if(fileName.contains(ASSET_KEY)){
            arr = fileName.split(Pattern.quote(ASSET_KEY));
        }
        if(arr!=null){
            if(arr.length==2){
                FileHandle file = Gdx.files.classpath("assets/" + arr[0] + "/" + arr[1]);
                if (file.exists()) {
                    System.out.println(" from fabric mod");
                    ALL_ASSETS.put(fileName, file);
                    cir.setReturnValue(file);
                }
            }
        }
    }
    @Redirect(method = "loadAsset(Ljava/lang/String;Z)Lcom/badlogic/gdx/files/FileHandle;", at= @At(value = "INVOKE", target = "Ljava/io/PrintStream;print(Ljava/lang/String;)V"))
    private static void printCapture(PrintStream instance, String s){
            if(s.contains(ASSET_KEY)){
               String a= s.replace("Loading ","");
               String b=  a.replace(ASSET_KEY,":");
               s = "Loading "+b;
            }
            instance.print(s);

    }
}
