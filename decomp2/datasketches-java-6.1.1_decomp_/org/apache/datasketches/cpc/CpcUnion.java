package org.apache.datasketches.cpc;

import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.SketchesStateException;

public class CpcUnion {
   private final long seed;
   private int lgK;
   private long[] bitMatrix;
   private CpcSketch accumulator;

   public CpcUnion() {
      this(11, 9001L);
   }

   public CpcUnion(int lgK) {
      this(lgK, 9001L);
   }

   public CpcUnion(int lgK, long seed) {
      this.seed = seed;
      this.lgK = lgK;
      this.bitMatrix = null;
      this.accumulator = new CpcSketch(lgK);
   }

   public void update(CpcSketch sketch) {
      mergeInto(this, sketch);
   }

   public CpcSketch getResult() {
      return getResult(this);
   }

   public int getLgK() {
      return this.lgK;
   }

   public static Family getFamily() {
      return Family.CPC;
   }

   long getNumCoupons() {
      return this.bitMatrix != null ? CpcUtil.countBitsSetInMatrix(this.bitMatrix) : this.accumulator.numCoupons;
   }

   static long[] getBitMatrix(CpcUnion union) {
      checkUnionState(union);
      return union.bitMatrix != null ? union.bitMatrix : CpcUtil.bitMatrixOfSketch(union.accumulator);
   }

   private static void walkTableUpdatingSketch(CpcSketch dest, PairTable table) {
      int[] slots = table.getSlotsArr();
      int numSlots = 1 << table.getLgSizeInts();

      assert dest.lgK <= 26;

      int destMask = (1 << dest.lgK) - 1 << 6 | 63;
      int stride = (int)(0.6180339887498949 * (double)numSlots);

      assert stride >= 2;

      if (stride == stride >>> 1 << 1) {
         ++stride;
      }

      assert stride >= 3 && stride < numSlots;

      int i = 0;

      int j;
      for(j = 0; i < numSlots; j += stride) {
         j &= numSlots - 1;
         int rowCol = slots[j];
         if (rowCol != -1) {
            dest.rowColUpdate(rowCol & destMask);
         }

         ++i;
      }

   }

   private static void orTableIntoMatrix(long[] bitMatrix, int destLgK, PairTable table) {
      int[] slots = table.getSlotsArr();
      int numSlots = 1 << table.getLgSizeInts();
      int destMask = (1 << destLgK) - 1;

      for(int i = 0; i < numSlots; ++i) {
         int rowCol = slots[i];
         if (rowCol != -1) {
            int col = rowCol & 63;
            int row = rowCol >>> 6;
            bitMatrix[row & destMask] |= 1L << col;
         }
      }

   }

   private static void orWindowIntoMatrix(long[] destMatrix, int destLgK, byte[] srcWindow, int srcOffset, int srcLgK) {
      assert destLgK <= srcLgK;

      int destMask = (1 << destLgK) - 1;
      int srcK = 1 << srcLgK;

      for(int srcRow = 0; srcRow < srcK; ++srcRow) {
         destMatrix[srcRow & destMask] |= ((long)srcWindow[srcRow] & 255L) << srcOffset;
      }

   }

   private static void orMatrixIntoMatrix(long[] destMatrix, int destLgK, long[] srcMatrix, int srcLgK) {
      assert destLgK <= srcLgK;

      int destMask = (1 << destLgK) - 1;
      int srcK = 1 << srcLgK;

      for(int srcRow = 0; srcRow < srcK; ++srcRow) {
         destMatrix[srcRow & destMask] |= srcMatrix[srcRow];
      }

   }

   private static void reduceUnionK(CpcUnion union, int newLgK) {
      assert newLgK < union.lgK;

      if (union.bitMatrix != null) {
         int newK = 1 << newLgK;
         long[] newMatrix = new long[newK];
         orMatrixIntoMatrix(newMatrix, newLgK, union.bitMatrix, union.lgK);
         union.bitMatrix = newMatrix;
         union.lgK = newLgK;
      } else {
         CpcSketch oldSketch = union.accumulator;
         if (oldSketch.numCoupons == 0L) {
            union.accumulator = new CpcSketch(newLgK, oldSketch.seed);
            union.lgK = newLgK;
            return;
         }

         CpcSketch newSketch = new CpcSketch(newLgK, oldSketch.seed);
         walkTableUpdatingSketch(newSketch, oldSketch.pairTable);
         Flavor finalNewFlavor = newSketch.getFlavor();

         assert finalNewFlavor != Flavor.EMPTY;

         if (finalNewFlavor == Flavor.SPARSE) {
            union.accumulator = newSketch;
            union.lgK = newLgK;
            return;
         }

         union.accumulator = null;
         union.bitMatrix = CpcUtil.bitMatrixOfSketch(newSketch);
         union.lgK = newLgK;
      }

   }

