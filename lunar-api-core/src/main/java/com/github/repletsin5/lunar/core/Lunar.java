package com.github.repletsin5.lunar.core;


import net.fabricmc.api.ClientModInitializer;




public class Lunar implements ClientModInitializer {
    private static boolean gameLoaded = false;

    @Override
    public void onInitializeClient() {


    }

    public static void setGameLoaded() {
        gameLoaded = true;
    }
    public static boolean hasGameLoaded(){
        return gameLoaded;
    }
}
