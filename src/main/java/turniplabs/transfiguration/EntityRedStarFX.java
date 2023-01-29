package turniplabs.transfiguration;

import net.minecraft.src.EntityFX;
import net.minecraft.src.Tessellator;
import net.minecraft.src.World;
import org.lwjgl.opengl.GL11;

public class EntityRedStarFX extends EntityFX {
    public EntityRedStarFX(World world, double x, double y, double z, double motionX, double motionY, double motionZ) {
        super(world, x, y, z, motionX, motionY, motionZ);
        this.particleMaxAge = 12;
        this.particleScale = 2;
        this.motionX = (Math.random() * 2.0D - 1.0D) * 0.01F;
        this.motionY = (Math.random() * 2.0D - 1.0D) * 0.01F;
        this.motionZ = (Math.random() * 2.0D - 1.0D) * 0.01F;
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
        int y = 9;
        this.particleTextureIndex = y * 16 + x * 2;

        float pointX = (float)(this.particleTextureIndex % 16) / 16.0F;
        float pointX2 = pointX + 0.0624375F * 2;
        float pointY = (float)(this.particleTextureIndex / 16) / 16.0F;
        float pointY2 = pointY + 0.0624375F * 2;
        float scale = 0.1F * this.particleScale;
        float posX = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)f - interpPosX);
        float posY = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)f - interpPosY);
        float posZ = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)f - interpPosZ);
        float opacity = 1f;
        if (particleAge >= 9) {
            opacity = (float)(particleMaxAge - particleAge) / 3f;
        }

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        tessellator.setColorRGBA_F(this.particleRed, this.particleGreen, this.particleBlue, opacity);
        tessellator.addVertexWithUV(posX - f1 * scale - f4 * scale, posY - f2 * scale, posZ - f3 * scale - f5 * scale, pointX2, pointY2);
        tessellator.addVertexWithUV(posX - f1 * scale + f4 * scale, posY + f2 * scale, posZ - f3 * scale + f5 * scale, pointX2, pointY);
        tessellator.addVertexWithUV(posX + f1 * scale + f4 * scale, posY + f2 * scale, posZ + f3 * scale + f5 * scale, pointX, pointY);
        tessellator.addVertexWithUV(posX + f1 * scale - f4 * scale, posY - f2 * scale, posZ + f3 * scale - f5 * scale, pointX, pointY2);
    }

}
