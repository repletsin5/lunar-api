package me.replet.lunar.api.gamestates;

import finalforeach.cosmicreach.gamestates.GameState;
import me.replet.lunar.api.event.Event;
import me.replet.lunar.api.event.EventFactory;

public class GameStateEvents {
    public static final Event<ChangeState> AFTER_CHANGE_STATE= EventFactory.createArrayBacked(ChangeState.class, callbacks->(gameState) -> {
        for (var callback : callbacks) {
            //null check so game does not crash
            if(callback!=null) callback.changeState(gameState);
        }
    });
    public static final Event<ChangeState> BEFORE_CHANGE_STATE= EventFactory.createArrayBacked(ChangeState.class, callbacks->(gameState) -> {
        for (var callback : callbacks) {
            //null check so game does not crash
            if(callback!=null) callback.changeState(gameState);
        }
    });


    @FunctionalInterface
    public interface ChangeState{
        void changeState(GameState gameState);
    }
}
