package me.replet.lunar.api.Blocks;

import com.badlogic.gdx.files.FileHandle;
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
