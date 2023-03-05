package dev.demon.base.process.processors;

import cc.funkemunky.api.tinyprotocol.packet.in.WrappedInFlyingPacket;
import dev.demon.base.event.PacketEvent;
import dev.demon.base.process.Processor;
import dev.demon.base.process.ProcessorInfo;
import dev.demon.base.user.User;
import dev.demon.util.PacketUtil;
import dev.demon.util.location.CustomLocation;
import dev.demon.util.math.MathUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ProcessorInfo(
        name = "Movement"
)
public class MovementProcessor extends Processor {

    private double deltaY, deltaYAbs, lastDeltaX, lastDeltaY, lastDeltaZ, deltaXZ,
            lastDeltaXZ, deltaX, deltaZ, deltaXAbs, deltaZAbs;

    private double deltaYaw, deltaPitch, lastDeltaYaw, lastDeltaPitch,
            deltaYawAbs, deltaPitchAbs, lastDeltaPitchAbs, lastDeltaYawAbs;

    private double yawDeltaClamped, yawAccel, pitchAccel;

    private CustomLocation to = new CustomLocation();
    private CustomLocation from = new CustomLocation();
    private CustomLocation fromFrom = new CustomLocation();


    public MovementProcessor(User data) {
        super(data);
    }

    @Override
    public void onPacket(PacketEvent event) {
        switch (PacketUtil.toPacket(event)) {
            case CLIENT_FLYING:
            case CLIENT_POSITION:
            case CLIENT_LOOK:
            case CLIENT_POSITION_LOOK: {

                WrappedInFlyingPacket packet = new WrappedInFlyingPacket(event.getPacketObject(), getData().getPlayer());

                double x = packet.getX();
                double y = packet.getY();
                double z = packet.getZ();

                float pitch = packet.getPitch();
                float yaw = packet.getYaw();

                boolean ground = packet.isGround();

                this.fromFrom.setWorld(this.from.getWorld());
                this.from.setWorld(to.getWorld());
                this.to.setWorld(getData().getPlayer().getWorld());

                this.fromFrom.setOnGround(this.from.isOnGround());
                this.from.setOnGround(this.to.isOnGround());
                this.to.setOnGround(ground);

                if (packet.isPos()) {
                    this.fromFrom.setPosX(this.from.getPosX());
                    this.fromFrom.setPosY(this.from.getPosY());
                    this.fromFrom.setPosZ(this.from.getPosZ());

                    this.from.setPosX(this.to.getPosX());
                    this.from.setPosY(this.to.getPosY());
                    this.from.setPosZ(this.to.getPosZ());

                    this.to.setPosX(x);
                    this.to.setPosY(y);
                    this.to.setPosZ(z);

                    this.lastDeltaX = this.deltaX;
                    this.lastDeltaY = this.deltaY;
                    this.lastDeltaZ = this.deltaZ;

                    this.deltaY = this.to.getPosY() - this.from.getPosY();
                    this.deltaX = this.to.getPosX() - this.from.getPosX();
                    this.deltaZ = this.to.getPosZ() - this.from.getPosZ();

                    this.deltaXAbs = Math.abs(this.deltaX);
                    this.deltaZAbs = Math.abs(this.deltaZ);
                    this.deltaYAbs = Math.abs(this.deltaY);

                    this.lastDeltaXZ = this.deltaXZ;

                    this.deltaXZ = Math.hypot(this.deltaXAbs, this.deltaZAbs);
                }

                if (packet.isLook()) {
                    this.fromFrom.setYaw(this.from.getYaw());
                    this.fromFrom.setPitch(this.from.getPitch());

                    this.from.setYaw(this.to.getYaw());
                    this.from.setPitch(this.to.getPitch());

                    this.to.setPitch(pitch);
                    this.to.setYaw(yaw);

                    this.lastDeltaYaw = this.deltaYaw;
                    this.lastDeltaPitch = this.deltaPitch;

                    this.deltaYaw = this.to.getYaw() - this.from.getYaw();
                    this.deltaPitch = this.to.getPitch() - this.from.getPitch();

                    this.lastDeltaYawAbs = this.deltaYawAbs;
                    this.lastDeltaPitchAbs = this.deltaPitchAbs;

                    this.deltaYawAbs = Math.abs(this.to.getYaw() - this.from.getYaw());
                    this.deltaPitchAbs = Math.abs(this.to.getPitch() - this.from.getPitch());

                    this.yawAccel = Math.abs(this.deltaYawAbs - this.lastDeltaYawAbs);
                    this.pitchAccel = Math.abs(this.deltaPitchAbs - this.lastDeltaPitchAbs);

                    this.yawDeltaClamped = MathUtil.wrapAngleTo180_float((float) this.deltaYaw);
                }



                break;
            }
        }
    }
}
