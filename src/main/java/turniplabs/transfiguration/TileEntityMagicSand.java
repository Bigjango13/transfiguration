package turniplabs.transfiguration;

import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.TileEntity;

public class TileEntityMagicSand extends TileEntity {
    public int blockId;
    public int blockColor;

    @Override
    public void readFromNBT(NBTTagCompound nbttagcompound) {
        super.readFromNBT(nbttagcompound);
        this.blockId = nbttagcompound.getInteger("BlockId");
        this.blockColor = nbttagcompound.getInteger("BlockColor");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbttagcompound) {
        super.writeToNBT(nbttagcompound);
        nbttagcompound.setInteger("BlockId", this.blockId);
        nbttagcompound.setInteger("BlockColor", this.blockColor);
    }
}
