package com.github.repletsin5.lunar.registry.api.blocks;

import com.badlogic.gdx.utils.Array;
import finalforeach.cosmicreach.world.blocks.Block;

public class ModBlocks {
    private Array<ModBlock> blocks = new Array(ModBlock.class);

    ModBlocks(Array<ModBlock> blocks){
        this.blocks = blocks;
    }

    ModBlocks() { }

    public void add(ModBlock block) {
        if (block == ModBlock.EMPTY)
            return;
        blocks.add(block);
    }

    public void add(ModBlock[] blockList) {
        for (var b : blockList) {
            if (b == ModBlock.EMPTY)
                continue;
            blocks.add(b);
        }
    }

    public void remove(ModBlock block) {
        for (int i = 0; i < blocks.size-1; i++){
            if (blocks.get(i).equals(block)) {
                blocks.removeIndex(i);
            }
        }
    }

    public void remove(ModBlock[] blockList) {
        for(int i = 0; i < blocks.size-1; i++){
            for (var b : blockList){
                if(blocks.get(i).equals(b)){
                    blocks.removeIndex(i);
                }
            }
        }
    }

    public ModBlock get(Block block) {
        for (var mb : blocks) {
            if(block.equals(mb.block)) {
                return mb;
            }
        }
        return ModBlock.EMPTY;
    }

    public ModBlock getByName(String name) {
        for (var mb : blocks) {
            if(mb.name.equals(name)) {
                return mb;
            }
        }
        return ModBlock.EMPTY;
    }

    public ModBlock get(String id) {
        for (var mb : blocks) {
            if(mb.id.equals(id)) {
                return mb;
            }
        }
        return ModBlock.EMPTY;
    }

    public boolean contains(String s) {
        for (var mb : blocks) {
            if(mb.id.equals(s)) {
                return true;
            }
        }

        for (var mb : blocks) {
            if (mb.name.equals(s)) {
                return true;
            }
        }

        return false;
    }

    public boolean contains(ModBlock b) {
        for (var mb : blocks) {
            if(mb.equals(b)) {
                return true;
            }
        }
        return false;
    }

    public boolean contains(Block b) {
        for (var mb : blocks) {
            if(mb.block.equals(b)) {
                return true;
            }
        }
        return false;
    }
}
