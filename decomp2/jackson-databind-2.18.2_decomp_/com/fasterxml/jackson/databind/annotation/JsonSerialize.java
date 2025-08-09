package com.fasterxml.jackson.databind.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.util.Converter;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotation
public @interface JsonSerialize {
   Class using() default JsonSerializer.None.class;

   Class contentUsing() default JsonSerializer.None.class;

   Class keyUsing() default JsonSerializer.None.class;

   Class nullsUsing() default JsonSerializer.None.class;

   Class as() default Void.class;

   Class keyAs() default Void.class;

   Class contentAs() default Void.class;

   Typing typing() default JsonSerialize.Typing.DEFAULT_TYPING;

   Class converter() default Converter.None.class;

   Class contentConverter() default Converter.None.class;

   /** @deprecated */
   @Deprecated
   Inclusion include() default JsonSerialize.Inclusion.DEFAULT_INCLUSION;

   /** @deprecated */
   @Deprecated
   public static enum Inclusion {
      ALWAYS,
      NON_NULL,
      NON_DEFAULT,
      NON_EMPTY,
      DEFAULT_INCLUSION;
   }

   public static enum Typing {
      DYNAMIC,
      STATIC,
      DEFAULT_TYPING;
   }
}
