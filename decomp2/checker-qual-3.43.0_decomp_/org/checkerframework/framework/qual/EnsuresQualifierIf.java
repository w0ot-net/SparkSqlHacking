package org.checkerframework.framework.qual;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@InheritedAnnotation
@Repeatable(List.class)
public @interface EnsuresQualifierIf {
   boolean result();

   String[] expression();

   Class qualifier();

   @Documented
   @Retention(RetentionPolicy.RUNTIME)
   @Target({ElementType.METHOD})
   @InheritedAnnotation
   public @interface List {
      EnsuresQualifierIf[] value();
   }
}
