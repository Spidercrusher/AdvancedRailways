package spidercrusher.advancedrailways.carts;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import spidercrusher.advancedrailways.entity.EntityARCartBase;
import spidercrusher.advancedrailways.entity.EntityLocomotive;
import spidercrusher.advancedrailways.reference.Reference;

public class CartHelper {

    public static EntityARCartBase createCart(World world, double x, double y, double z, IEnumCartType iEnumCartType) {
        if (iEnumCartType instanceof EnumLocomotiveType) {
            return EntityLocomotive.create(world, x, y, z, (EnumLocomotiveType) iEnumCartType);

        } else {
            throw new IllegalArgumentException("Cannot create " + Reference.MOD_NAME + " cart for iEnumCartType " + iEnumCartType + "!");
        }
    }
}
