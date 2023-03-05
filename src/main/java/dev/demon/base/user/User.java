package dev.demon.base.user;

import dev.demon.base.event.EventBus;
import dev.demon.base.process.ProcessorManager;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.UUID;

@Getter
@Setter
public class User {

    private final Player player;
    private final UUID uuid;

    private EventBus eventBus;

    private ProcessorManager processorManager;

    private UserCheckManager checkManager;

    private boolean alerts = true;

    public User(Player player) {

        this.player = player;
        this.uuid = player.getUniqueId();

        //construct packets
        this.eventBus = new EventBus(this);

        //run processors
        this.processorManager = new ProcessorManager(this);

        //run checks after processors are complete
        this.checkManager = new UserCheckManager();

        this.checkManager.register(this);

    }
}
