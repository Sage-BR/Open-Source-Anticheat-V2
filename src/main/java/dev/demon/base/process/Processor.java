package dev.demon.base.process;

import dev.demon.Anticheat;
import dev.demon.base.event.Event;
import dev.demon.base.user.User;
import lombok.Getter;
import org.bukkit.Material;

@Getter
public class Processor extends Event {

    private final String name;

    private final User data;

    public Processor(final User data) {
        this.name = getClass().getAnnotation(ProcessorInfo.class).name();
        this.data = data;
    }

    public Material getMaterial(User user, double x, double y, double z) {
        return Anticheat.getInstance().getNmsManager().getInstance().getType(
                user.getPlayer().getWorld(), x, y, z);
    }
}
