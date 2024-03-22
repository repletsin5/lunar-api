package com.github.repletsin5.lunar.registry.api.blocks;


import com.github.repletsin5.lunar.resources.api.resources.ModAsset;

public class ModAssetBlock {
    public ModAssetBlock(ModBlock b, ModAsset mb){
        this.block = b;
        this.asset = mb;
    }

    ModAssetBlock() { }

    public ModBlock block = null;
    public ModAsset asset = null;
}
