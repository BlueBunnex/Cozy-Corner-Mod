package net.bluebunnex.cozycorner;

import net.bluebunnex.cozycorner.block.FurnitureBlock;
import net.bluebunnex.cozycorner.block.SofaBlock;
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
    public static Block sofa;

    @EventListener
    public void registerBlocks(BlockRegistryEvent event) {

        canopy = new FurnitureBlock(NAMESPACE.id("canopy"));
        sofa = new SofaBlock(NAMESPACE.id("sofa"));
    }
}