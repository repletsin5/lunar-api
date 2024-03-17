package me.replet.lunar.api.resources;

import com.badlogic.gdx.files.FileHandle;

public class ModAsset {
    ModAsset(FileHandle handle, String asset){
        this.handle=handle;
        this.asset=asset;
    }
    ModAsset(){}
   public FileHandle handle= null;
   public String asset=null;
}
