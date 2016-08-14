package spidercrusher.advancedrailways.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLocomotive extends ModelBase {

    protected ModelRenderer model;

    ModelLocomotive(String modelName) {
        model = new ModelRenderer(this, modelName);
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        model.render(scale);
    }
}
