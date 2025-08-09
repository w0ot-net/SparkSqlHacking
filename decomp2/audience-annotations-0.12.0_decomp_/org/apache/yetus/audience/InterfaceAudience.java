package org.apache.yetus.audience;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@InterfaceAudience.Public
@InterfaceStability.Evolving
public class InterfaceAudience {
   private InterfaceAudience() {
   }

   @Documented
   @Retention(RetentionPolicy.RUNTIME)
   public @interface LimitedPrivate {
      String[] value();
   }

   @Documented
   @Retention(RetentionPolicy.RUNTIME)
   public @interface Private {
   }

   @Documented
   @Retention(RetentionPolicy.RUNTIME)
   public @interface Public {
   }
}
