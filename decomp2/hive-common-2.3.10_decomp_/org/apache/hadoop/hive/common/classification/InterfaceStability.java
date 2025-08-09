package org.apache.hadoop.hive.common.classification;

import java.lang.annotation.Documented;

public class InterfaceStability {
   @Documented
   public @interface Evolving {
   }

   @Documented
   public @interface Stable {
   }

   @Documented
   public @interface Unstable {
   }
}
