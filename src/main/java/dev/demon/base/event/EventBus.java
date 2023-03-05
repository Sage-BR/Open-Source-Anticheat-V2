package dev.demon.base.event;

import dev.demon.base.user.User;
import lombok.Getter;

@Getter
public class EventBus {
    private final User user;

    public EventBus(User user) {
        this.user = user;
    }

    public void constructPacket(String type, Object packetObject) {
        PacketEvent packetEvent = new PacketEvent(
                type, packetObject
        );

        if (this.user == null) return;

        this.user.getProcessorManager().getProcessors().forEach(check ->
                check.onPacket(packetEvent));

        this.user.getCheckManager().checks.forEach(check -> {
            if (check.isEnabled()) {
                check.onPacket(packetEvent);
            }
        });
        
    }
}