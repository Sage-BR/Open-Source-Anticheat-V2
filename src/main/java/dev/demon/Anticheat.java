package dev.demon;

import dev.demon.base.check.api.CheckManager;
import dev.demon.base.user.User;
import dev.demon.base.user.UserManager;
import dev.demon.packet.PacketHandler;
import dev.demon.util.nms.InstanceManager;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
@Setter
public class Anticheat extends JavaPlugin {

    @Getter
    @Setter
    public static Anticheat instance;

    public InstanceManager nmsManager = new InstanceManager();
    public UserManager userManager = new UserManager();

    public PacketHandler handler;

    private CheckManager checkManager;

    @Override
    public void onEnable() {
        instance = this;

        this.nmsManager.create();

        this.handler = new PacketHandler();

        this.checkManager.loadChecks();
    }

    @Override
    public void onDisable() {
        for (User player : this.userManager.getUserMap().values()) {
            player.getCheckManager().getChecks().clear();
            this.userManager.removePlayer(player.getPlayer());
        }

        this.userManager = null;
        this.handler = null;
        this.nmsManager = null;

        instance = null;
    }
}
