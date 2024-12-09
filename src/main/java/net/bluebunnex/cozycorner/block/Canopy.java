package net.bluebunnex.cozycorner.block;

import net.bluebunnex.cozycorner.CozyCorner;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.Box;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.item.ItemPlacementContext;
import net.modificationstation.stationapi.api.state.StateManager;
import net.modificationstation.stationapi.api.state.property.IntProperty;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

public class Canopy extends TemplateBlock {

    public static final IntProperty FACING = IntProperty.of("facing", 0, 3);

    public Canopy(Identifier identifier) {

        super(identifier, Material.WOOL);

        this.setTranslationKey(CozyCorner.NAMESPACE, identifier.path);

        this.setResistance(0.8f);
        this.setHardness(0.8f);
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

        return Box.createCached(x,y,z,x + 1,y + 0.5,z + 1);
    }

    @Override
    public Box getCollisionShape(World world, int x, int y, int z) {

        return Box.createCached(x,y,z,x + 1,y + 0.5,z + 1);
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {

        builder.add(FACING);
        super.appendProperties(builder);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {

        return getDefaultState().with(FACING, ((int) context.getPlayerLookDirection().asRotation()) / 90);
    }
}
