package org.apache.hadoop.hive.common.classification;

import java.lang.annotation.Documented;

public class InterfaceAudience {
   private InterfaceAudience() {
   }

   @Documented
   public @interface LimitedPrivate {
      String[] value();
   }

   @Documented
   public @interface Private {
   }

   @Documented
   public @interface Public {
   }
}
