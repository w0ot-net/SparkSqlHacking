package org.apache.datasketches.cpc;

import java.util.Arrays;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.SketchesStateException;
import org.apache.datasketches.common.Util;

final class PairTable {
   private static final int upsizeNumer = 3;
   private static final int upsizeDenom = 4;
   private static final int downsizeNumer = 1;
   private static final int downsizeDenom = 4;
   private int lgSizeInts;
   private final int validBits;
   private int numPairs;
   private int[] slotsArr;

   PairTable(int lgSizeInts, int numValidBits) {
      checkLgSizeInts(lgSizeInts);
      this.lgSizeInts = lgSizeInts;
      int numSlots = 1 << lgSizeInts;
      this.validBits = numValidBits;
      this.numPairs = 0;
      this.slotsArr = new int[numSlots];

      for(int i = 0; i < numSlots; ++i) {
         this.slotsArr[i] = -1;
      }

   }

   static PairTable newInstanceFromPairsArray(int[] pairs, int numPairs, int lgK) {
      int lgNumSlots;
      for(lgNumSlots = 2; 4 * numPairs > 3 * (1 << lgNumSlots); ++lgNumSlots) {
      }

      PairTable table = new PairTable(lgNumSlots, 6 + lgK);

      for(int i = 0; i < numPairs; ++i) {
         mustInsert(table, pairs[i]);
      }

      table.numPairs = numPairs;
      return table;
   }

   PairTable clear() {
      Arrays.fill(this.slotsArr, -1);
      this.numPairs = 0;
      return this;
   }

   PairTable copy() {
      PairTable copy = new PairTable(this.lgSizeInts, this.validBits);
      copy.numPairs = this.numPairs;
      copy.slotsArr = (int[])this.slotsArr.clone();
      return copy;
   }

   int getLgSizeInts() {
      return this.lgSizeInts;
   }

   int getNumPairs() {
      return this.numPairs;
   }

   int[] getSlotsArr() {
      return this.slotsArr;
   }

   int getValidBits() {
      return this.validBits;
   }

   PairTable rebuild(int newLgSizeInts) {
      checkLgSizeInts(newLgSizeInts);
      int newSize = 1 << newLgSizeInts;
      int oldSize = 1 << this.lgSizeInts;
      RuntimeAsserts.rtAssert(newSize > this.numPairs);
      int[] oldSlotsArr = this.slotsArr;
      this.slotsArr = new int[newSize];
      Arrays.fill(this.slotsArr, -1);
      this.lgSizeInts = newLgSizeInts;

      for(int i = 0; i < oldSize; ++i) {
         int item = oldSlotsArr[i];
         if (item != -1) {
            mustInsert(this, item);
         }
      }

      return this;
   }

   public String toString() {
      return this.toString(false);
   }

   private static void mustInsert(PairTable table, int item) {
      int lgSizeInts = table.lgSizeInts;
      int sizeInts = 1 << lgSizeInts;
      int mask = sizeInts - 1;
      int shift = table.validBits - lgSizeInts;
      RuntimeAsserts.rtAssert(shift > 0);
      int probe = item >>> shift;
      RuntimeAsserts.rtAssert(probe >= 0 && probe <= mask);
      int[] arr = table.slotsArr;

      int fetched;
      for(fetched = arr[probe]; fetched != item && fetched != -1; fetched = arr[probe]) {
         probe = probe + 1 & mask;
      }

      if (fetched == item) {
         throw new SketchesStateException("PairTable mustInsert() failed");
      } else {
         assert fetched == -1;

         arr[probe] = item;
      }
   }

   static boolean maybeInsert(PairTable table, int item) {
      int lgSizeInts = table.lgSizeInts;
      int sizeInts = 1 << lgSizeInts;
      int mask = sizeInts - 1;
      int shift = table.validBits - lgSizeInts;
      RuntimeAsserts.rtAssert(shift > 0);
      int probe = item >>> shift;
      RuntimeAsserts.rtAssert(probe >= 0 && probe <= mask);
      int[] arr = table.slotsArr;

      int fetched;
      for(fetched = arr[probe]; fetched != item && fetched != -1; fetched = arr[probe]) {
         probe = probe + 1 & mask;
      }

      if (fetched == item) {
         return false;
      } else {
         assert fetched == -1;

         arr[probe] = item;
         ++table.numPairs;

         while(4 * table.numPairs > 3 * (1 << table.lgSizeInts)) {
            table.rebuild(table.lgSizeInts + 1);
         }

         return true;
      }
   }

   static boolean maybeDelete(PairTable table, int item) {
      int lgSizeInts = table.lgSizeInts;
      int sizeInts = 1 << lgSizeInts;
      int mask = sizeInts - 1;
      int shift = table.validBits - lgSizeInts;
      RuntimeAsserts.rtAssert(shift > 0);
      int probe = item >>> shift;
      RuntimeAsserts.rtAssert(probe >= 0 && probe <= mask);
      int[] arr = table.slotsArr;

      int fetched;
      for(fetched = arr[probe]; fetched != item && fetched != -1; fetched = arr[probe]) {
         probe = probe + 1 & mask;
      }

      if (fetched == -1) {
         return false;
      } else {
         assert fetched == item;

         arr[probe] = -1;
         --table.numPairs;

         assert table.numPairs >= 0;

         probe = probe + 1 & mask;

         for(int var10 = arr[probe]; var10 != -1; var10 = arr[probe]) {
            arr[probe] = -1;
            mustInsert(table, var10);
            probe = probe + 1 & mask;
         }

         while(4 * table.numPairs < 1 * (1 << table.lgSizeInts) && table.lgSizeInts > 2) {
            table.rebuild(table.lgSizeInts - 1);
         }

         return true;
      }
   }

