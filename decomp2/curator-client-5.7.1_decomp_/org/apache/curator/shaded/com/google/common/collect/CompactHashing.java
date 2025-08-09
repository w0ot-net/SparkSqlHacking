package org.apache.curator.shaded.com.google.common.collect;

import java.util.Arrays;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.base.Objects;
import org.checkerframework.checker.nullness.qual.Nullable;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
final class CompactHashing {
   static final byte UNSET = 0;
   private static final int HASH_TABLE_BITS_MAX_BITS = 5;
   static final int MODIFICATION_COUNT_INCREMENT = 32;
   static final int HASH_TABLE_BITS_MASK = 31;
   static final int MAX_SIZE = 1073741823;
   static final int DEFAULT_SIZE = 3;
   private static final int MIN_HASH_TABLE_SIZE = 4;
   private static final int BYTE_MAX_SIZE = 256;
   private static final int BYTE_MASK = 255;
   private static final int SHORT_MAX_SIZE = 65536;
   private static final int SHORT_MASK = 65535;

   private CompactHashing() {
   }

   static int tableSize(int expectedSize) {
      return Math.max(4, Hashing.closedTableSize(expectedSize + 1, (double)1.0F));
   }

   static Object createTable(int buckets) {
      if (buckets >= 2 && buckets <= 1073741824 && Integer.highestOneBit(buckets) == buckets) {
         if (buckets <= 256) {
            return new byte[buckets];
         } else {
            return buckets <= 65536 ? new short[buckets] : new int[buckets];
         }
      } else {
         throw new IllegalArgumentException("must be power of 2 between 2^1 and 2^30: " + buckets);
      }
   }

   static void tableClear(Object table) {
      if (table instanceof byte[]) {
         Arrays.fill((byte[])table, (byte)0);
      } else if (table instanceof short[]) {
         Arrays.fill((short[])table, (short)0);
      } else {
         Arrays.fill((int[])table, 0);
      }

   }

   static int tableGet(Object table, int index) {
      if (table instanceof byte[]) {
         return ((byte[])table)[index] & 255;
      } else {
         return table instanceof short[] ? ((short[])table)[index] & '\uffff' : ((int[])table)[index];
      }
   }

   static void tableSet(Object table, int index, int entry) {
      if (table instanceof byte[]) {
         ((byte[])table)[index] = (byte)entry;
      } else if (table instanceof short[]) {
         ((short[])table)[index] = (short)entry;
      } else {
         ((int[])table)[index] = entry;
      }

   }

   static int newCapacity(int mask) {
      return (mask < 32 ? 4 : 2) * (mask + 1);
   }

   static int getHashPrefix(int value, int mask) {
      return value & ~mask;
   }

   static int getNext(int entry, int mask) {
      return entry & mask;
   }

   static int maskCombine(int prefix, int suffix, int mask) {
      return prefix & ~mask | suffix & mask;
   }

   static int remove(@CheckForNull Object key, @CheckForNull Object value, int mask, Object table, int[] entries, @Nullable Object[] keys, @CheckForNull @Nullable Object[] values) {
      int hash = Hashing.smearedHash(key);
      int tableIndex = hash & mask;
      int next = tableGet(table, tableIndex);
      if (next == 0) {
         return -1;
      } else {
         int hashPrefix = getHashPrefix(hash, mask);
         int lastEntryIndex = -1;

         do {
            int entryIndex = next - 1;
            int entry = entries[entryIndex];
            if (getHashPrefix(entry, mask) == hashPrefix && Objects.equal(key, keys[entryIndex]) && (values == null || Objects.equal(value, values[entryIndex]))) {
               int newNext = getNext(entry, mask);
               if (lastEntryIndex == -1) {
                  tableSet(table, tableIndex, newNext);
               } else {
                  entries[lastEntryIndex] = maskCombine(entries[lastEntryIndex], newNext, mask);
               }

               return entryIndex;
            }

            lastEntryIndex = entryIndex;
            next = getNext(entry, mask);
         } while(next != 0);

         return -1;
      }
   }
}
