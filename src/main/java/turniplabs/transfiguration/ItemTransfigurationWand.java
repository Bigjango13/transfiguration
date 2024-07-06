package turniplabs.transfiguration;

import net.minecraft.core.block.Block;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.sound.SoundCategory;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import org.lwjgl.input.Keyboard;

public class ItemTransfigurationWand extends Item {
    public ItemTransfigurationWand(String name, int i) {
        super(name, i);
    }

    @Override
    public boolean onUseItemOnBlock(ItemStack itemstack, EntityPlayer entityplayer, World world, int x, int y, int z, Side side, double xPlaced, double yPlaced) {
        Block block = world.getBlock(x, y, z);

        if (block instanceof BlockMagicSand) {
            TileEntityMagicSand tileEntity = (TileEntityMagicSand) world.getBlockTileEntity(x, y, z);

            if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                itemstack.getData().putInt("BlockId", tileEntity.blockId);
                itemstack.getData().putInt("Meta", world.getBlockMetadata(x, y, z));
            } else {
                tileEntity.blockId = itemstack.getData().getInteger("BlockId");
                world.setBlockMetadata(x, y, z, itemstack.getData().getInteger("Meta"));
                for (int a = 0; a < 10; ++a) {
                    spawnGoldenStarParticle(world, x, y, z);
                }
                world.playSoundEffect(null, SoundCategory.WORLD_SOUNDS, x+.5, y+.5, z+.5, "step.snow", 1f, 1f);
                world.markBlockNeedsUpdate(x, y, z);
                world.notifyBlocksOfNeighborChange(x, y, z, block.id);
            }

            return true;
        }
        if (block.renderAsNormalBlock()) {
            itemstack.getData().putInt("BlockId", block.id);
            itemstack.getData().putInt("Meta", world.getBlockMetadata(x, y, z));
            return true;
        }

        return false;
    }

    public static void spawnGoldenStarParticle(World world, int i, int j, int k) {
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

        world.spawnParticle("goldenstar", i+.5 + x, j+.5 + y, k+.5 + z, 0, 0, 0, 0);
    }
}
