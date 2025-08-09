package com.univocity.parsers.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
public @interface Validate {
   boolean nullable() default false;

   boolean allowBlanks() default false;

   String matches() default "";

   String[] oneOf() default {};

   String[] noneOf() default {};

   Class[] validators() default {};
}
