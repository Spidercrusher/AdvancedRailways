package spidercrusher.advancedrailways.carts;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import spidercrusher.advancedrailways.entity.EntityLocomotive;
import spidercrusher.advancedrailways.entity.EntityLocomotiveCreative;
import spidercrusher.advancedrailways.item.ItemARCart;
import spidercrusher.advancedrailways.reference.EntityIDs;
import spidercrusher.advancedrailways.reference.Messages;
import spidercrusher.advancedrailways.reference.Names;
import spidercrusher.advancedrailways.util.Utils;

import java.util.List;

public enum EnumLocomotiveType implements IEnumCartType {
    CREATIVE(EntityIDs.LOCOMOTIVE_CREATIVE, EntityLocomotiveCreative.class, Names.Entities.LOCOMOTIVE_CREATIVE, EnumSpeed.HIGH_SPEED_FAST);
    //DIESEL(EntityIDs.LOCOMOTIVE_DIESEL, EntityLocomotiveDiesel.class, Names.Entities.LOCOMOTIVE_DIESEL, EnumSpeed.FAST),
    //ELECTRIC(EntityIDs.LOCOMOTIVE_ELECTRIC, EntityLocomotiveElectric.class, Names.Entities.LOCOMOTIVE_ELECTRIC, EnumSpeed.FAST),
    //HIGH_SPEED(EntityIDs.LOCOMOTIVE_HIGH_SPEED, EntityLocomotiveHighSpeed.class, Names.Entities.LOCOMOTIVE_HIGH_SPEED, EnumSpeed.HIGH_SPEED_FAST);

    public static final EnumLocomotiveType[] ALL_TYPES = values();

    private final int entityId;
    private final String name;
    private Class<? extends EntityLocomotive> entityLocomotiveClass;
    private final EnumSpeed maxSpeed;
    private List<String> tooltip;
    private final ItemARCart instance;

    EnumLocomotiveType(int entityId, Class<? extends EntityLocomotive> entityLocomotiveClass, String name, EnumSpeed maxSpeed) {
        this.entityId = entityId;
        this.name = name;
        this.entityLocomotiveClass = entityLocomotiveClass;
        this.maxSpeed = maxSpeed;
        this.tooltip = Utils.getLocalizedTooltip(Messages.Tooltip.LOCOMOTIVE);
        this.instance = new ItemARCart(this);
    }

    @Override
    public int getEntityId() {
        return entityId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Class<? extends Entity> getEntityClass() {
        return entityLocomotiveClass;
    }

    public EnumSpeed getMaxSpeed() {
        return maxSpeed;
    }

    @Override
    public List<String> getTooltip() {
        return tooltip;
    }

    @Override
    public ItemARCart getItemInstance() {
        return instance;
    }

    @Override
    public IEnumCartType getTypeFromId(int id) {
        return ALL_TYPES[id];
    }

    @Override
    public EntityLocomotive createCart(World world, BlockPos blockPos) {
        return EntityLocomotive.create(world, blockPos, this);
    }
}
