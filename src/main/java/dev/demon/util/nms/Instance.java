package dev.demon.util.nms;

import dev.demon.base.user.User;
import dev.demon.util.box.BoundingBox;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.potion.PotionEffect;

import java.util.List;

public abstract class Instance {

    public abstract Material getType(World world, double x, double y, double z);

    public abstract void sendBlockUpdate(User user, double x, double y, double z);

    public abstract List<PotionEffect> potionEffectList(User user);

    public abstract BoundingBox getEntityBoundingBox(Entity entity);

    public abstract boolean getEntityBoundingBoxGround(Entity entity);

    public abstract float getSlipperiness(Location location);

}
