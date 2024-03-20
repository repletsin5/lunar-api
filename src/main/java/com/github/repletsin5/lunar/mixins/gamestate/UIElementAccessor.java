package com.github.repletsin5.lunar.mixins.gamestate;

import com.badlogic.gdx.InputProcessor;
import finalforeach.cosmicreach.ui.UIElement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(UIElement.class)
public interface UIElementAccessor extends InputProcessor {
    @Accessor
    public boolean getShown();
}
