package net.bluebunnex.cozycorner;

import net.bluebunnex.cozycorner.block.FurnitureBlock;
import net.bluebunnex.cozycorner.block.SeatFurnitureBlock;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.Box;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class CozyCorner {

    @Entrypoint.Namespace
    public static final Namespace NAMESPACE = Null.get();

    public static Block canopy;
    public static Block sofa;
    public static Block sitting_log;
    public static Block folding_chair;

    @EventListener
    public void registerBlocks(BlockRegistryEvent event) {

        canopy = new FurnitureBlock(NAMESPACE.id("canopy"));

        sofa = new SeatFurnitureBlock(NAMESPACE.id("sofa"), Material.WOOL, Box.create(0, 0, 0, 1, 0.375, 1), 0.5);
        sitting_log = new SeatFurnitureBlock(NAMESPACE.id("sitting_log"), Material.WOOD, Box.create(0, 0, 0.375, 1, 0.25, 0.625), 0.25);
        folding_chair = new SeatFurnitureBlock(NAMESPACE.id("folding_chair"), Material.METAL, Box.create(0.125, 0, 0.125, 0.875, 0.375, 0.875), 0.5);
    }
}