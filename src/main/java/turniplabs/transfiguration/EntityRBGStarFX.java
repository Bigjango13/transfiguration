package turniplabs.transfiguration;

import net.minecraft.client.entity.fx.EntityFX;
import net.minecraft.client.render.stitcher.TextureRegistry;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.core.world.World;

public class EntityRBGStarFX extends EntityFX {
    String texture = "";
    public EntityRBGStarFX(World world, double x, double y, double z, double motionX, double motionY, double motionZ, String texture) {
        super(world, x, y, z, motionX, motionY, motionZ);
        this.particleMaxAge = 12;
        this.particleScale = 2;
        this.xd = (Math.random() * 2.0D - 1.0D) * 0.01F;
        this.yd = (Math.random() * 2.0D - 1.0D) * 0.01F;
        this.zd = (Math.random() * 2.0D - 1.0D) * 0.01F;
        this.texture = texture;
        this.particleTexture = TextureRegistry.getTexture(texture + "_0");
    }

    @Override
    public void renderParticle(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
        int x = 0;
        if (particleAge >= 3) {
            x = 1;
        }
        if (particleAge >= 6) {
            x = 2;
        }
        this.particleTexture = TextureRegistry.getTexture(texture + "_" + x);
        super.renderParticle(tessellator, f, f1, f2, f3, f4, f5);
    }

    @Override
    public float getBrightness(float f) {
        return 1f;
    }
}
