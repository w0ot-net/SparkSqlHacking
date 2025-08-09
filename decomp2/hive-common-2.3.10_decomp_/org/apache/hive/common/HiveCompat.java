package org.apache.hive.common;

import org.apache.hadoop.hive.conf.HiveConf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HiveCompat {
   private static Logger LOG = LoggerFactory.getLogger(HiveCompat.class);
   public static final String DEFAULT_COMPAT_LEVEL;
   public static final String LATEST_COMPAT_LEVEL;

   public static CompatLevel getCompatLevel(HiveConf hconf) {
      return getCompatLevel(HiveConf.getVar(hconf, HiveConf.ConfVars.HIVE_COMPAT));
   }

   public static CompatLevel getCompatLevel(String compatStr) {
      if (compatStr.equalsIgnoreCase("latest")) {
         compatStr = LATEST_COMPAT_LEVEL;
      }

      for(CompatLevel cl : HiveCompat.CompatLevel.values()) {
         if (cl.value.equals(compatStr)) {
            return cl;
         }
      }

      LOG.error("Could not find CompatLevel for " + compatStr + ", using default of " + DEFAULT_COMPAT_LEVEL);
      return getCompatLevel(DEFAULT_COMPAT_LEVEL);
   }

   private static CompatLevel getLastCompatLevel() {
      CompatLevel[] compatLevels = HiveCompat.CompatLevel.values();
      return compatLevels[compatLevels.length - 1];
   }

   static {
      DEFAULT_COMPAT_LEVEL = HiveCompat.CompatLevel.HIVE_0_12.value;
      LATEST_COMPAT_LEVEL = getLastCompatLevel().value;
   }

   public static enum CompatLevel {
      HIVE_0_12("0.12", 0, 12),
      HIVE_0_13("0.13", 0, 13);

      public final String value;
      public final int majorVersion;
      public final int minorVersion;

      private CompatLevel(String val, int majorVersion, int minorVersion) {
         this.value = val;
         this.majorVersion = majorVersion;
         this.minorVersion = minorVersion;
      }
   }
}
