package net.bluebunnex.cozycorner.block;

import net.bluebunnex.cozycorner.entity.SeatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;

public class SofaBlock extends FurnitureBlock {

    public SofaBlock(Identifier identifier) {
        super(identifier);
    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {

        if (!world.isRemote) {

            SeatEntity seatEntity = new SeatEntity(world, x + 0.5, y + 0.5, z + 0.5, world.getBlockState(x, y, z).get(FACING));
            world.spawnEntity(seatEntity);
            player.setVehicle(seatEntity);
        }

        return true;
    }
}
