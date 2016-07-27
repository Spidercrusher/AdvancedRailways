package spidercrusher.advancedrailways.entity;

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

    EntityLocomotive(World worldIn, double x, double y, double z, EnumLocomotiveType enumLocomotiveType) {
        super(worldIn, x, y, z);
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

    public static EntityLocomotive create(World worldIn, double x, double y, double z, EnumLocomotiveType enumLocomotiveType) {
        switch (enumLocomotiveType) {
            case CREATIVE:
                return new EntityLocomotiveCreative(worldIn, x, y, z);

            default:
                throw new MissingEnumConstantException(EnumLocomotiveType.class, enumLocomotiveType.getName());
        }
    }
}
