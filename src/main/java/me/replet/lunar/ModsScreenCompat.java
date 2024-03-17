package me.replet.lunar;

import com.google.common.collect.ImmutableMap;
import finalforeach.cosmicreach.gamestates.GameState;
import finalforeach.cosmicreach.gamestates.OptionsMenu;
import me.replet.lunar.api.modmenu.ConfigScreenFactory;
import me.replet.lunar.api.modmenu.ModConfigButtonAPI;

import java.util.Map;

public class ModsScreenCompat implements ModConfigButtonAPI {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return OptionsMenu::new;
    }

    @Override
        public Map<String, ConfigScreenFactory<?>> getProvidedConfigScreenFactories() {
            return ImmutableMap.of("cosmic_reach", parent -> new OptionsMenu(parent));
        }

}
