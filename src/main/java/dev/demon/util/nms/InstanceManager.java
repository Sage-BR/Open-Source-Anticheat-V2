package dev.demon.util.nms;

import dev.demon.util.nms.instances.Instance1_8_R3;
import lombok.Getter;
import org.bukkit.Bukkit;

@Getter
public class InstanceManager {
    private Instance instance;
    private final String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];


    public void create() {

        if (version.equalsIgnoreCase("1_7_R4")) {
     //       this.instance = new Instance1_7_R4();
        } else {
            this.instance = new Instance1_8_R3();
        }
    }
}
