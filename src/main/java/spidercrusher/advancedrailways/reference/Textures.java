package spidercrusher.advancedrailways.reference;

import net.minecraft.util.ResourceLocation;

public class Textures {

    private static final String IMAGE_EXTENSION = ".png";

    public static final class Entity {
        public static final String BASE_PATH = "textures/entity/";
    }

    public static ResourceLocation getTextureResourceLocation(String texturePath, String textureName) {
        String resourceLocation = texturePath + (texturePath.endsWith("/") ? "" : "/") + textureName + IMAGE_EXTENSION;
        return new ResourceLocation(Reference.MOD_ID, resourceLocation);
    }
}
