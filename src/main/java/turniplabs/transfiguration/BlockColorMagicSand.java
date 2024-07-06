package turniplabs.transfiguration;

import net.minecraft.client.render.block.color.BlockColor;

import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.client.util.helper.Colors;
import net.minecraft.core.block.Block;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.WorldSource;

public class BlockColorMagicSand extends BlockColor {
    @Override
    public int getFallbackColor(int meta) {
        return 0xFFFFFF;
    }

    @Override
    public int getWorldColor(WorldSource worldSource, int x, int y, int z) {
        return ((BlockMagicSand) worldSource.getBlock(x, y, z)).getWorldColor(worldSource, x, y, z, 0xFFFFFF);
    }
}
