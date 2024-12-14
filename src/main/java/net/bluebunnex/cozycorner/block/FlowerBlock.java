package net.bluebunnex.cozycorner.block;

import net.bluebunnex.cozycorner.CozyCorner;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

// code stolen from PlantBlock class
public class FlowerBlock extends TemplateBlock {

    public FlowerBlock(Identifier identifier) {
        super(identifier, Material.PLANT);

        this.setTickRandomly(true);

        this.setTranslationKey(CozyCorner.NAMESPACE, identifier.path);

        this.setResistance(0.0f);
        this.setHardness(0.0f);

        float radius = 0.2F;
        this.setBoundingBox(0.5F - radius, 0.0F, 0.5F - radius, 0.5F + radius, radius * 3.0F, 0.5F + radius);
    }

    @Override
    public boolean canPlaceAt(World world, int x, int y, int z) {

        int below = world.getBlockId(x, y - 1, z);

        return
                super.canPlaceAt(world, x, y, z)
                && (below == Block.GRASS_BLOCK.id || below == Block.DIRT.id);
    }

    @Override
    public Box getCollisionShape(World world, int x, int y, int z) {
        return null;
    }

    public boolean isOpaque() {
        return false;
    }

    public boolean isFullCube() {
        return false;
    }
}
