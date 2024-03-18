package me.replet.lunar.api.Blocks;

import finalforeach.cosmicreach.world.BlockPosition;
import finalforeach.cosmicreach.world.World;
import me.replet.lunar.api.event.Event;
import me.replet.lunar.api.event.EventFactory;

public class BlockEvents {

    public static final Event<AfterBlockBreak> AFTER_BLOCK_BREAK= EventFactory.createArrayBacked(AfterBlockBreak.class, callbacks->(world, pos, timeSinceLastInteract)->{
        for (AfterBlockBreak callback : callbacks) {
            //null check so game does not crash
            if(callback!=null) callback.afterBlockBreak(world,pos,timeSinceLastInteract);
        }
    });

    @FunctionalInterface
    public interface AfterBlockBreak{
        void afterBlockBreak(World world, BlockPosition pos,double timeSinceLastInteract);
    }
}
