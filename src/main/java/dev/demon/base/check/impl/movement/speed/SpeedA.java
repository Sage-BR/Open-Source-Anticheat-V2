package dev.demon.base.check.impl.movement.speed;

import cc.funkemunky.api.tinyprotocol.packet.in.WrappedInFlyingPacket;
import cc.funkemunky.api.utils.MathHelper;
import dev.demon.base.check.api.Check;
import dev.demon.base.check.api.CheckType;
import dev.demon.base.check.api.Data;
import dev.demon.base.event.PacketEvent;
import dev.demon.base.user.User;
import dev.demon.util.location.CustomLocation;

@Data(name = "Speed",
        checkType = CheckType.MOVEMENT,
        description = "Half ass reversed mc code, checks if the player is following the proper friction and acceleration")

public class SpeedA extends Check {

    private double threshold;
    private double MAX_EXPECTED = 1e-4;

    public SpeedA(User user) {
        super(user);
    }

    @Override
    public void onPacket(PacketEvent event) {
        if (event.isFlying()) {

            WrappedInFlyingPacket packet = new WrappedInFlyingPacket(event.getPacketObject(), getUser().getPlayer());

            if (!packet.isPos()) return;

            CustomLocation to = getUser().getProcessorManager().getMovementProcessor().getTo(),
                    from = getUser().getProcessorManager().getMovementProcessor().getFrom();


            boolean ground = to.isOnGround();
            boolean lastGround = from.isOnGround();

            double deltaXZ = getUser().getProcessorManager().getMovementProcessor().getDeltaXZ();

            double lastDeltaXZ = getUser().getProcessorManager().getMovementProcessor().getLastDeltaXZ();

            double prediction = getUser().getProcessorManager().getMovementProcessor().getFromFrom().isOnGround() ?
                    lastDeltaXZ * (0.91F * 0.6F) : lastDeltaXZ * 0.91F;

            if (!ground && lastGround) {
                prediction += 0.2F;
            }

            float strafe = 1F, forward = 1F;
            float f = strafe * strafe + forward * forward;

            float friction;

            float var3 = (0.6F * 0.91F);

            float attributeSpeed;

            attributeSpeed = getUser().getProcessorManager()
                    .getAbilitiesProcessor().getWalkSpeed();


            if (getUser().getProcessorManager().getPotionProcessor().isSpeed()) {
                attributeSpeed += getUser().getProcessorManager().getPotionProcessor()
                        .getSpeedAmp() * 0.2D * attributeSpeed;
            }

            if (getUser().getProcessorManager().getPotionProcessor().isSlow()) {
                attributeSpeed += getUser().getProcessorManager().getPotionProcessor()
                        .getSlowAmp() * -.15D * attributeSpeed;
            }


            float moveSpeed = (float) attributeSpeed;

            moveSpeed += moveSpeed * 0.30000001192092896D;

            float getAIMoveSpeed = moveSpeed;

            float var4 = 0.16277136F / (var3 * var3 * var3);

            if (from.isOnGround()) {
                friction = getAIMoveSpeed * var4;
            } else {
                friction = 0.026F;
            }

            if (f >= 1.0E-4F) {
                f = (float) Math.sqrt(f);
                if (f < 1.0F) {
                    f = 1.0F;
                }
                f = friction / f;
                strafe = strafe * f;
                forward = forward * f;
                float f1 = (float) MathHelper.sin(to.getYaw() * (float) Math.PI / 180.0F);
                float f2 = (float) MathHelper.cos(to.getYaw() * (float) Math.PI / 180.0F);
                float motionXAdd = (strafe * f2 - forward * f1);
                float motionZAdd = (forward * f2 + strafe * f1);

                prediction = Math.hypot(motionXAdd, motionZAdd);
            }

            double total = deltaXZ - prediction;

            //todo: actually fix this lol (can false if you sneak on and off quickly when jumping/falling onto, or off a block)
            if (getUser().getPlayer().isSneaking()) {
                this.MAX_EXPECTED = 0.1;
            }

            if (deltaXZ > 0.199 && total > MAX_EXPECTED) {
                if (++this.threshold > 9) {
                    this.fail("Player is not following game friction",
                            "deltaXZ=" + deltaXZ,
                            "total=" + total,
                            "expected=" + this.MAX_EXPECTED);
                }
            } else {
                this.threshold -= Math.min(this.threshold, .03);
            }
        }
    }
}
