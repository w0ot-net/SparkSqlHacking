package io.vertx.core.cli.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Option {
   String NO_NAME = "\u0000";

   String longName() default "\u0000";

   String shortName() default "\u0000";

   String argName() default "value";

   boolean required() default false;

   boolean acceptValue() default true;

   boolean acceptMultipleValues() default false;

   boolean flag() default false;

   boolean help() default false;

   String[] choices() default {};
}
