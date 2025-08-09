package org.apache.hadoop.hive.common.classification;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@InterfaceStability.Evolving
@InterfaceAudience.LimitedPrivate({"Hive developer"})
public class RetrySemantics {
   @Retention(RetentionPolicy.RUNTIME)
   @Target({ElementType.METHOD})
   public @interface CannotRetry {
   }

   @Retention(RetentionPolicy.RUNTIME)
   @Target({ElementType.METHOD})
   public @interface Idempotent {
      String[] value() default {""};

      int maxRetryCount() default Integer.MAX_VALUE;

      int delayMs() default 100;
   }

   @Retention(RetentionPolicy.RUNTIME)
   @Target({ElementType.METHOD})
   public @interface ReadOnly {
   }

   @Retention(RetentionPolicy.RUNTIME)
   @Target({ElementType.METHOD})
   public @interface SafeToRetry {
      String[] value() default {""};
   }
}
