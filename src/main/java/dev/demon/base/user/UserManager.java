package dev.demon.base.user;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
public class UserManager {

    private final Map<UUID, User> userMap = new ConcurrentHashMap<>();

    public void addPlayer(Player player) {
        final UUID uuid = player.getUniqueId();

        if (!this.userMap.containsKey(uuid)) {
            User data = new User(player);
            this.userMap.put(uuid, data);
        }
    }

    public void removePlayer(Player player) {
        UUID uuid = player.getUniqueId();
        this.userMap.remove(uuid);
    }

    public User getPlayer(Player player) {
        return this.userMap.get(player.getUniqueId());
    }
}
