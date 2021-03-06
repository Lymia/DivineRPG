package divinerpg.objects.blocks.tile.entity;

import divinerpg.objects.blocks.BlockModFurnace;
import divinerpg.registry.ModBlocks;

public class TileEntityOceanfireFurnace extends TileEntityModFurnace {

    public String getFuranceName() {
        return "tile.oceanfire_furnace.name";
    }

    int getFurnaceSpeed() {
        return 225;
    }

    public boolean needsFuel() {
        return false;
    }

    public void updateBlockState(boolean isBurning) {
        BlockModFurnace.updateBlock(isBurning ? ModBlocks.oceanfireFurnaceOn : ModBlocks.oceanfireFurnace, world, pos);
    }
}