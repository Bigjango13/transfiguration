package turniplabs.transfiguration;

import net.minecraft.src.*;

public class BlockMagicSand extends BlockContainer {
    public BlockMagicSand(int i, Material material) {
        super(i, material);
    }

    @Override
    protected TileEntity getBlockEntity() {
        return new TileEntityMagicSand();
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public int colorMultiplier(World world, IBlockAccess iblockaccess, int i, int j, int k) {
        TileEntityMagicSand tileEntity = (TileEntityMagicSand) iblockaccess.getBlockTileEntity(i, j, k);
        if (tileEntity.blockColor == 0) {
            return super.colorMultiplier(world, iblockaccess, i, j, k);
        }
        return tileEntity.blockColor;
    }

    @Override
    public int getBlockTexture(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        TileEntityMagicSand tileEntity = (TileEntityMagicSand) iblockaccess.getBlockTileEntity(i, j, k);
        Block block = getBlock(tileEntity.blockId);
        if (block == null) {
            return super.getBlockTexture(iblockaccess, i, j, k, l);
        }
        return block.getBlockTexture(iblockaccess, i, j, k, l);
    }
}
