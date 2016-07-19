package spidercrusher.advancedrailways.entity;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import spidercrusher.advancedrailways.carts.EnumLocomotiveType;

public class EntityLocomotiveCreative extends EntityLocomotive {

    public EntityLocomotiveCreative(World worldIn) {
        super(worldIn, EnumLocomotiveType.CREATIVE);
    }

    public EntityLocomotiveCreative(World worldIn, BlockPos blockPos) {
        super(worldIn, blockPos, EnumLocomotiveType.CREATIVE);
    }
}
