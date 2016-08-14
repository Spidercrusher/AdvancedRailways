package spidercrusher.advancedrailways.client.model;

public class ModelLocomotiveCreative extends ModelLocomotive {

    public ModelLocomotiveCreative(String modelName) {
        super(modelName);

        this.model.setTextureSize(64, 64);

        setTextureOffset(modelName + ".base", 0, 0);
        setTextureOffset(modelName + ".cabin", 0, 0);
        setTextureOffset(modelName + ".frontBottom", 0, 0);
        setTextureOffset(modelName + ".backBottom", 0, 0);
        setTextureOffset(modelName + ".FrontTop", 0, 0);
        setTextureOffset(modelName + ".backTop", 0, 0);

        this.model.setRotationPoint(0.0F, 4.0F, 0.0F);

        model.addBox("base", -10.0F, -3.0F, -8.0F, 20, 4, 16);
        model.addBox("cabin", -5.5F, -15.0F, -8.0F, 6, 12, 16);
        model.addBox("frontBottom", 0.5F, -11.0F, -7.0F, 9, 8, 14);
        model.addBox("backBottom", -9.5F, -11.0F, -7.0F, 4, 8, 14);
        model.addBox("FrontTop", 0.5F, -14.0F, -2.5F, 9, 3, 5);
        model.addBox("backTop", -9.5F, -14.0F, -2.5F, 4, 3, 5);
    }
}
