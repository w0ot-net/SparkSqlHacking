package com.univocity.parsers.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
public @interface Headers {
   String[] sequence() default {};

   boolean write() default true;

   boolean extract() default false;
}
