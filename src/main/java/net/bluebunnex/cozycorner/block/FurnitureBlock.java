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
            return null;
        }
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {

        builder.add(FACING);
        super.appendProperties(builder);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {

        return getDefaultState().with(FACING, ((int) context.getPlayerLookDirection().asRotation() % 360) / 90);
    }

    private static Box getTurnedShape(Box shape, int facing) {

        if (facing == 0 || facing == 2) {
            return shape;
        }

        return Box.create(shape.minZ, shape.minY, shape.minX, shape.maxZ, shape.maxY, shape.maxX);
    }
}
