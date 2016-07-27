package spidercrusher.advancedrailways.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraftforge.client.model.obj.OBJModel;
import spidercrusher.advancedrailways.client.render.RenderHelper;

import java.util.HashMap;

public class ObjModelBase extends ModelBase {

    private HashMap<String, RenderHelper.ObjGroup> model;

    public ObjModelBase(OBJModel objModel) {
        model = RenderHelper.getVertexListFromObjModel(objModel);
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        RenderHelper.renderObjModel(entityIn.getPositionVector(), model);
    }
}
