package dev.demon.base.check.api;

import dev.demon.Anticheat;
import dev.demon.base.event.Event;
import dev.demon.base.user.User;
import lombok.Getter;
import lombok.Setter;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

@Getter
@Setter
public abstract class Check extends Event implements Cloneable {

    @Setter
    private User user;
    private Data data;
    private double violations;
    private double punishmentVL;
    private String checkName, checkType;
    private CheckType checkCategory;
    private boolean enabled;
    private boolean punishable, experimental;

    public Check(User user) {

        this.user = user;

        if (getClass().isAnnotationPresent(Data.class)) {
            this.data = getClass().getAnnotation(Data.class);

            this.punishmentVL = this.data.punishmentVL();
            this.checkName = this.data.name();
            this.checkType = this.data.subName();
            this.enabled = this.data.enabled();
            this.punishable = this.data.punishable();
            this.experimental = this.data.experimental();
            this.checkCategory = this.data.checkType();
        }
    }

    public Check clone() {
        try {
            return (Check) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void fail(String... data) {

        StringBuilder stringBuilder = new StringBuilder();

        for (String s : data) {
            stringBuilder.append(s).append(", ");
        }

        String checkType = this.checkType;

        if (this.experimental) {
            checkType += "*";
        }

        String alert = "Anticheat >> " + this.user.getPlayer().getName() + " flagged "
                + this.checkName + " (" + checkType + ") " +
                "(" + this.violations + "/" + this.punishmentVL + ")";

        TextComponent textComponent = new TextComponent(alert);

        textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                new ComponentBuilder(stringBuilder.toString().trim()).create()));

        Anticheat.getInstance().getUserManager().getUserMap().entrySet().stream().filter(uuidUserEntry ->
                        uuidUserEntry.getValue().getPlayer().isOp() && uuidUserEntry.getValue().isAlerts())
                .forEach(uuidUserEntry -> uuidUserEntry.getValue().getPlayer().spigot().sendMessage(textComponent));


        if (this.punishable) {
            this.violations += 1.0;
        }
    }
}
