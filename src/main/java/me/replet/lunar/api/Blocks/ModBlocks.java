package me.replet.lunar.api.Blocks;

import com.badlogic.gdx.utils.Array;
import finalforeach.cosmicreach.world.blocks.Block;

public class ModBlocks {
    private Array<ModBlock> Blocks = new Array(ModBlock.class);

    ModBlocks(Array<ModBlock> blocks){
        this.Blocks=blocks;
    }

    ModBlocks(){

    }
    public void Add(ModBlock block){
        if (block== ModBlock.EMPTY)
            return;
        Blocks.add(block);
    }
    public void Add(ModBlock[] blockList){
        for (var b : blockList) {
            if (b == ModBlock.EMPTY)
                continue;
            Blocks.add(b);
        }
    }
    public void Remove(ModBlock block){
        for(int i=0; i < Blocks.size-1; i++){
                if(Blocks.get(i).equals(block)){
                    Blocks.removeIndex(i);
            }
        }
    }
    public void Remove(ModBlock[] blockList){
        for(int i=0; i < Blocks.size-1; i++){
            for (var b : blockList){
                if(Blocks.get(i).equals(b)){
                    Blocks.removeIndex(i);
                }
            }
        }
    }
    public ModBlock Get(Block block){
        for (var mb : Blocks){
            if(block.equals(mb.block)){
                return mb;
            }
        }
        return ModBlock.EMPTY;
    }
    public ModBlock GetByName(String name){
        for (var mb : Blocks){
            if(mb.name.equals(name)){
                return mb;
            }
        }
        return ModBlock.EMPTY;

    }
    public ModBlock Get(String id){
        for (var mb : Blocks){
            if(mb.id.equals(id)){
                return mb;
            }
        }
        return ModBlock.EMPTY;

    }
    public boolean Contains(String s){
        for (var mb : Blocks){
            if(mb.id.equals(s)){
                return true;
            }
        }
        for (var mb : Blocks) {
            if (mb.name.equals(s)) {
                return true;
            }
        }
        return false;
    }
    public boolean Contains(ModBlock b){
        for (var mb : Blocks){
            if(mb.equals(b)){
                return true;
            }
        }
        return false;
    }
    public boolean Contains(Block b){
        for (var mb : Blocks){
            if(mb.block.equals(b)){
                return true;
            }
        }

        return false;
    }
}
