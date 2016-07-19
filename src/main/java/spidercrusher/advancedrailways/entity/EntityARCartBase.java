package spidercrusher.advancedrailways.entity;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;

public class EntityARCartBase extends EntityMinecart {

    EntityARCartBase(World worldIn) {
        super(worldIn);
    }

    EntityARCartBase(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    @Override
    public Type getType() {
        return null;
    }
}
