package spidercrusher.advancedrailways.entity;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import spidercrusher.advancedrailways.carts.EnumLocomotiveType;
import spidercrusher.advancedrailways.carts.EnumSpeed;
import spidercrusher.advancedrailways.util.MissingEnumConstantException;

public class EntityLocomotive  extends EntityARCartBase {

    protected EnumLocomotiveType enumLocomotiveType;
    protected EnumSpeed maxSpeed;

    EntityLocomotive(World worldIn, EnumLocomotiveType enumLocomotiveType) {
        super(worldIn);
        this.enumLocomotiveType = enumLocomotiveType;
    }

    EntityLocomotive(World worldIn, BlockPos blockPos, EnumLocomotiveType enumLocomotiveType) {
        super(worldIn, blockPos.getX(), blockPos.getY(), blockPos.getZ());
        this.enumLocomotiveType = enumLocomotiveType;
    }

    @Override
    public double getMaxSpeed() { // TODO!
        return 0;
    }

    public EnumSpeed getEnumMaxSpeed() {
        return maxSpeed;
    }

    public void setCurrentSpeed(int speed) {
        if (speed > maxSpeed.getSpeedMultiplier()) {
            speed = maxSpeed.getSpeedMultiplier();
        }
        // TODO
    }

    public void setCurrentSpeed(EnumSpeed speed) {
        setCurrentSpeed(speed.getSpeedMultiplier());
    }

    public EnumLocomotiveType getLocomotiveType() {
        return enumLocomotiveType;
    }

    public static EntityLocomotive create(World worldIn, BlockPos blockPos, EnumLocomotiveType enumLocomotiveType) {
        switch (enumLocomotiveType) {
            case CREATIVE:
                return new EntityLocomotiveCreative(worldIn, blockPos);

            default:
                throw new MissingEnumConstantException(EnumLocomotiveType.class, enumLocomotiveType.getName());
        }
    }
}
