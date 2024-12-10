package net.bluebunnex.cozycorner.block;

import net.bluebunnex.cozycorner.CozyCorner;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.item.ItemPlacementContext;
import net.modificationstation.stationapi.api.state.StateManager;
import net.modificationstation.stationapi.api.state.property.IntProperty;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.math.Direction;

public class FurnitureBlock extends TemplateBlock {

    public static final IntProperty FACING = IntProperty.of("facing", 0, 3);

    public final Box shape;

    public FurnitureBlock(Identifier identifier, Material material, Box shape) {
        super(identifier, material);

        this.setTranslationKey(CozyCorner.NAMESPACE, identifier.path);

        this.setResistance(0.8f);
        this.setHardness(0.8f);

        this.shape = shape;
    }

    @Override
    public boolean isOpaque() {
        return false;
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    @Override
    public Box getBoundingBox(World world, int x, int y, int z) {

        return getCollisionShape(world, x, y, z);
    }

    @Override
    public Box getCollisionShape(World world, int x, int y, int z) {

        BlockState bs = world.getBlockState(x, y, z);

        // immediately on place the block at this position is still air, so we have to check for that
        if (bs.contains(FACING)) {
            return getTurnedShape(shape, (int) bs.get(FACING)).offset(x, y, z);
        } else {
            return Box.create(x, y, z, x + 1, y + 1, z + 1);
        }
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {

        builder.add(FACING);
        super.appendProperties(builder);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {

        // directions gives the cardinal directions in the order of how prevalent they are
        // based on the player's look direction (I think)
        Direction[] directions = context.getPlacementDirections();
        int facing = 0;

        for (int i=0; i<directions.length; i++) {

            if (directions[i].getId() > 1) {

                facing = switch (directions[i].getId()) {
                    case 2 -> 2; // east
                    case 5 -> 3; // south
                    case 3 -> 0; // west
                    default -> 1; // north
                };

                break;
            }
        }

        return getDefaultState().with(FACING, facing);
    }

    private static Box getTurnedShape(Box shape, int facing) {

        if (facing == 0 || facing == 2) {
            return shape;
        }

        return Box.create(shape.minZ, shape.minY, shape.minX, shape.maxZ, shape.maxY, shape.maxX);
    }
}
