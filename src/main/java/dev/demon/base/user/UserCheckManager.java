package dev.demon.base.user;

//optimized imports to make life easier


import dev.demon.Anticheat;
import dev.demon.base.check.api.Check;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class UserCheckManager {

    @Getter
    public final List<Check> checks = new ArrayList<>();


    public void register(User user) {
        List<Check> clonedChecks = Anticheat.getInstance().getCheckManager().cloneChecks();
        this.checks.addAll(clonedChecks);

        for (Check actualCheck : this.checks) {
            actualCheck.setUser(user);
        }

        clonedChecks.clear();
    }
}
