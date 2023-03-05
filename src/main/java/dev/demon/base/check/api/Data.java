package dev.demon.base.check.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Data {

     boolean enabled() default true;
     boolean punishable() default true;
     boolean experimental() default false;
     double punishmentVL() default 20.0;

     String name();
     String subName() default "A";
     String description() default "Detects if the player is cheating via Packets";

     CheckType checkType() default CheckType.MOVEMENT;

}
