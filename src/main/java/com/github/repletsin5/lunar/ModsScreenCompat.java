package com.github.repletsin5.lunar;

import com.github.repletsin5.lunar.api.modmenu.ConfigScreenFactory;
import com.github.repletsin5.lunar.api.modmenu.ModConfigButtonAPI;
import com.google.common.collect.ImmutableMap;
import finalforeach.cosmicreach.gamestates.OptionsMenu;

import java.util.Map;

public class ModsScreenCompat implements ModConfigButtonAPI {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return OptionsMenu::new;
    }

    @Override
    public Map<String, ConfigScreenFactory<?>> getProvidedConfigScreenFactories() {
        return ImmutableMap.of("cosmic_reach", OptionsMenu::new);
    }
}
