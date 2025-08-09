package org.checkerframework.checker.calledmethods.qual;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.framework.qual.InheritedAnnotation;

@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
@Repeatable(List.class)
@Retention(RetentionPolicy.RUNTIME)
@InheritedAnnotation
public @interface EnsuresCalledMethodsOnException {
   String[] value();

   String[] methods();

   @Documented
   @Retention(RetentionPolicy.RUNTIME)
   @Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
   @InheritedAnnotation
   public @interface List {
      EnsuresCalledMethodsOnException[] value();
   }
}
