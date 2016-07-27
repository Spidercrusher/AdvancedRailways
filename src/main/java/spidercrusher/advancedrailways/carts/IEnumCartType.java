package spidercrusher.advancedrailways.carts;

import net.minecraft.entity.Entity;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import spidercrusher.advancedrailways.item.ItemARCart;

import java.util.List;

public interface IEnumCartType extends IStringSerializable {

    int getEntityId();

    Class<? extends Entity> getEntityClass();

    IEnumCartType getTypeFromId(int id);

    Entity createCart(World world, double x, double y, double z);

    List<String> getTooltip();

    ItemARCart getItemInstance();
}
