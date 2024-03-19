package com.github.repletsin5.lunar.api.resources;


import java.util.ArrayList;
import java.util.List;

import static com.github.repletsin5.lunar.Lunar.HasGameLoaded;
import static finalforeach.cosmicreach.GameAssetLoader.loadAsset;

public class LoadAssetAPI {
    public  static final String ASSET_KEY = "¬¬$^$^¬¬";

    private static List<ModAsset> assetQueue = new ArrayList<>();

    public static void UseAssetQueue(){
        for(var a : assetQueue){
           a.handle = loadAsset(a.asset);
        }
    }public static ModAsset LoadAsset(String modID,String path){
        return LoadAsset(modID,path, true);
    }
    public static ModAsset LoadAsset(String modID,String path,boolean addToQueue){
       String key = modID + ASSET_KEY + path;
       if (modID == "base") {
         key=path;
       }
       if(HasGameLoaded()) {
           return new ModAsset(loadAsset(key), key);
       }
       else {
           ModAsset modAsset = new ModAsset(null, key);
           if(addToQueue)
               assetQueue.add(modAsset);
           return modAsset;
       }
    }

    public static String ModAssetPath(String modID,String path){
        return modID+ASSET_KEY+path;
    }


}
