package spidercrusher.advancedrailways.client.model;

import net.minecraft.entity.Entity;
import net.minecraftforge.client.model.obj.OBJModel;

public class ModelLocomotive extends ObjModelBase {

    ModelLocomotive(OBJModel objModel) {
        super(objModel);

    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    }
}
