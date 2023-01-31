package turniplabs.transfiguration;

import net.minecraft.src.*;
import org.lwjgl.input.Keyboard;

public class ItemColorWand extends Item {
    public ItemColorWand(int i) {
        super(i);
    }

    @Override
    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l, double heightPlaced) {
        int id = world.getBlockId(i, j, k);

        Block block = Block.getBlock(id);

        if (block instanceof BlockMagicSand) {
            TileEntityMagicSand tileEntity = (TileEntityMagicSand) world.getBlockTileEntity(i, j, k);

            if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                itemstack.tag.setInteger("BlockColor", tileEntity.blockColor);
            } else {
                tileEntity.blockColor = itemstack.tag.getInteger("BlockColor");
                for (int a = 0; a < 10; ++a) {
                    spawnRedStarParticle(world, i, j, k);
                }
                world.playSoundEffect(i+.5, j+.5, k+.5, "step.snow", 1f, 1f);
                world.markBlockAsNeedsUpdate(i, j, k);
                world.notifyBlocksOfNeighborChange(i, j, k, id);
            }

            return true;
        } else {
            itemstack.tag.setInteger("BlockColor", block.colorMultiplier(world, world, i, j, k));
            return true;
        }
    }

    public static void spawnRedStarParticle(World world, int i, int j, int k) {
        double a = Math.random() - 0.5;
        double b = Math.random() - 0.5;

        double x;
        double y;
        double z;

        int face = itemRand.nextInt(6);
        switch (face) {
            case 1:
                x = a;
                y = 0.6;
                z = b;
                break;
            case 2:
                x = a;
                y = -0.6;
                z = b;
                break;
            case 3:
                x = 0.6;
                y = a;
                z = b;
                break;
            case 4:
                x = -0.6;
                y = a;
                z = b;
                break;
            case 5:
                x = a;
                y = b;
                z = 0.6;
                break;
            default:
                x = a;
                y = b;
                z = -0.6;
                break;

        }

        world.spawnParticle("redstar", i+.5 + x, j+.5 + y, k+.5 + z, 0, 0, 0);
    }
}
