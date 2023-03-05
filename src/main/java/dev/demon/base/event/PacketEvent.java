package dev.demon.base.event;

import dev.demon.util.PacketUtil;
import lombok.Getter;

@Getter
public class PacketEvent {

    private final String packetType;
    private final Object packetObject;
    private final long timestamp = System.currentTimeMillis();
    private final PacketUtil.Packets packetEnum;
    private boolean isFlying;
    private boolean isRotation;
    private boolean isCombat;

    public PacketEvent(String packetType, Object packetObject) {


        this.packetType = packetType;
        this.packetObject = packetObject;
        this.packetEnum = PacketUtil.toPacket(this);

        switch (this.packetEnum) {
            case CLIENT_POSITION_LOOK:
            case CLIENT_LOOK: {
                this.isRotation = true;
                break;
            }
        }


        switch (this.packetEnum) {

            case CLIENT_USE_ENTITY: {
                this.isCombat = true;
                break;
            }

            case CLIENT_LOOK:
            case CLIENT_POSITION_LOOK:
            case CLIENT_POSITION:
            case CLIENT_FLYING: {
                this.isFlying = true;
                break;
            }
        }
    }
}
