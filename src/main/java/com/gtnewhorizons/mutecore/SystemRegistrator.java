package com.gtnewhorizons.mutecore;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import dev.dominion.ecs.api.Scheduler;

public class SystemRegistrator {

    private static final Scheduler SYSTEMS = MuTECore.ENGINE.createScheduler();

    /**
     * Register a single system. It will be ran after the last registered system
     */
    public static void registerSystem(Runnable system) {
        SYSTEMS.schedule(system);
    }

    /**
     * Register any amount of systems. The systems will be scheduled one after another.
     */
    public static void registerSystems(Runnable... systems) {
        for (Runnable system : systems) {
            SYSTEMS.schedule(system);
        }
    }

    /**
     * Register any amount of systems. The systems registered this way will be ran in parallel
     */
    public static void registerSystemsParallel(Runnable... systems) {
        int processors = Runtime.getRuntime()
            .availableProcessors();
        Runnable[] parallel = new Runnable[processors];
        int count = 0;
        for (Runnable system : systems) {
            parallel[count++] = system;
            if (count >= processors) {
                SYSTEMS.parallelSchedule(parallel);
                count = 0;
                parallel = new Runnable[processors];
            }
        }
    }

    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event) {
        SYSTEMS.tick();
    }
}
