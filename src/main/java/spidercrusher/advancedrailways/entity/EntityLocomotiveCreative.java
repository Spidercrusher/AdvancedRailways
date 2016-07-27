package spidercrusher.advancedrailways.entity;

import net.minecraft.world.World;
import spidercrusher.advancedrailways.carts.EnumLocomotiveType;

public class EntityLocomotiveCreative extends EntityLocomotive {

    public EntityLocomotiveCreative(World worldIn) {
        super(worldIn, EnumLocomotiveType.CREATIVE);
    }

    public EntityLocomotiveCreative(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z, EnumLocomotiveType.CREATIVE);
    }
}
