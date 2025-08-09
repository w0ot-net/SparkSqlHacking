package io.netty.channel;

import io.netty.util.internal.ObjectUtil;
import java.util.ArrayList;
import java.util.List;

public class AdaptiveRecvByteBufAllocator extends DefaultMaxMessagesRecvByteBufAllocator {
   public static final int DEFAULT_MINIMUM = 64;
   public static final int DEFAULT_INITIAL = 2048;
   public static final int DEFAULT_MAXIMUM = 65536;
   private static final int INDEX_INCREMENT = 4;
   private static final int INDEX_DECREMENT = 1;
   private static final int[] SIZE_TABLE;
   /** @deprecated */
   @Deprecated
   public static final AdaptiveRecvByteBufAllocator DEFAULT;
   private final int minIndex;
   private final int maxIndex;
   private final int initialIndex;
   private final int minCapacity;
   private final int maxCapacity;

   private static int getSizeTableIndex(int size) {
      int low = 0;
      int high = SIZE_TABLE.length - 1;

      while(high >= low) {
         if (high == low) {
            return high;
         }

         int mid = low + high >>> 1;
         int a = SIZE_TABLE[mid];
         int b = SIZE_TABLE[mid + 1];
         if (size > b) {
            low = mid + 1;
         } else {
            if (size >= a) {
               if (size == a) {
                  return mid;
               }

               return mid + 1;
            }

            high = mid - 1;
         }
      }

      return low;
   }

   public AdaptiveRecvByteBufAllocator() {
      this(64, 2048, 65536);
   }

   public AdaptiveRecvByteBufAllocator(int minimum, int initial, int maximum) {
      ObjectUtil.checkPositive(minimum, "minimum");
      if (initial < minimum) {
         throw new IllegalArgumentException("initial: " + initial);
      } else if (maximum < initial) {
         throw new IllegalArgumentException("maximum: " + maximum);
      } else {
         int minIndex = getSizeTableIndex(minimum);
         if (SIZE_TABLE[minIndex] < minimum) {
            this.minIndex = minIndex + 1;
         } else {
            this.minIndex = minIndex;
         }

         int maxIndex = getSizeTableIndex(maximum);
         if (SIZE_TABLE[maxIndex] > maximum) {
            this.maxIndex = maxIndex - 1;
         } else {
            this.maxIndex = maxIndex;
         }

         int initialIndex = getSizeTableIndex(initial);
         if (SIZE_TABLE[initialIndex] > initial) {
            this.initialIndex = initialIndex - 1;
         } else {
            this.initialIndex = initialIndex;
         }

         this.minCapacity = minimum;
         this.maxCapacity = maximum;
      }
   }

   public RecvByteBufAllocator.Handle newHandle() {
      return new HandleImpl(this.minIndex, this.maxIndex, this.initialIndex, this.minCapacity, this.maxCapacity);
   }

   public AdaptiveRecvByteBufAllocator respectMaybeMoreData(boolean respectMaybeMoreData) {
      super.respectMaybeMoreData(respectMaybeMoreData);
      return this;
   }

   static {
      List<Integer> sizeTable = new ArrayList();

      for(int i = 16; i < 512; i += 16) {
         sizeTable.add(i);
      }

      for(int i = 512; i > 0; i <<= 1) {
         sizeTable.add(i);
      }

      SIZE_TABLE = new int[sizeTable.size()];

      for(int i = 0; i < SIZE_TABLE.length; ++i) {
         SIZE_TABLE[i] = (Integer)sizeTable.get(i);
      }

      DEFAULT = new AdaptiveRecvByteBufAllocator();
   }

   private final class HandleImpl extends DefaultMaxMessagesRecvByteBufAllocator.MaxMessageHandle {
      private final int minIndex;
      private final int maxIndex;
      private final int minCapacity;
      private final int maxCapacity;
      private int index;
      private int nextReceiveBufferSize;
      private boolean decreaseNow;

      HandleImpl(int minIndex, int maxIndex, int initialIndex, int minCapacity, int maxCapacity) {
         this.minIndex = minIndex;
         this.maxIndex = maxIndex;
         this.index = initialIndex;
         this.nextReceiveBufferSize = Math.max(AdaptiveRecvByteBufAllocator.SIZE_TABLE[this.index], minCapacity);
         this.minCapacity = minCapacity;
         this.maxCapacity = maxCapacity;
      }

      public void lastBytesRead(int bytes) {
         if (bytes == this.attemptedBytesRead()) {
            this.record(bytes);
         }

         super.lastBytesRead(bytes);
      }

      public int guess() {
         return this.nextReceiveBufferSize;
      }

      private void record(int actualReadBytes) {
         if (actualReadBytes <= AdaptiveRecvByteBufAllocator.SIZE_TABLE[Math.max(0, this.index - 1)]) {
            if (this.decreaseNow) {
               this.index = Math.max(this.index - 1, this.minIndex);
               this.nextReceiveBufferSize = Math.max(AdaptiveRecvByteBufAllocator.SIZE_TABLE[this.index], this.minCapacity);
               this.decreaseNow = false;
            } else {
               this.decreaseNow = true;
            }
         } else if (actualReadBytes >= this.nextReceiveBufferSize) {
            this.index = Math.min(this.index + 4, this.maxIndex);
            this.nextReceiveBufferSize = Math.min(AdaptiveRecvByteBufAllocator.SIZE_TABLE[this.index], this.maxCapacity);
            this.decreaseNow = false;
         }

      }

      public void readComplete() {
         this.record(this.totalBytesRead());
      }
   }
}
