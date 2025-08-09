package org.apache.yetus.audience;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@InterfaceAudience.Public
@InterfaceStability.Evolving
public class InterfaceStability {
   @Documented
   @Retention(RetentionPolicy.RUNTIME)
   public @interface Evolving {
   }

   @Documented
   @Retention(RetentionPolicy.RUNTIME)
   public @interface Stable {
   }

   @Documented
   @Retention(RetentionPolicy.RUNTIME)
   public @interface Unstable {
   }
}
