package me.replet.lunar.api.modmenu;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public interface ModConfigButtonAPI {
    default ConfigScreenFactory<?> getModConfigScreenFactory() {
        return screen -> null;
    }

    default Map<String, ConfigScreenFactory<?>> getProvidedConfigScreenFactories(){
        return ImmutableMap.of();
    }
}
