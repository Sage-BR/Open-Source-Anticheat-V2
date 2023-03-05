package dev.demon.base.process.processors;

import cc.funkemunky.api.tinyprotocol.packet.out.WrappedOutAbilitiesPacket;
import dev.demon.base.event.PacketEvent;
import dev.demon.base.process.Processor;
import dev.demon.base.process.ProcessorInfo;
import dev.demon.base.user.User;
import dev.demon.util.PacketUtil;
import lombok.Getter;
import lombok.Setter;

@ProcessorInfo(name = "Abilities")
@Getter
@Setter
public class AbilitiesProcessor extends Processor {

    private boolean invulnerable, flying, allowedFlight;

    private float walkSpeed, flySpeed;

    public AbilitiesProcessor(User data) {
        super(data);
    }

    @Override
    public void onPacket(PacketEvent event) {
        if (event.getPacketEnum() == PacketUtil.Packets.SERVER_ABILITES) {
            WrappedOutAbilitiesPacket wrapper = new WrappedOutAbilitiesPacket(event.getPacketObject(), getData().getPlayer());

            this.invulnerable = wrapper.isInvulnerable();
            this.flying = wrapper.isFlying();
            this.allowedFlight = wrapper.isAllowedFlight();

            this.walkSpeed = wrapper.getWalkSpeed();
            this.flySpeed = wrapper.getFlySpeed();

        }
    }
}
