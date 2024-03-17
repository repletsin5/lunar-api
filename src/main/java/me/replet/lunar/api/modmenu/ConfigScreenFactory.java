package me.replet.lunar.api.modmenu;

import finalforeach.cosmicreach.gamestates.GameState;

@FunctionalInterface
public interface ConfigScreenFactory<S extends GameState> {
    S create(GameState parent);
}