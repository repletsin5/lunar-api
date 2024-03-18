package me.replet.lunar.api.blocks;

import me.replet.lunar.api.resources.ModAsset;

public class ModAssetBlock {
    public ModAssetBlock(ModBlock b, ModAsset mb){
        this.block=b;
        this.asset=mb;
    }
    ModAssetBlock(){}
    public ModBlock block=null;
    public ModAsset asset=null;
}
