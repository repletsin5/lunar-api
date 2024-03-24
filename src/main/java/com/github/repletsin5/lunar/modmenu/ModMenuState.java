package com.github.repletsin5.lunar.modmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.github.repletsin5.lunar.Lunar;
//import com.github.repletsin5.lunar.api.ui.CustomFontRenderer;
import com.github.repletsin5.lunar.mixins.gamestate.UIElementAccessor;
import finalforeach.cosmicreach.gamestates.GameState;
import finalforeach.cosmicreach.ui.FontRenderer;
import finalforeach.cosmicreach.ui.HorizontalAnchor;
import finalforeach.cosmicreach.ui.UIElement;
import finalforeach.cosmicreach.ui.VerticalAnchor;
import net.fabricmc.loader.api.FabricLoader;

public class ModMenuState extends GameState {
    GameState previousState;
    String selectedID = "";
    GameState selectedConfig = null;

    public ModMenuState(final GameState previousState) {
        UIElement doneButton = new UIElement(0.0F, -20.0F, 150.0F, 30.0F) {
            public void onClick() {
                super.onClick();
                GameState.switchToGameState(previousState);
            }
        };
        doneButton.setText("Done");
        doneButton.vAnchor = VerticalAnchor.BOTTOM_ALIGNED;
        doneButton.show();
        this.uiElements.add(doneButton);

        UIElement configButton = new UIElement(0.0F, -20.0F, 150.0F, 30.0F) {
            public void onClick() {
                super.onClick();
                var a = this;
                if(((UIElementAccessor)(Object)this).getShown()) {
                    var state = Lunar.getConfigScreen(selectedID,previousState);
                    if (state != null)
                        GameState.switchToGameState(state);
                }
            }
        };
        configButton.setText("Configure");
        configButton.vAnchor = VerticalAnchor.BOTTOM_ALIGNED;
        configButton.hAnchor = HorizontalAnchor.LEFT_ALIGNED;
        configButton.hide();
        this.uiElements.add(configButton);

        float yStart = 8.0F;
        for (var mod : FabricLoader.getInstance().getAllMods()) {
            String text = mod.getMetadata().getName() + " - " + mod.getMetadata().getVersion().getFriendlyString();

            UIElement element = new UIElement(0F, yStart, 350f, 28.0F) {
                public void onClick() {
                    super.onClick();
                    selectedID = mod.getMetadata().getId();
                    if(Lunar.configScreenFactories.containsKey(mod.getMetadata().getId()))
                        configButton.show();
                    else {
                        if (Lunar.delayedScreenFactoryProviders.stream().anyMatch(obj -> obj.containsKey(mod.getMetadata().getId())))
                            configButton.show();
                        else
                            configButton.hide();
                    }}
            };
            element.hAnchor = HorizontalAnchor.LEFT_ALIGNED;
            element.vAnchor = VerticalAnchor.TOP_ALIGNED;
            element.setText(text);
            element.show();
            this.uiElements.add(element);
            yStart += 28.0F;
        }
    }

    public void render(float partTick) {
        super.render(partTick);
        ScreenUtils.clear(0.145F, 0.078F, 0.153F, 1.0F, true);
        Gdx.gl.glEnable(2929);
        Gdx.gl.glDepthFunc(513);
        Gdx.gl.glEnable(2884);
        Gdx.gl.glCullFace(1029);
        Gdx.gl.glEnable(3042);
        Gdx.gl.glBlendFunc(770, 771);
        if (Gdx.input.isKeyJustPressed(111)) {
            switchToGameState(this.previousState);
        }

        float yStart = 8.0F;
        Vector2 pos = new Vector2();
        batch.setProjectionMatrix(this.uiCamera.combined);
        batch.begin();

        //CustomFontRenderer.drawText(batch,"Test text for font renderer",50,50);
        for (var mod : FabricLoader.getInstance().getAllMods()) {
            if (mod.getMetadata().getId() == selectedID) {
                yStart += pos.y + 4.0F;
                String text = mod.getMetadata().getDescription();
                FontRenderer.getTextDimensions(this.uiViewport, text, pos);
                batch.setColor(Color.GRAY);
                FontRenderer.drawText(batch, this.uiViewport, text, 355.0F, yStart + 2f + 1f, HorizontalAnchor.LEFT_ALIGNED, VerticalAnchor.TOP_ALIGNED);
                batch.setColor(Color.WHITE);
                FontRenderer.drawText(batch, this.uiViewport, text, 355.0F, yStart + 2f, HorizontalAnchor.LEFT_ALIGNED, VerticalAnchor.TOP_ALIGNED);
            }
        }
        batch.end();

        this.drawUIElements();
    }
}
