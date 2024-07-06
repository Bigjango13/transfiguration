package turniplabs.transfiguration;

import net.minecraft.client.render.block.color.BlockColor;
import net.minecraft.client.render.block.color.BlockColorDefault;
import net.minecraft.client.render.block.color.BlockColorDispatcher;
import net.minecraft.core.block.Block;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.sound.SoundCategory;
import net.minecraft.core.world.World;
import net.minecraft.core.util.helper.Side;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class ItemColorWand extends Item {
    public ItemColorWand(String name, int i) {
        super(name, i);
    }

    public static final int[] colors = {
        0xf0f0f0, 0xeb8844, 0xc354cd, 0x6689d3, 0xdecf2a, 0x41cd34, 0xd88198, 0x434343,
        0x949997, 0x287697, 0x7b2fbe, 0x253192, 0x51301a, 0x3b511a, 0xb3312c, 0x1e1b1b
    };
    public static ArrayList<Integer> blockIds1to1Color = new ArrayList<>(Arrays.asList(new Integer[]{
        Block.planksOakPainted.id, Block.fencePlanksOakPainted.id, Block.wool.id,
        Block.lampIdle.id, Block.lampActive.id
    }));
    public static ArrayList<Integer> blockIds1to16Color = new ArrayList<>(Arrays.asList(new Integer[]{
        Block.fencegatePlanksOakPainted.id, Block.slabPlanksOakPainted.id, Block.stairsPlanksOakPainted.id,
        Block.trapdoorPlanksOakPainted.id, Block.chestPlanksOakPainted.id, Block.doorPlanksOakPaintedBottom.id,
        Block.doorPlanksOakPaintedTop.id, Block.chestLegacyPainted.id
    }));

    @Override
    public boolean onUseItemOnBlock(ItemStack itemstack, EntityPlayer entityplayer, World world, int x, int y, int z, Side side, double xPlaced, double yPlaced) {
        int id = world.getBlockId(x, y, z);

        Block block = Block.getBlock(id);

        if (block instanceof BlockMagicSand) {
            TileEntityMagicSand tileEntity = (TileEntityMagicSand) world.getBlockTileEntity(x, y, z);

            if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                itemstack.getData().putInt("BlockColor", tileEntity.blockColor);
            } else {
                tileEntity.blockColor = itemstack.getData().getInteger("BlockColor");
                for (int a = 0; a < 10; ++a) {
                    spawnRedStarParticle(world, x, y, z);
                }
                world.playSoundEffect(null, SoundCategory.WORLD_SOUNDS, x+.5, y+.5, z+.5, "step.snow", 1f, 1f);
                world.markBlockNeedsUpdate(x, y, z);
                world.notifyBlocksOfNeighborChange(x, y, z, id);
            }

            return true;
        } else {
            BlockColor bc = BlockColorDispatcher.getInstance().getDispatch(block);
            int color = bc.getWorldColor(world, x, y, z);
            if (bc instanceof BlockColorDefault) {
                int meta = world.getBlockMetadata(x, y, z);
                if (blockIds1to1Color.contains(block.id)) {
                    color = colors[meta];
                } else if (blockIds1to16Color.contains(block.id)) {
                    color = colors[meta / 16];
                }
            }
            itemstack.getData().putInt("BlockColor", color);
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

        world.spawnParticle("redstar", i+.5 + x, j+.5 + y, k+.5 + z, 0, 0, 0, 0);
    }
}
