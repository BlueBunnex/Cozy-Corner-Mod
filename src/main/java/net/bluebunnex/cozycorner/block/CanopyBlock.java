package net.bluebunnex.cozycorner.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.item.ItemPlacementContext;
import net.modificationstation.stationapi.api.state.StateManager;
import net.modificationstation.stationapi.api.state.property.BooleanProperty;
import net.modificationstation.stationapi.api.util.Identifier;

public class CanopyBlock extends FurnitureBlock {

    public static final BooleanProperty IS_EDGE = BooleanProperty.of("is_edge");

    public CanopyBlock(Identifier identifier) {
        super(identifier, Material.WOOL, Box.create(0, 0, 0, 1, 0.5, 1));
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {

        builder.add(IS_EDGE);
        super.appendProperties(builder);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {

        BlockState state = super.getPlacementState(context);
        BlockPos pos = context.getBlockPos();

        return state.with(
                IS_EDGE,
                isEdge(context.getWorld(), pos.getX(), pos.getY(), pos.getZ(), state.get(FACING))
        );
    }

    @Override
    public void neighborUpdate(World world, int x, int y, int z, int id) {
        super.neighborUpdate(world, x, y, z, id);

        BlockState state = world.getBlockState(x, y, z);

        world.setBlockState(x, y, z, state.with(IS_EDGE, isEdge(world, x, y, z, state.get(FACING))));
    }

    private boolean isEdge(World world, int x, int y, int z, int facing) {

        // get block "in front of" using FACING attribute
        // if it's canopy, set this to not edge
        // if it's not canopy, set this to edge
        int offX = x;
        int offZ = z;

        if (facing == 0) { offZ--; }
        if (facing == 1) { offX++; }
        if (facing == 2) { offZ++; }
        if (facing == 3) { offX--; }

        return world.getBlockId(offX, y, offZ) != this.id;
    }
}
