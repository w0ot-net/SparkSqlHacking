package org.apache.zookeeper.server;

public enum EphemeralTypeEmulate353 {
   VOID,
   NORMAL,
   CONTAINER,
   TTL;

   public static final long CONTAINER_EPHEMERAL_OWNER = Long.MIN_VALUE;
   public static final long MAX_TTL = 1152921504606846975L;
   public static final long TTL_MASK = Long.MIN_VALUE;

   public static EphemeralTypeEmulate353 get(long ephemeralOwner) {
      if (ephemeralOwner == Long.MIN_VALUE) {
         return CONTAINER;
      } else if (ephemeralOwner < 0L) {
         return TTL;
      } else {
         return ephemeralOwner == 0L ? VOID : NORMAL;
      }
   }

   public static long ttlToEphemeralOwner(long ttl) {
      if (ttl <= 1152921504606846975L && ttl > 0L) {
         return Long.MIN_VALUE | ttl;
      } else {
         throw new IllegalArgumentException("ttl must be positive and cannot be larger than: 1152921504606846975");
      }
   }
}
