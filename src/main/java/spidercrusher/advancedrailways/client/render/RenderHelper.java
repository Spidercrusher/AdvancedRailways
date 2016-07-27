package spidercrusher.advancedrailways.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.client.model.obj.OBJModel;
import org.lwjgl.opengl.GL11;
import spidercrusher.advancedrailways.util.LogHelper;

import javax.vecmath.Vector4f;
import java.util.*;

public class RenderHelper { // TODO change the vertexList to an immutable LinkedList

    /**
     * tries to load an objModel
     *
     * @param modelResourceLocation resourceLocation of the model to be loaded
     * @return the objModel if loaded successful, null if loading failed
     */

    public static OBJModel loadObjModel(ResourceLocation modelResourceLocation) {
        OBJModel objModel = null;
        try {
            objModel = (OBJModel) OBJLoader.INSTANCE.loadModel(modelResourceLocation);
        } catch (Exception e) {
            LogHelper.error("Could not find the objModel for resourceLocation %s\n%s", modelResourceLocation.toString(), e.getLocalizedMessage()); // TODO!!!!!
        }

        return objModel;
    }

    public static HashMap<String, ObjGroup> getDefaultModel() {
        List<OBJModel.Vertex> vertexList = new LinkedList<OBJModel.Vertex>();

        vertexList.add(addVertex(0.8F, 0.0F, 0.0F, 1.0F, 0.0F));
        vertexList.add(addVertex(0.8F, 0.8F, 0.0F, 1.0F, 1.0F));
        vertexList.add(addVertex(0.0F, 0.8F, 0.0F, 0.0F, 1.0F));
        vertexList.add(addVertex(0.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        vertexList.add(addVertex(0.8F, 0.0F, -0.8F, 1.0F, 0.0F));
        vertexList.add(addVertex(0.8F, 0.8F, -0.8F, 1.0F, 1.0F));
        vertexList.add(addVertex(0.8F, 0.8F, 0.0F, 0.0F, 1.0F));
        vertexList.add(addVertex(0.8F, 0.0F, 0.0F, 0.0F, 0.0F));

        vertexList.add(addVertex(0.0F, 0.0F, -0.8F, 1.0F, 0.0F));
        vertexList.add(addVertex(0.0F, 0.8F, -0.8F, 1.0F, 1.0F));
        vertexList.add(addVertex(0.8F, 0.8F, -0.8F, 0.0F, 1.0F));
        vertexList.add(addVertex(0.8F, 0.0F, -0.8F, 0.0F, 0.0F));

        vertexList.add(addVertex(0.0F, 0.0F, 0.0F, 1.0F, 0.0F));
        vertexList.add(addVertex(0.0F, 0.8F, 0.0F, 1.0F, 1.0F));
        vertexList.add(addVertex(0.0F, 0.8F, -0.8F, 0.0F, 1.0F));
        vertexList.add(addVertex(0.0F, 0.0F, -0.8F, 0.0F, 0.0F));

        vertexList.add(addVertex(0.8F, 0.0F, -0.8F, 1.0F, 0.0F));
        vertexList.add(addVertex(0.8F, 0.0F, 0.0F, 1.0F, 1.0F));
        vertexList.add(addVertex(0.0F, 0.0F, 0.0F, 0.0F, 1.0F));
        vertexList.add(addVertex(0.0F, 0.0F, -0.8F, 0.0F, 0.0F));

        vertexList.add(addVertex(0.8F, 0.8F, 0.0F, 1.0F, 0.0F));
        vertexList.add(addVertex(0.8F, 0.8F, -0.8F, 1.0F, 1.0F));
        vertexList.add(addVertex(0.0F, 0.8F, -0.8F, 0.0F, 1.0F));
        vertexList.add(addVertex(0.0F, 0.8F, 0.0F, 0.0F, 0.0F));

        HashMap<String, ObjGroup> objGroupHashMap = new HashMap<String, ObjGroup>();
        objGroupHashMap.put("default", new ObjGroup("default", (LinkedList<OBJModel.Vertex>) vertexList, TextureMap.LOCATION_MISSING_TEXTURE));
        return objGroupHashMap;
    }

    private static OBJModel.Vertex addVertex(float x, float y, float z, float u, float v) {
        OBJModel.Vertex vertex = new OBJModel.Vertex(new Vector4f(x, y, z, 0), null);
        vertex.setNormal(new OBJModel.Normal());
        vertex.setTextureCoordinate(new OBJModel.TextureCoordinate(u, v, 0));
        return vertex;
    }

    /**
     * converts the objModel into a format for easier use with the Tessellator. If the given model is null, a default model will be returned.
     *
     * @param objModel the objModel to convert
     * @return HashMap containing the objGroups of the model, with group name as key.
     */

    public static HashMap<String, ObjGroup> getVertexListFromObjModel(OBJModel objModel) {
        HashMap<String, ObjGroup> objGroupHashMap = new HashMap<String, ObjGroup>();

        if (objModel == null) {
            return getDefaultModel();
        }

        for (Map.Entry<String, OBJModel.Group> groupEntry : objModel.getMatLib().getGroups().entrySet()) {
            List<OBJModel.Vertex> vertexList = new LinkedList<OBJModel.Vertex>();
            ResourceLocation textureResourceLocation = null;

            for (OBJModel.Face face : groupEntry.getValue().getFaces()) {
                if (textureResourceLocation == null) {
                    textureResourceLocation = objModel.getMatLib().getMaterial(face.getMaterialName()).getTexture().getTextureLocation();
                }
                Collections.addAll(vertexList, face.getVertices());
            }

            objGroupHashMap.put(groupEntry.getKey(), new ObjGroup(groupEntry.getKey(), (LinkedList<OBJModel.Vertex>) vertexList, textureResourceLocation));
        }

        return objGroupHashMap;
    }

    public static void renderObjModel(Vec3d renderPos, HashMap<String, ObjGroup> objGroupHashMap) {
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer vertexBuffer = tessellator.getBuffer();

        for (ObjGroup objGroup : objGroupHashMap.values()) {
            if (!objGroup.getShouldRender()) break; // Don't render disabled groups

            Minecraft.getMinecraft().getTextureManager().bindTexture(objGroup.getTextureResourceLocation());

            vertexBuffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_NORMAL);

            for (OBJModel.Vertex vertex : objGroup.getVertexList()) {
                addObjVertexToVertexBuffer(vertexBuffer, renderPos, vertex);
            }

            tessellator.draw();
        }
    }

    public static void addObjVertexToVertexBuffer(VertexBuffer vertexBuffer, Vec3d renderPos, OBJModel.Vertex vertex) {
        vertexBuffer.pos(vertex.getPos().getX() + renderPos.xCoord, vertex.getPos().getY() + renderPos.yCoord, vertex.getPos().getZ() + renderPos.zCoord)
                .tex(vertex.getTextureCoordinate().u, vertex.getTextureCoordinate().v)
                .normal(vertex.getNormal().x, vertex.getNormal().y, vertex.getNormal().z)
                .endVertex();
    }

    public static class ObjGroup {

        private final String groupName;
        private final LinkedList<OBJModel.Vertex> vertexList;
        private final ResourceLocation defaultTextureResourceLocation;
        private ResourceLocation textureResourceLocation;
        private boolean shouldRender = true;

        public ObjGroup(String groupName, LinkedList<OBJModel.Vertex> vertexList, ResourceLocation textureResourceLocation) {
            this.groupName = groupName;
            this.vertexList = vertexList;
            this.defaultTextureResourceLocation = this.textureResourceLocation = textureResourceLocation;
        }

        public String getGroupName() {
            return groupName;
        }

        public List<OBJModel.Vertex> getVertexList() {
            return vertexList;
        }

        public ResourceLocation getDefaultTextureResourceLocation() {
            return defaultTextureResourceLocation;
        }

        public ResourceLocation getTextureResourceLocation() {
            return textureResourceLocation == null ? defaultTextureResourceLocation : textureResourceLocation;
        }

        public void setTextureResourceLocation(ResourceLocation textureResourceLocation) {
            this.textureResourceLocation = textureResourceLocation;
        }

        public void setShouldRender(boolean shouldRender) {
            this.shouldRender = shouldRender;
        }

        public boolean getShouldRender() {
            return shouldRender;
        }
    }
}
