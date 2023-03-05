package dev.demon.base.process.processors;


import dev.demon.Anticheat;
import dev.demon.base.event.PacketEvent;
import dev.demon.base.process.Processor;
import dev.demon.base.process.ProcessorInfo;
import dev.demon.base.user.User;
import dev.demon.util.PacketUtil;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@ProcessorInfo(name = "Potions")
@Getter
@Setter
public class PotionProcessor extends Processor {

    private int speedTicks, slowTicks, jumpTicks;
    private int speedAmp, jumpAmp, slowAmp;
    private boolean speed, jump, slow;

    public PotionProcessor(User data) {
        super(data);
    }

    @Override
    public void onPacket(PacketEvent event) {
        switch (PacketUtil.toPacket(event)) {
            case CLIENT_FLYING:
            case CLIENT_POSITION:
            case CLIENT_LOOK:
            case CLIENT_POSITION_LOOK: {

                boolean speed = false;
                boolean jump = false;
                boolean slow = false;

                int speedAmplifer = 0;
                int jumpAmplifer = 0;
                int slowAmplifer = 0;

                for (PotionEffect potionEffect : Anticheat.getInstance().getNmsManager().getInstance()
                        .potionEffectList(getData())) {

                    PotionEffectType potionEffectType = potionEffect.getType();

                    if (potionEffectType.equals(PotionEffectType.SPEED)) {
                        speedAmplifer = potionEffect.getAmplifier() + 1;
                        speed = true;
                    }

                    if (potionEffectType.equals(PotionEffectType.SLOW)) {
                        slowAmplifer = potionEffect.getAmplifier() + 1;
                        slow = true;
                    }

                    if (potionEffectType.equals(PotionEffectType.JUMP)) {
                        jumpAmplifer = potionEffect.getAmplifier() + 1;
                        jump = true;
                    }
                }

                this.speed = speed;
                this.slow = slow;
                this.jump = jump;

                this.speedAmp = speedAmplifer;
                this.jumpAmp = jumpAmplifer;
                this.slowAmp = slowAmplifer;

                if (speed) {

                    if (speedTicks < 20) {
                        this.speedTicks++;
                    }
                } else {
                    if (speedTicks > 0) {
                        this.speedTicks--;
                    }
                }

                if (jump) {
                    if (this.jumpTicks < 20) {
                        this.jumpTicks++;
                    }
                } else {
                    if (this.jumpTicks > 0) {
                        this.jumpTicks--;
                    }
                }

                if (slow) {
                    if (this.slowTicks < 20) {
                        this.slowTicks++;
                    }
                } else {
                    if (this.slowTicks > 0) {
                        this.slowTicks--;
                    }
                }

                break;
            }
        }
    }
}