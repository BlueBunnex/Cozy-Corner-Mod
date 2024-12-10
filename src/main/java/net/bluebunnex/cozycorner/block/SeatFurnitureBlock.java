package net.bluebunnex.cozycorner.block;

import net.bluebunnex.cozycorner.entity.SeatEntity;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;

public class SeatFurnitureBlock extends FurnitureBlock {

    public final double rideHeight;

    public SeatFurnitureBlock(Identifier identifier, Material material, Box shape, double rideHeight) {
        super(identifier, material, shape);

        this.rideHeight = rideHeight;
    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {

        if (!world.isRemote) {

            SeatEntity seatEntity = new SeatEntity(world, x + 0.5, y + rideHeight, z + 0.5, world.getBlockState(x, y, z).get(FACING));
            world.spawnEntity(seatEntity);
            player.setVehicle(seatEntity);
        }

        return true;
    }
}
