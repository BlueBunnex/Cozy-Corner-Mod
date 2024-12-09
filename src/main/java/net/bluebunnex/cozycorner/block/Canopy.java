package net.bluebunnex.cozycorner.block;

import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

public class Canopy extends TemplateBlock {

    public Canopy(Identifier identifier) {

        super(identifier, Material.WOOL);
    }
}
