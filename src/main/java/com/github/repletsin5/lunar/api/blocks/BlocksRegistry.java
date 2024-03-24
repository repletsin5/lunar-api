package com.github.repletsin5.lunar.api.blocks;


import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.github.repletsin5.lunar.mixins.core.BlockAccessor;
import finalforeach.cosmicreach.world.blocks.Block;
import finalforeach.cosmicreach.world.blocks.BlockState;
import com.github.repletsin5.lunar.api.resources.LoadAssetAPI;
import com.github.repletsin5.lunar.api.resources.ModAsset;

import java.util.*;

import static finalforeach.cosmicreach.GameAssetLoader.loadAsset;
import static com.github.repletsin5.lunar.Lunar.hasGameLoaded;
import static com.github.repletsin5.lunar.api.resources.LoadAssetAPI.ASSET_KEY;
import static finalforeach.cosmicreach.world.blocks.Block.*;

public class BlocksRegistry {
    private static final ModBlocks blocks = new ModBlocks();
    private static final ModBlocks registeredBlocks = new ModBlocks();
    private static final List<AbstractMap.SimpleEntry<ModAssetBlock, String>> blockQueue = new ArrayList<>();

    public static void useBlockQueue() {
        blockQueue.forEach(item -> {
            Block b = loadBlockFromJsonByPath(item.getKey().asset.asset);
            item.getKey().block.block = b;
            blocksByStringId.put(b.getStringId(),b);
        });
    }

    public static ModBlock register(String modID, String name, ModBlock mblock)  {

            if (registeredBlocks.contains(mblock)) {
                return mblock;
            }

            if (blocks.contains(mblock)) {
                if (mblock.block != null) {
                    registeredBlocks.add(mblock);
                    allBlocks.add(mblock.block);
                }
                return mblock;
            }

            String id = name.toLowerCase(Locale.ROOT).replaceAll("\\s+", "_");
            mblock = createBlock(modID, id, name);
            if (mblock.block == null)
                return mblock;
            registeredBlocks.add(mblock);
            allBlocks.add(mblock.block);
            blocksByStringId.put(mblock.block.getStringId(),mblock.block);
            return mblock;
    }

    public static Block loadBlockFromJsonByID(String modID, String blockID) {
        Json json = new Json();
        Block b = json.fromJson(Block.class, LoadAssetAPI.loadAsset(modID,"blocks/" + blockID + ".json").handle);
        Array<String> blockStateKeysToAdd = b.blockStates.keys().toArray();

        BlockState blockState;
        for (Array.ArrayIterator var4 = blockStateKeysToAdd.iterator(); var4.hasNext(); allBlockStates.put(blockState.stringId, blockState)) {
            String stateKey = (String)var4.next();
            blockState = b.blockStates.get(stateKey);
            blockState.initialize(b);
            blockState.stringId = stateKey;
            if (blockState.generateSlabs) {
                BlockAccessor.invokeGenerateSlabs(blockState.stringId, blockState);
            }
        }
        return b;
    }

    public static Block loadBlockFromJsonByPath(String path) {
        Json json = new Json();
        Block b = json.fromJson(Block.class, loadAsset(path));
        Array<String> blockStateKeysToAdd = b.blockStates.keys().toArray();

        BlockState blockState;
        for (Array.ArrayIterator var4 = blockStateKeysToAdd.iterator(); var4.hasNext(); allBlockStates.put(blockState.stringId, blockState)) {
            String stateKey = (String)var4.next();
            blockState = b.blockStates.get(stateKey);
            blockState.initialize(b);
            blockState.stringId = stateKey;
            if (blockState.generateSlabs) {
                BlockAccessor.invokeGenerateSlabs(blockState.stringId, blockState);
            }
        }
        return b;
    }

    private static ModBlock createBlock(String modID, String blockID, String blockName) {
        if(hasGameLoaded()) {
            if (registeredBlocks.contains(blockName)) {
                return registeredBlocks.getByName(blockName);
            } else {
                Block b = loadBlockFromJsonByID(modID, blockID);
                ModBlock block = new ModBlock();
                block.block = b;
                block.name = blockName;
                block.id = blockID;
                blocks.add(block);
                return block;
            }
        } else {
            ModBlock hold = new ModBlock();
            hold.name = blockName;
            hold.id = blockID;
            blocks.add(hold);
            ModAsset asset = new ModAsset(null,modID + ASSET_KEY + "blocks/" + blockID + ".json");
            blockQueue.add(new AbstractMap.SimpleEntry<>(new ModAssetBlock(hold, asset), blockName));
            return hold;
        }
    }
}
