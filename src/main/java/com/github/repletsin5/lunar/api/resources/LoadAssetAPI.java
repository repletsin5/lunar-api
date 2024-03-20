package com.github.repletsin5.lunar.api.resources;


import finalforeach.cosmicreach.GameAssetLoader;

import java.util.ArrayList;
import java.util.List;

import static com.github.repletsin5.lunar.Lunar.hasGameLoaded;
import static finalforeach.cosmicreach.GameAssetLoader.loadAsset;

public class LoadAssetAPI {
    public static final String ASSET_KEY = "¬¬$^$^¬¬";

    private static List<ModAsset> assetQueue = new ArrayList<>();

    public static void useAssetQueue(){
        for(var a : assetQueue) {
           a.handle = GameAssetLoader.loadAsset(a.asset);
        }
    }

    public static ModAsset loadAsset(String modID, String path){
        return loadAsset(modID, path, true);
    }

    public static ModAsset loadAsset(String modID, String path, boolean addToQueue){
       String key = modID + ASSET_KEY + path;

       if (modID == "base") {
           key = path;
       }

       if (hasGameLoaded()) {
           return new ModAsset(GameAssetLoader.loadAsset(key), key);
       } else {
           ModAsset modAsset = new ModAsset(null, key);
           if (addToQueue)
               assetQueue.add(modAsset);
           return modAsset;
       }
    }

    public static String modAssetPath(String modID, String path){
        return modID + ASSET_KEY + path;
    }
}
