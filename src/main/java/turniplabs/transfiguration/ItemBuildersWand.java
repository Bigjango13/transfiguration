package turniplabs.transfiguration;

import net.minecraft.core.block.Block;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.util.helper.Side;
import org.lwjgl.input.Keyboard;

public class ItemBuildersWand extends Item {
    public ItemBuildersWand(String name, int i) {
        super(name, i);
    }

    @Override
    public void inventoryTick(ItemStack itemstack, World world, Entity entity, int i, boolean flag) {
        double[] firstPosition = itemstack.getData().getDoubleArray("FirstPosition");
        double[] secondPosition = itemstack.getData().getDoubleArray("SecondPosition");

        if (firstPosition.length > 0) {
            ItemTransfigurationWand.spawnGoldenStarParticle(world, (int) firstPosition[0], (int) firstPosition[1], (int) firstPosition[2]);
        }

        if (secondPosition.length > 0) {
            ItemColorWand.spawnRedStarParticle(world, (int) secondPosition[0], (int) secondPosition[1], (int) secondPosition[2]);
        }

    }

    @Override
    public boolean onUseItemOnBlock(ItemStack itemstack, EntityPlayer entityplayer, World world, int blockX, int blockY, int blockZ, Side side, double xPlaced, double yPlaced) {
        int id = world.getBlockId(blockX, blockY, blockZ);
        int metadata = world.getBlockMetadata(blockX, blockY, blockZ);
        Block block = Block.getBlock(id);

        if (Keyboard.isKeyDown(Keyboard.KEY_LMENU)) {
            itemstack.getData().putDoubleArray("SecondPosition", new double[]{blockX, blockY, blockZ});
        } else if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
            double[] firstPosition = itemstack.getData().getDoubleArray("FirstPosition");
            double[] secondPosition = itemstack.getData().getDoubleArray("SecondPosition");

            int maxX = (int) Math.max(firstPosition[0], secondPosition[0]);
            int minX = (int) Math.min(firstPosition[0], secondPosition[0]);
            int maxY = (int) Math.max(firstPosition[1], secondPosition[1]);
            int minY = (int) Math.min(firstPosition[1], secondPosition[1]);
            int maxZ = (int) Math.max(firstPosition[2], secondPosition[2]);
            int minZ = (int) Math.min(firstPosition[2], secondPosition[2]);

            for (int x = minX; x <= maxX; ++x) {
                for (int y = minY; y <= maxY; ++y) {
                    for (int z = minZ; z <= maxZ; ++z) {
                        world.setBlockAndMetadataWithNotify(x, y, z, id, metadata);
                        spawnBlueStarParticle(world, x, y, z);
                        if (block instanceof BlockMagicSand) {
                            TileEntityMagicSand source = (TileEntityMagicSand) world.getBlockTileEntity(blockX, blockY, blockZ);
                            TileEntityMagicSand destination = (TileEntityMagicSand) world.getBlockTileEntity(x, y, z);
                            destination.blockId = source.blockId;
                            destination.blockColor = source.blockColor;
                        }
                    }
                }
            }
        } else {
            itemstack.getData().putDoubleArray("FirstPosition", new double[]{blockX, blockY, blockZ});
        }

        return true;
    }

    public void spawnBlueStarParticle(World world, int i, int j, int k) {
        double a = Math.random() - 0.5;
        double b = Math.random() - 0.5;

        double x;
        double y;
        double z;

        if (world.getBlockId(i, j+1, k) == 0) {
            x = a;
            y = 0.6;
            z = b;
            world.spawnParticle("bluestar", i + .5 + x, j + .5 + y, k + .5 + z, 0.0D, 0.0D, 0.0D, 0);
        }
        if (world.getBlockId(i, j-1, k) == 0) {
            x = a;
            y = -0.6;
            z = b;
            world.spawnParticle("bluestar", i + .5 + x, j + .5 + y, k + .5 + z, 0.0D, 0.0D, 0.0D, 0);
        }
        if (world.getBlockId(i+1, j, k) == 0) {
            x = 0.6;
            y = a;
            z = b;
            world.spawnParticle("bluestar", i + .5 + x, j + .5 + y, k + .5 + z, 0.0D, 0.0D, 0.0D, 0);
        }
        if (world.getBlockId(i-1, j, k) == 0) {
            x = -0.6;
            y = a;
            z = b;
            world.spawnParticle("bluestar", i + .5 + x, j + .5 + y, k + .5 + z, 0.0D, 0.0D, 0.0D, 0);
        }
        if (world.getBlockId(i, j, k+1) == 0) {
            x = a;
            y = b;
            z = 0.6;
            world.spawnParticle("bluestar", i + .5 + x, j + .5 + y, k + .5 + z, 0.0D, 0.0D, 0.0D, 0);
        }
        if (world.getBlockId(i, j, k-1) == 0) {
            x = a;
            y = b;
            z = -0.6;
            world.spawnParticle("bluestar", i + .5 + x, j + .5 + y, k + .5 + z, 0.0D, 0.0D, 0.0D, 0);
        }
    }
}
