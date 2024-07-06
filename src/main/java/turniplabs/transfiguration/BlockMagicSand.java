package turniplabs.transfiguration;

import net.minecraft.client.render.block.color.BlockColorDispatcher;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockTileEntity;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.WorldSource;

public class BlockMagicSand extends BlockTileEntity {
    public BlockMagicSand(String key, int id, Material material) {
            super(key, id, material);
    }

    @Override
    protected TileEntity getNewBlockEntity() {
        return new TileEntityMagicSand();
    }

    public int getWorldColor(WorldSource worldSource, int x, int y, int z, int defaultColor) {
        TileEntityMagicSand tileEntity = (TileEntityMagicSand) worldSource.getBlockTileEntity(x, y, z);
        if (tileEntity.blockColor == 0) {
            return defaultColor;
        }
        return tileEntity.blockColor;
    }

    public IconCoordinate getBlockTexture(WorldSource worldSource, int x, int y, int z, Side side, IconCoordinate defaultIcon) {
        TileEntityMagicSand tileEntity = (TileEntityMagicSand) worldSource.getBlockTileEntity(x, y, z);
        Block block = getBlock(tileEntity.blockId);
        if (block == null || block instanceof BlockMagicSand) {
            return defaultIcon;
        }
        return BlockModelDispatcher.getInstance().getDispatch(block).getBlockTexture(worldSource, x, y, z, side);
    }
}
