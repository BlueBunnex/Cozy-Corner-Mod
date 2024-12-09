package net.bluebunnex.cozycorner;

import net.bluebunnex.cozycorner.block.Canopy;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class CozyCorner {

    @Entrypoint.Namespace
    public static final Namespace NAMESPACE = Null.get();

    public static Block canopy;

    @EventListener
    public void registerBlocks(BlockRegistryEvent event) {

        canopy = new Canopy(NAMESPACE.id("canopy"));
    }
}