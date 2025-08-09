package org.apache.arrow.memory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoundsChecking {
   public static final boolean BOUNDS_CHECKING_ENABLED;
   static final Logger logger = LoggerFactory.getLogger(BoundsChecking.class);

   private BoundsChecking() {
   }

   static {
      String envProperty = System.getenv("ARROW_ENABLE_UNSAFE_MEMORY_ACCESS");
      String oldProperty = System.getProperty("drill.enable_unsafe_memory_access");
      if (oldProperty != null) {
         logger.warn("\"drill.enable_unsafe_memory_access\" has been renamed to \"arrow.enable_unsafe_memory_access\"");
         logger.warn("\"arrow.enable_unsafe_memory_access\" can be set to:  true (to not check) or false (to check, default)");
      }

      String newProperty = System.getProperty("arrow.enable_unsafe_memory_access");
      String unsafeFlagValue = newProperty;
      if (newProperty == null) {
         unsafeFlagValue = oldProperty;
      }

      if (unsafeFlagValue == null) {
         unsafeFlagValue = envProperty;
      }

      BOUNDS_CHECKING_ENABLED = !"true".equals(unsafeFlagValue);
   }
}
