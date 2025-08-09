package org.apache.zookeeper.server;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.zookeeper.CreateMode;

public enum EphemeralType {
   VOID,
   NORMAL,
   CONTAINER,
   TTL {
      public long maxValue() {
         return 1099511627775L;
      }

      public long toEphemeralOwner(long ttl) {
         if (ttl <= TTL.maxValue() && ttl > 0L) {
            return -72057594037927936L | ttl;
         } else {
            throw new IllegalArgumentException("ttl must be positive and cannot be larger than: " + TTL.maxValue());
         }
      }

      public long getValue(long ephemeralOwner) {
         return EphemeralType.getExtendedFeatureValue(ephemeralOwner);
      }
   };

   public static final long CONTAINER_EPHEMERAL_OWNER = Long.MIN_VALUE;
   public static final long MAX_EXTENDED_SERVER_ID = 254L;
   private static final long EXTENDED_MASK = -72057594037927936L;
   private static final long EXTENDED_BIT_TTL = 0L;
   private static final long RESERVED_BITS_MASK = 72056494526300160L;
   private static final long RESERVED_BITS_SHIFT = 40L;
   private static final Map extendedFeatureMap;
   private static final long EXTENDED_FEATURE_VALUE_MASK = 1099511627775L;
   static final String EXTENDED_TYPES_ENABLED_PROPERTY = "zookeeper.extendedTypesEnabled";
   static final String TTL_3_5_3_EMULATION_PROPERTY = "zookeeper.emulate353TTLNodes";

   private EphemeralType() {
   }

   public long maxValue() {
      return 0L;
   }

   public long toEphemeralOwner(long value) {
      return 0L;
   }

   public long getValue(long ephemeralOwner) {
      return 0L;
   }

   public static boolean extendedEphemeralTypesEnabled() {
      return Boolean.getBoolean("zookeeper.extendedTypesEnabled");
   }

   public static EphemeralType get(long ephemeralOwner) {
      if (extendedEphemeralTypesEnabled()) {
         if (Boolean.getBoolean("zookeeper.emulate353TTLNodes") && EphemeralTypeEmulate353.get(ephemeralOwner) == EphemeralTypeEmulate353.TTL) {
            return TTL;
         }

         if ((ephemeralOwner & -72057594037927936L) == -72057594037927936L) {
            long extendedFeatureBit = getExtendedFeatureBit(ephemeralOwner);
            EphemeralType ephemeralType = (EphemeralType)extendedFeatureMap.get(extendedFeatureBit);
            if (ephemeralType == null) {
               throw new IllegalArgumentException(String.format("Invalid ephemeralOwner. [%s]", Long.toHexString(ephemeralOwner)));
            }

            return ephemeralType;
         }
      }

      if (ephemeralOwner == Long.MIN_VALUE) {
         return CONTAINER;
      } else {
         return ephemeralOwner == 0L ? VOID : NORMAL;
      }
   }

   public static void validateServerId(long serverId) {
      if (extendedEphemeralTypesEnabled() && serverId > 254L) {
         throw new RuntimeException("extendedTypesEnabled is true but Server ID is too large. Cannot be larger than 254");
      }
   }

   @SuppressFBWarnings(
      value = {"RV_RETURN_VALUE_IGNORED_NO_SIDE_EFFECT"},
      justification = "toEphemeralOwner may throw IllegalArgumentException"
   )
   public static void validateTTL(CreateMode mode, long ttl) {
      if (mode.isTTL()) {
         TTL.toEphemeralOwner(ttl);
      } else if (ttl >= 0L) {
         throw new IllegalArgumentException("ttl not valid for mode: " + mode);
      }

   }

   private static long getExtendedFeatureBit(long ephemeralOwner) {
      return (ephemeralOwner & 72056494526300160L) >> 40;
   }

   private static long getExtendedFeatureValue(long ephemeralOwner) {
      return ephemeralOwner & 1099511627775L;
   }

   static {
      Map<Long, EphemeralType> map = new HashMap();
      map.put(0L, TTL);
      extendedFeatureMap = Collections.unmodifiableMap(map);
   }
}
