package org.glassfish.jersey.internal.guava;

public final class MapMaker extends GenericMapMaker {
   static final int UNSET_INT = -1;
   private static final int DEFAULT_INITIAL_CAPACITY = 16;
   private static final int DEFAULT_CONCURRENCY_LEVEL = 4;
   private static final int DEFAULT_EXPIRATION_NANOS = 0;
   private final int initialCapacity = -1;
   private final int concurrencyLevel = -1;
   final int maximumSize = -1;
   private final long expireAfterWriteNanos = -1L;
   private final long expireAfterAccessNanos = -1L;

   Equivalence getKeyEquivalence() {
      return this.getKeyStrength().defaultEquivalence();
   }

   int getInitialCapacity() {
      return 16;
   }

   int getConcurrencyLevel() {
      return 4;
   }

   MapMakerInternalMap.Strength getKeyStrength() {
      return MapMakerInternalMap.Strength.STRONG;
   }

   MapMakerInternalMap.Strength getValueStrength() {
      return MapMakerInternalMap.Strength.STRONG;
   }

   long getExpireAfterWriteNanos() {
      return 0L;
   }

   long getExpireAfterAccessNanos() {
      return 0L;
   }

   Ticker getTicker() {
      return Ticker.systemTicker();
   }

   public String toString() {
      MoreObjects.ToStringHelper s = MoreObjects.toStringHelper(this);
      return s.toString();
   }

   static enum RemovalCause {
      EXPLICIT {
      },
      REPLACED {
      },
      COLLECTED {
      },
      EXPIRED {
      },
      SIZE {
      };

      private RemovalCause() {
      }
   }

   static final class RemovalNotification extends ImmutableEntry {
      private static final long serialVersionUID = 0L;

      RemovalNotification(Object key, Object value, RemovalCause cause) {
         super(key, value);
      }
   }

   interface RemovalListener {
      void onRemoval(RemovalNotification var1);
   }
}
