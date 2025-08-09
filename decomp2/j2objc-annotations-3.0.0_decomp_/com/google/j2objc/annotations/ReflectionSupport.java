package com.google.j2objc.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.TYPE, ElementType.PACKAGE})
@Retention(RetentionPolicy.CLASS)
public @interface ReflectionSupport {
   Level value();

   public static enum Level {
      NATIVE_ONLY,
      FULL;

      // $FF: synthetic method
      private static Level[] $values() {
         return new Level[]{NATIVE_ONLY, FULL};
      }
   }
}
