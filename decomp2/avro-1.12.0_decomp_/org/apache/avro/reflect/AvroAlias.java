package org.apache.avro.reflect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
@Repeatable(AvroAliases.class)
public @interface AvroAlias {
   String NULL = "NOT A VALID NAMESPACE";

   String alias();

   String space() default "NOT A VALID NAMESPACE";
}
