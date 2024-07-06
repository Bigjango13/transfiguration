package turniplabs.transfiguration;

import com.mojang.nbt.CompoundTag;
import net.minecraft.core.block.entity.TileEntity;

public class TileEntityMagicSand extends TileEntity {
    public int blockId;
    public int blockColor;

    @Override
    public void readFromNBT(CompoundTag tag) {
        super.readFromNBT(tag);
        this.blockId = tag.getInteger("BlockId");
        this.blockColor = tag.getInteger("BlockColor");
    }

    @Override
    public void writeToNBT(CompoundTag tag) {
        super.writeToNBT(tag);
        tag.putInt("BlockId", this.blockId);
        tag.putInt("BlockColor", this.blockColor);
    }
}
