package turniplabs.transfiguration;

import net.fabricmc.api.ModInitializer;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.*;
import turniplabs.halplibe.mixin.accessors.BlockAccessor;


public class Transfiguration implements ModInitializer {
    public static final String MOD_ID = "transfiguration";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final Block magicSand = BlockHelper.createBlock(MOD_ID, new BlockMagicSand(2000, Material.sand), "magicsand", "magic_sand.png", Block.soundSandFootstep, 2f, 0f, 0f);
    static {
        ((BlockAccessor) magicSand).callSetLightOpacity(magicSand.blockID);
    }
    public static final Item transfigurationWand = ItemHelper.createItem(MOD_ID, new ItemTransfigurationWand(1500), "transfigurationwand", "transfiguration_wand.png");
    public static final Item colorWand = ItemHelper.createItem(MOD_ID, new ItemColorWand(1501), "colorwand", "color_wand.png");

    @Override
    public void onInitialize() {
        LOGGER.info("Transfiguration initialized.");
        EntityHelper.createTileEntity(TileEntityMagicSand.class, "Magic");
        ParticleHelper.createParticle(EntityGoldenStarFX.class, "goldenstar");
        ParticleHelper.createParticle(EntityRedStarFX.class, "redstar");

        RecipeHelper.Crafting.createRecipe(transfigurationWand, 1, new Object[]{"#N#", "NGN", "SN#", 'G', Item.dustGlowstone, 'N', Item.nuggetGold, 'S', Item.stick});
        RecipeHelper.Crafting.createRecipe(colorWand, 1, new Object[]{"#R#", "GDO", "SL#", 'R', Item.dustRedstone, 'G', Item.dustGlowstone, 'D', Item.diamond, 'O', Item.olivine, 'L', new ItemStack(Item.dye, 1, 4), 'S', Item.stick});
        RecipeHelper.Crafting.createShapelessRecipe(magicSand, 1, new Object[]{Block.sand, Item.dustGlowstone});
    }
}
