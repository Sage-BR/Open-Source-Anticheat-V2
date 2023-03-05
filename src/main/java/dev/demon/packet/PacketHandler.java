package dev.demon.packet;


import cc.funkemunky.api.Atlas;
import cc.funkemunky.api.tinyprotocol.listener.functions.PacketListener;
import dev.demon.Anticheat;
import dev.demon.base.user.User;
import org.bukkit.event.EventPriority;

//Too lazy to make an actual packet handler for mutli version support, so we use atlas :) (funke needs to update it ngl)
public class PacketHandler {

    public final PacketListener listener = Atlas.getInstance().getPacketProcessor().process(Anticheat.getInstance(),
            EventPriority.NORMAL, i -> {

                User user = Anticheat.getInstance().getUserManager().getPlayer(i.getPlayer());

                if (user == null) return;

                user.getEventBus().constructPacket(i.getType(), i.getPacket());
            });
}
