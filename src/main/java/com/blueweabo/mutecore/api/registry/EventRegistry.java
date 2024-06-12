package com.blueweabo.mutecore.api.registry;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Nonnull;

import org.jetbrains.annotations.ApiStatus.Internal;

public class EventRegistry {

    public static final @Internal @Nonnull List<PlayerInteractionEvent> PLAYER_INTERACTION_EVENTS = new ArrayList<>();

    /**
     * Register a player interaction event. After beign registered it will be put where it should be depending on its priority.
     */
    public static void registerPlayerInteractionEvent(@Nonnull PlayerInteractionEvent event) {
        PLAYER_INTERACTION_EVENTS.add(event);
        PLAYER_INTERACTION_EVENTS.sort(Comparator.reverseOrder());
    }
}
