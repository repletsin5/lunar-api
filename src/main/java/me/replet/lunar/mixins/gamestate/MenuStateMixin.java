package me.replet.lunar.mixins.gamestate;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import finalforeach.cosmicreach.RuntimeInfo;
import finalforeach.cosmicreach.gamestates.GameState;
import finalforeach.cosmicreach.gamestates.MainMenu;
import finalforeach.cosmicreach.gamestates.OptionsMenu;
import finalforeach.cosmicreach.ui.FontRenderer;
import finalforeach.cosmicreach.ui.HorizontalAnchor;
import finalforeach.cosmicreach.ui.UIElement;
import finalforeach.cosmicreach.ui.VerticalAnchor;
import me.replet.lunar.modmenu.ModMenuState;
import net.fabricmc.loader.api.FabricLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(finalforeach.cosmicreach.gamestates.MainMenu.class)
public abstract class MenuStateMixin extends GameState {
    private static final String fabricV = FabricLoader.getInstance().getModContainer("fabricloader").get().getMetadata().getVersion().getFriendlyString();
    private static  Vector2 pos = new Vector2();

    @Redirect(method = "render",at = @At(value = "INVOKE", target ="Lfinalforeach/cosmicreach/ui/FontRenderer;getTextDimensions(Lcom/badlogic/gdx/utils/viewport/Viewport;Ljava/lang/String;Lcom/badlogic/gdx/math/Vector2;)Lcom/badlogic/gdx/math/Vector2;",ordinal = 2),remap = false)
    Vector2 getTexDem(Viewport c, String texReg, Vector2 i){
        pos = FontRenderer.getTextDimensions(this.uiViewport, texReg, i);
        return  pos;
    }
    @Redirect(method = "render",at = @At(value = "INVOKE", target ="Lfinalforeach/cosmicreach/ui/FontRenderer;drawText(Lcom/badlogic/gdx/graphics/g2d/SpriteBatch;Lcom/badlogic/gdx/utils/viewport/Viewport;Ljava/lang/String;FFLfinalforeach/cosmicreach/ui/HorizontalAnchor;Lfinalforeach/cosmicreach/ui/VerticalAnchor;)V",ordinal = 7),remap = false)
    void identify(SpriteBatch batch, Viewport uiViewport, String text, float xStart, float yStart, HorizontalAnchor hAnchor, VerticalAnchor vAnchor){
        FontRenderer.drawText(batch, this.uiViewport, text, xStart, yStart, HorizontalAnchor.RIGHT_ALIGNED, VerticalAnchor.BOTTOM_ALIGNED);
        yStart -= pos.y;
        text = "Fabric Loader "+ fabricV;
        FontRenderer.getTextDimensions(this.uiViewport, text, pos);
        batch.setColor(Color.GRAY);
        FontRenderer.drawText(batch, this.uiViewport, text, -7.0F, yStart-2f + 1f, HorizontalAnchor.RIGHT_ALIGNED, VerticalAnchor.BOTTOM_ALIGNED);
        batch.setColor(Color.WHITE);
        FontRenderer.drawText(batch, this.uiViewport, text, -8.0F, yStart-2f, HorizontalAnchor.RIGHT_ALIGNED, VerticalAnchor.BOTTOM_ALIGNED);
    }
    @Inject(method = "create",at=@At(value = "TAIL"),remap = false)
    void ModMenuButtonAdd(CallbackInfo ci){
        UIElement modsButton = new UIElement(175.0F-150.0F-22f-2f, 175.0F-50.0F-22f-2f, 44f, 44f) {
            public void onClick() {
                super.onClick();
                GameState.switchToGameState(new ModMenuState(currentGameState));
            }
        };
        modsButton.setText("Mods");
        modsButton.show();
        this.uiElements.add(modsButton);
    }
}
