package me.replet.lunar.api.resources;

import com.badlogic.gdx.files.FileHandle;

import java.util.ArrayList;
import java.util.List;

import static finalforeach.cosmicreach.GameAssetLoader.loadAsset;
import static me.replet.lunar.Lunar.HasGameLoaded;

public class LoadAssetAPI {
    public  static final String ASSET_KEY = "¬¬$^$^¬¬";

    private static List<ModAsset> assetQueue = new ArrayList<>();

    public static void UseAssetQueue(){
        for(var a : assetQueue){
           a.handle = loadAsset(a.asset);
        }
    }
    public static ModAsset LoadAsset(String modID,String path){
        String key = modID + ASSET_KEY + path;
       if(HasGameLoaded())
          return new ModAsset(loadAsset(key),key);
       else {
           ModAsset modAsset = new ModAsset(null, key);
           assetQueue.add(modAsset);
           return modAsset;
       }
    }


}
