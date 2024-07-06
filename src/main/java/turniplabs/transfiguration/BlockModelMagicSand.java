package turniplabs.transfiguration;

import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.core.block.Block;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.WorldSource;

public class BlockModelMagicSand<T extends Block> extends BlockModelStandard<T> {
    public BlockModelMagicSand(Block block) {
        super(block);
    }

    @Override
    public IconCoordinate getBlockTexture(WorldSource worldSource, int x, int y, int z, Side side) {
        return ((BlockMagicSand) block).getBlockTexture(worldSource, x, y, z, side, atlasIndices[side.getId()]);
    }
}
