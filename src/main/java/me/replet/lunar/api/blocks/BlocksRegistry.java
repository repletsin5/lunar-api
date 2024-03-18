package me.replet.lunar.api.blocks;


import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.google.common.util.concurrent.UncheckedExecutionException;
import finalforeach.cosmicreach.world.blocks.Block;
import finalforeach.cosmicreach.world.blocks.BlockState;
import jdk.jshell.spi.ExecutionControl;
import me.replet.lunar.api.resources.LoadAssetAPI;
import me.replet.lunar.api.resources.ModAsset;

import java.io.UncheckedIOException;
import java.util.*;

import static finalforeach.cosmicreach.GameAssetLoader.loadAsset;
import static finalforeach.cosmicreach.world.blocks.Block.allBlockStates;
import static finalforeach.cosmicreach.world.blocks.Block.allBlocks;
import static me.replet.lunar.Lunar.HasGameLoaded;
import static me.replet.lunar.api.resources.LoadAssetAPI.ASSET_KEY;

public class BlocksRegistry {

    private static final ModBlocks Blocks = new ModBlocks();
    private static final ModBlocks RegisteredBlocks = new ModBlocks();
    private static final List<AbstractMap.SimpleEntry<ModAssetBlock,String>> BlockQueue = new ArrayList<>();

    public static void UseBlockQueue(){
        BlockQueue.forEach(item->{
            Block b = LoadBlockFromJsonByPath(item.getKey().asset.asset);
            item.getKey().block.block=b;
        });
    }

    @SuppressWarnings("all")
    public static ModBlock Register(String modID,String name,ModBlock mblock)  {
        if(modID!="0(((($(&&$$$^^^"){//hacky fix for 'unreachable code'
            throw new UncheckedExecutionException("Register Does not work currently!!!!!!", new Throwable());
        }else {
            if (RegisteredBlocks.Contains(mblock)) {
                return mblock;
            }
            if (Blocks.Contains(mblock)) {
                if (mblock.block != null) {
                    RegisteredBlocks.Add(mblock);
                    allBlocks.add(mblock.block);
                }
                return mblock;
            }

            String id = name.toLowerCase(Locale.ROOT).replaceAll("\\s+", "_");
            mblock = CreateBlock(modID, id, name);
            if (mblock.block == null)
                return mblock;
            RegisteredBlocks.Add(mblock);
            allBlocks.add(mblock.block);
            return mblock;
        }
    }
    public static Block LoadBlockFromJsonByID(String modID,String blockID){
        Json json = new Json();
        Block b = json.fromJson(Block.class, LoadAssetAPI.LoadAsset(modID,"blocks/" + blockID + ".json").handle);
        Array<String> blockStateKeysToAdd = b.blockStates.keys().toArray();

        BlockState blockState;
        for(Array.ArrayIterator var4 = blockStateKeysToAdd.iterator(); var4.hasNext(); allBlockStates.put(blockState.stringId, blockState)) {
            String stateKey = (String)var4.next();
            blockState = (BlockState)b.blockStates.get(stateKey);
            blockState.initialize(b);
            blockState.stringId = stateKey;
            if (blockState.generateSlabs) {
                //generateSlabs(blockState.stringId, blockState);
            }
        }
        return b;
    }
    public static Block LoadBlockFromJsonByPath(String path){
        Json json = new Json();
        Block b = json.fromJson(Block.class, loadAsset(path));
        Array<String> blockStateKeysToAdd = b.blockStates.keys().toArray();

        BlockState blockState;
        for(Array.ArrayIterator var4 = blockStateKeysToAdd.iterator(); var4.hasNext(); allBlockStates.put(blockState.stringId, blockState)) {
            String stateKey = (String)var4.next();
            blockState = (BlockState)b.blockStates.get(stateKey);
            blockState.initialize(b);
            blockState.stringId = stateKey;
            if (blockState.generateSlabs) {
                //generateSlabs(blockState.stringId, blockState);
            }
        }
        return b;
    }
    private static ModBlock CreateBlock(String modID,String blockID,String blockName){

        if(HasGameLoaded()){
            if (RegisteredBlocks.Contains(blockName)) {
                return RegisteredBlocks.GetByName(blockName);
            } else {
                Block b =LoadBlockFromJsonByID(modID, blockID);
                ModBlock block= new ModBlock();
                block.block=b;
                block.name=blockName;
                block.id=blockID;
                Blocks.Add(block);
                return block;
            }
        }else {
            ModBlock hold = new ModBlock();
            hold.name=blockName;
            hold.id=blockID;
            Blocks.Add(hold);
            ModAsset asset= new ModAsset(null,modID+ASSET_KEY+"blocks/" + blockID + ".json");
            BlockQueue.add(new AbstractMap.SimpleEntry<>(new ModAssetBlock(hold, asset),blockName));
            return hold;
        }
    }
}
