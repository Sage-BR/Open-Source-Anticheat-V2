package dev.demon.base.check.api;

public enum CheckType {
    COMBAT("Combat"),
    MOVEMENT("Movement"),
    MISC("MISC");

    final String name;

    CheckType(String name) {
        this.name = name;
    }
}