   private static void mergeInto(CpcUnion union, CpcSketch source) {
      if (source != null) {
         checkSeeds(union.seed, source.seed);
         int sourceFlavorOrd = source.getFlavor().ordinal();
         if (sourceFlavorOrd != 0) {
            checkUnionState(union);
            if (source.lgK < union.lgK) {
               reduceUnionK(union, source.lgK);
            }

            if (sourceFlavorOrd > 1 && union.accumulator != null) {
               union.bitMatrix = CpcUtil.bitMatrixOfSketch(union.accumulator);
               union.accumulator = null;
            }

            int state = sourceFlavorOrd - 1 << 1 | (union.bitMatrix != null ? 1 : 0);
            switch (state) {
               case 0:
                  if (union.accumulator == null) {
                     throw new SketchesStateException("union.accumulator can never be null here.");
                  }

                  if (union.accumulator.getFlavor() == Flavor.EMPTY && union.lgK == source.lgK) {
                     union.accumulator = source.copy();
                  } else {
                     walkTableUpdatingSketch(union.accumulator, source.pairTable);
                     if (union.accumulator.getFlavor().ordinal() > 1) {
                        union.bitMatrix = CpcUtil.bitMatrixOfSketch(union.accumulator);
                        union.accumulator = null;
                     }
                  }
                  break;
               case 1:
                  orTableIntoMatrix(union.bitMatrix, union.lgK, source.pairTable);
                  break;
               case 2:
               case 4:
               case 6:
               default:
                  throw new SketchesStateException("Illegal Union state: " + state);
               case 3:
               case 5:
                  orWindowIntoMatrix(union.bitMatrix, union.lgK, source.slidingWindow, source.windowOffset, source.lgK);
                  orTableIntoMatrix(union.bitMatrix, union.lgK, source.pairTable);
                  break;
               case 7:
                  long[] sourceMatrix = CpcUtil.bitMatrixOfSketch(source);
                  orMatrixIntoMatrix(union.bitMatrix, union.lgK, sourceMatrix, source.lgK);
            }

         }
      }
   }

   private static CpcSketch getResult(CpcUnion union) {
      checkUnionState(union);
      if (union.accumulator != null) {
         if (union.accumulator.numCoupons == 0L) {
            CpcSketch result = new CpcSketch(union.lgK, union.accumulator.seed);
            result.mergeFlag = true;
            return result;
         } else {
            assert Flavor.SPARSE == union.accumulator.getFlavor();

            CpcSketch result = union.accumulator.copy();
            result.mergeFlag = true;
            return result;
         }
      } else {
         long[] matrix = union.bitMatrix;
         int lgK = union.lgK;
         CpcSketch result = new CpcSketch(union.lgK, union.seed);
         long numCoupons = CpcUtil.countBitsSetInMatrix(matrix);
         result.numCoupons = numCoupons;
         Flavor flavor = CpcUtil.determineFlavor(lgK, numCoupons);

         assert flavor.ordinal() > Flavor.SPARSE.ordinal();

         int offset = CpcUtil.determineCorrectOffset(lgK, numCoupons);
         result.windowOffset = offset;
         int k = 1 << lgK;
         byte[] window = new byte[k];
         result.slidingWindow = window;
         int newTableLgSize = Math.max(lgK - 4, 2);
         PairTable table = new PairTable(newTableLgSize, 6 + lgK);
         result.pairTable = table;
         long maskForClearingWindow = ~(255L << offset);
         long maskForFlippingEarlyZone = (1L << offset) - 1L;
         long allSurprisesORed = 0L;

         for(int i = 0; i < k; ++i) {
            long pattern = matrix[i];
            window[i] = (byte)((int)(pattern >>> offset & 255L));
            pattern &= maskForClearingWindow;
            pattern ^= maskForFlippingEarlyZone;
            allSurprisesORed |= pattern;

            while(pattern != 0L) {
               int col = Long.numberOfTrailingZeros(pattern);
               pattern ^= 1L << col;
               int rowCol = i << 6 | col;
               boolean isNovel = PairTable.maybeInsert(table, rowCol);

               assert isNovel;
            }
         }

         result.fiCol = Long.numberOfTrailingZeros(allSurprisesORed);
         if (result.fiCol > offset) {
            result.fiCol = offset;
         }

         result.mergeFlag = true;
         return result;
      }
   }

   private static void checkSeeds(long seedA, long seedB) {
      if (seedA != seedB) {
         throw new SketchesArgumentException("Hash Seeds do not match.");
      }
   }

   private static void checkUnionState(CpcUnion union) {
      if (union == null) {
         throw new SketchesStateException("union cannot be null");
      } else {
         CpcSketch accumulator = union.accumulator;
         if (!(accumulator != null ^ union.bitMatrix != null)) {
            throw new SketchesStateException("accumulator and bitMatrix cannot be both valid or both null: accumValid = " + (accumulator != null) + ", bitMatrixValid = " + (union.bitMatrix != null));
         } else {
            if (accumulator != null) {
               if (accumulator.numCoupons > 0L && (accumulator.slidingWindow != null || accumulator.pairTable == null)) {
                  throw new SketchesStateException("Non-empty union accumulator must be SPARSE: " + accumulator.getFlavor());
               }

               if (union.lgK != accumulator.lgK) {
                  throw new SketchesStateException("union LgK must equal accumulator LgK");
               }
            }

         }
      }
   }
}