   static int[] unwrappingGetItems(PairTable table, int numPairs) {
      if (numPairs < 1) {
         return null;
      } else {
         int[] slotsArr = table.slotsArr;
         int tableSize = 1 << table.lgSizeInts;
         int[] result = new int[numPairs];
         int i = 0;
         int l = 0;
         int r = numPairs - 1;
         int hiBit = 1 << table.validBits - 1;

         while(i < tableSize && slotsArr[i] != -1) {
            int item = slotsArr[i++];
            if ((item & hiBit) != 0) {
               result[r--] = item;
            } else {
               result[l++] = item;
            }
         }

         while(i < tableSize) {
            int look = slotsArr[i++];
            if (look != -1) {
               result[l++] = look;
            }
         }

         assert l == r + 1;

         return result;
      }
   }

   static void introspectiveInsertionSort(int[] a, int l, int r) {
      int length = r - l + 1;
      long cost = 0L;
      long costLimit = 8L * (long)length;

      for(int i = l + 1; i <= r; ++i) {
         int j = i;

         long v;
         for(v = (long)a[i] & 4294967295L; j >= l + 1 && v < ((long)a[j - 1] & 4294967295L); --j) {
            a[j] = a[j - 1];
         }

         a[j] = (int)v;
         cost += (long)(i - j);
         if (cost > costLimit) {
            long[] b = new long[a.length];

            for(int m = 0; m < a.length; ++m) {
               b[m] = (long)a[m] & 4294967295L;
            }

            Arrays.sort(b, l, r + 1);

            for(int m = 0; m < a.length; ++m) {
               a[m] = (int)b[m];
            }

            return;
         }
      }

   }

   static void merge(int[] arrA, int startA, int lengthA, int[] arrB, int startB, int lengthB, int[] arrC, int startC) {
      int lengthC = lengthA + lengthB;
      int limA = startA + lengthA;
      int limB = startB + lengthB;
      int limC = startC + lengthC;
      int a = startA;
      int b = startB;

      for(int c = startC; c < limC; ++c) {
         if (b >= limB) {
            arrC[c] = arrA[a++];
         } else if (a >= limA) {
            arrC[c] = arrB[b++];
         } else {
            long aa = (long)arrA[a] & 4294967295L;
            long bb = (long)arrB[b] & 4294967295L;
            if (aa < bb) {
               arrC[c] = arrA[a++];
            } else {
               arrC[c] = arrB[b++];
            }
         }
      }

      assert a == limA;

      assert b == limB;

   }

   static boolean equals(PairTable a, PairTable b) {
      if (a == null && b == null) {
         return true;
      } else if (a != null && b != null) {
         RuntimeAsserts.rtAssertEquals((long)a.validBits, (long)b.validBits);
         int numPairsA = a.numPairs;
         int numPairsB = b.numPairs;
         RuntimeAsserts.rtAssertEquals((long)numPairsA, (long)numPairsB);
         int[] pairsA = unwrappingGetItems(a, numPairsA);
         int[] pairsB = unwrappingGetItems(b, numPairsB);
         introspectiveInsertionSort(pairsA, 0, numPairsA - 1);
         introspectiveInsertionSort(pairsB, 0, numPairsB - 1);
         RuntimeAsserts.rtAssertEquals(pairsA, pairsB);
         return true;
      } else {
         throw new SketchesArgumentException("PairTable " + (a == null ? "a" : "b") + " is null");
      }
   }

   String toString(boolean detail) {
      StringBuilder sb = new StringBuilder();
      int sizeInts = 1 << this.lgSizeInts;
      sb.append("PairTable").append(Util.LS);
      sb.append("  Lg Size Ints  : ").append(this.lgSizeInts).append(Util.LS);
      sb.append("  Size Ints     : ").append(sizeInts).append(Util.LS);
      sb.append("  Valid Bits    : ").append(this.validBits).append(Util.LS);
      sb.append("  Num Pairs     : ").append(this.numPairs).append(Util.LS);
      if (detail) {
         sb.append("  DATA (hex) : ").append(Util.LS);
         String hdr = String.format("%9s %9s %9s %4s", "Index", "Word", "Row", "Col");
         sb.append(hdr).append(Util.LS);

         for(int i = 0; i < sizeInts; ++i) {
            int word = this.slotsArr[i];
            if (word == -1) {
               String h = String.format("%9d %9s", i, "--");
               sb.append(h).append(Util.LS);
            } else {
               int row = word >>> 6;
               int col = word & 63;
               String wordStr = Long.toHexString((long)word & 4294967295L);
               String data = String.format("%9d %9s %9d %4d", i, wordStr, row, col);
               sb.append(data).append(Util.LS);
            }
         }
      }

      return sb.toString();
   }

   private static void checkLgSizeInts(int lgSizeInts) {
      if (lgSizeInts < 2 || lgSizeInts > 26) {
         throw new SketchesArgumentException("Illegal LgSizeInts: " + lgSizeInts);
      }
   }
}
