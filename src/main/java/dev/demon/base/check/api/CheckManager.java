package dev.demon.base.check.api;

import java.util.ArrayList;
import java.util.List;

public class CheckManager {
    public final List<Check> checks = new ArrayList<>();

    public void loadChecks() {


    }

    public List<Check> cloneChecks() {
        List<Check> checks = new ArrayList<>();
        this.checks.forEach(check -> checks.add(check.clone()));
        return checks;
    }
}