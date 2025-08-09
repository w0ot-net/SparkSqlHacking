package org.glassfish.jersey.internal.util.collection;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedTransferQueue;

public final class DataStructures {
   public static final int DEFAULT_CONCURENCY_LEVEL = ceilingNextPowerOfTwo(Runtime.getRuntime().availableProcessors());

   private static int ceilingNextPowerOfTwo(int x) {
      return 1 << 32 - Integer.numberOfLeadingZeros(x - 1);
   }

   /** @deprecated */
   @Deprecated
   public static BlockingQueue createLinkedTransferQueue() {
      return new LinkedTransferQueue();
   }

   /** @deprecated */
   @Deprecated
   public static ConcurrentMap createConcurrentMap() {
      return new ConcurrentHashMap();
   }

   /** @deprecated */
   @Deprecated
   public static ConcurrentMap createConcurrentMap(Map map) {
      return new ConcurrentHashMap(map);
   }

   /** @deprecated */
   @Deprecated
   public static ConcurrentMap createConcurrentMap(int initialCapacity) {
      return new ConcurrentHashMap(initialCapacity);
   }

   /** @deprecated */
   @Deprecated
   public static ConcurrentMap createConcurrentMap(int initialCapacity, float loadFactor, int concurrencyLevel) {
      return new ConcurrentHashMap(initialCapacity, loadFactor, concurrencyLevel);
   }
}
