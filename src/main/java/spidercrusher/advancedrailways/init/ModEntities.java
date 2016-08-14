package spidercrusher.advancedrailways.init;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import spidercrusher.advancedrailways.AdvancedRailways;
import spidercrusher.advancedrailways.carts.EnumLocomotiveType;
import spidercrusher.advancedrailways.carts.IEnumCartType;
import spidercrusher.advancedrailways.client.render.LocomotiveRenderFactory;
import spidercrusher.advancedrailways.entity.EntityLocomotive;

public class ModEntities {

    public static void init() {
        registerCartTypes(EnumLocomotiveType.ALL_TYPES);
    }

    private static void registerEntity(Class<? extends Entity> entityClass, String entityName, int entityID) {
        EntityRegistry.registerModEntity(entityClass, entityName, entityID, AdvancedRailways.instance, 256, 3, true);
    }

    private static void registerCartTypes(IEnumCartType[] iEnumCartTypes) {
        for (IEnumCartType iEnumCartType : iEnumCartTypes) {
            registerEntity(iEnumCartType.getEntityClass(), iEnumCartType.getName(), iEnumCartType.getEntityId());
        }
    }

    public static void initRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(EntityLocomotive.class, new LocomotiveRenderFactory(EnumLocomotiveType.CREATIVE));
    }
}
