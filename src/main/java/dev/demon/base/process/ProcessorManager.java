package dev.demon.base.process;

import dev.demon.base.process.processors.AbilitiesProcessor;
import dev.demon.base.process.processors.MovementProcessor;
import dev.demon.base.process.processors.PotionProcessor;
import dev.demon.base.user.User;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ProcessorManager {
    private final List<Processor> processors = new ArrayList<>();

    private final MovementProcessor movementProcessor;
    private final AbilitiesProcessor abilitiesProcessor;
    private final PotionProcessor potionProcessor;

    public ProcessorManager(User user) {
        this.processors.add(this.movementProcessor = new MovementProcessor(user));
        this.processors.add(this.abilitiesProcessor = new AbilitiesProcessor(user));
        this.processors.add(this.potionProcessor = new PotionProcessor(user));
    }
}
