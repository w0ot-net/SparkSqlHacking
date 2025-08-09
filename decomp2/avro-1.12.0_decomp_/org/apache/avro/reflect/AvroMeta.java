package org.apache.avro.reflect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
@Repeatable(AvroMetas.class)
public @interface AvroMeta {
   String key();

   String value();

   @Retention(RetentionPolicy.RUNTIME)
   @Target({ElementType.TYPE, ElementType.FIELD})
   public @interface AvroMetas {
      AvroMeta[] value();
   }
}
