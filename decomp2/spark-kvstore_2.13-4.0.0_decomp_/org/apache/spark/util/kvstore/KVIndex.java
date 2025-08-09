package org.apache.spark.util.kvstore;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.apache.spark.annotation.Private;

@Private
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface KVIndex {
   String NATURAL_INDEX_NAME = "__main__";

   String value() default "__main__";

   String parent() default "";

   boolean copy() default false;
}
