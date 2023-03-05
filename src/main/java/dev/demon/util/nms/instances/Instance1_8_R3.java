package dev.demon.util.nms.instances;

import dev.demon.base.user.User;
import dev.demon.util.box.BoundingBox;
import dev.demon.util.nms.Instance;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.util.CraftMagicNumbers;
import org.bukkit.entity.Entity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("rawtypes")
public class Instance1_8_R3 extends Instance {

    @Override
    public Material getType(World world, double x, double y, double z) {
        BlockPosition blockPosition = new BlockPosition(x, y, z);
        return CraftMagicNumbers.getMaterial(((CraftWorld) world).getHandle().getType(blockPosition).getBlock());
    }

    @Override
    public List<PotionEffect> potionEffectList(User user) {
        List<PotionEffect> effects = new ArrayList();

        for (Object obj : ((CraftPlayer) user.getPlayer()).getHandle().effects.values()) {
            if (obj instanceof MobEffect) {
                MobEffect handle = (MobEffect) obj;
                effects.add(new PotionEffect(PotionEffectType.getById(handle.getEffectId()), handle.getDuration(),
                        handle.getAmplifier(), handle.isAmbient(), handle.isShowParticles()));
            }
        }

        return effects;
    }

    @Override
    public float getSlipperiness(Location location) {
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();

        BlockPosition blockPos = new BlockPosition(x, y, z);
        Block nmsBlock = MinecraftServer.getServer().getWorld().getType(blockPos).getBlock();

        return nmsBlock.frictionFactor;
    }


    @Override
    public void sendBlockUpdate(User user, double x, double y, double z) {
        PacketPlayOutBlockChange packetPlayOutBlockChange =
                new PacketPlayOutBlockChange((((CraftWorld) user.getPlayer().getWorld()).getHandle()),
                        new BlockPosition(x, y, z));
        ((CraftPlayer) user.getPlayer()).getHandle().playerConnection.sendPacket(packetPlayOutBlockChange);
    }

    @Override
    public BoundingBox getEntityBoundingBox(Entity entity) {
        AxisAlignedBB aabb = ((CraftEntity) entity).getHandle().getBoundingBox();

        return new BoundingBox(
                (float) aabb.a, (float) aabb.b, (float) aabb.c, (float) aabb.d, (float) aabb.e, (float) aabb.f
        );
    }

    @Override
    public boolean getEntityBoundingBoxGround(Entity entity) {

        CraftEntity entityPlayer = ((CraftEntity) entity);

        AxisAlignedBB aabb = ((CraftEntity) entity).getHandle().getBoundingBox();

        AxisAlignedBB axisAlignedBB = aabb
                .grow(0.0625, 0.0625, 0.0625)
                .a(0.0, -0.55, 0.0);

        return entityPlayer.getHandle().world.c(axisAlignedBB);
    }
}
