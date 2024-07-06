package turniplabs.transfiguration;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.sound.BlockSounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.*;
import turniplabs.halplibe.helper.recipeBuilders.RecipeBuilderShapeless;
import turniplabs.halplibe.helper.recipeBuilders.RecipeBuilderShaped;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;


public class Transfiguration implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint {
    public static final String MOD_ID = "transfiguration";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static Block magicSand;
    public static Item transfigurationWand;
    public static Item colorWand;
    public static Item buildersWand;

    @Override
    public void onInitialize() {
        LOGGER.info("Transfiguration initialized.");
    }

    @Override
    public void beforeGameStart() {
        int baseId = 22657;
        int sandId = 6273;
        magicSand = new BlockBuilder(MOD_ID)
            .setBlockModel(block -> new BlockModelMagicSand<>(block)
                .withTextures("transfiguration:block/magic_sand")
            )
            .setBlockColor(block -> new BlockColorMagicSand())
            .setBlockSound(BlockSounds.SAND)
            .build(new BlockMagicSand("magicsand", sandId, Material.sand));
        transfigurationWand = new ItemBuilder(MOD_ID)
            .setIcon("transfiguration:item/transfiguration_wand")
            .build(new ItemTransfigurationWand("transfigurationwand", baseId++));
        colorWand = new ItemBuilder(MOD_ID)
            .setIcon("transfiguration:item/color_wand")
            .build(new ItemColorWand("colorwand", baseId++));
        buildersWand = new ItemBuilder(MOD_ID)
            .setIcon("transfiguration:item/builders_wand")
            .build(new ItemBuildersWand("builderswand", baseId++));


        EntityHelper.createTileEntity(TileEntityMagicSand.class, "MagicSand");
        ParticleHelper.createParticle("goldenstar", (world, x, y, z, motionX, motionY, motionZ, data) -> new EntityRBGStarFX(world, x, y, z, motionX, motionY, motionZ, "transfiguration:particle/goldenstar"));
        ParticleHelper.createParticle("bluestar", (world, x, y, z, motionX, motionY, motionZ, data) -> new EntityRBGStarFX(world, x, y, z, motionX, motionY, motionZ, "transfiguration:particle/bluestar"));
        ParticleHelper.createParticle("redstar", (world, x, y, z, motionX, motionY, motionZ, data) -> new EntityRBGStarFX(world, x, y, z, motionX, motionY, motionZ, "transfiguration:particle/redstar"));
    }

    @Override
    public void afterGameStart() {
    }

    @Override
    public void onRecipesReady() {
        new RecipeBuilderShaped(MOD_ID, " N ", "NGN", "SN ")
            .addInput('G', Item.dustGlowstone)
            .addInput('N', Item.ingotGold)
            .addInput('S', Item.stick)
            .create("transfigurationWand", new ItemStack(transfigurationWand, 1));
        new RecipeBuilderShaped(MOD_ID, " R ", "GDO", "SL ")
            .addInput('R', Item.dustRedstone)
            .addInput('G', Item.dustGlowstone)
            .addInput('D', Item.diamond)
            .addInput('O', Item.olivine)
            .addInput('L', new ItemStack(Item.dye, 1, 4))
            .addInput('S', Item.stick)
            .create("colorWand", new ItemStack(colorWand, 1));
        new RecipeBuilderShapeless(MOD_ID)
            .addInput(Block.sand)
            .addInput(Item.dustGlowstone)
            .create("magicSand", new ItemStack(magicSand, 1));
    }

    @Override
    public void initNamespaces() {
    }
}
