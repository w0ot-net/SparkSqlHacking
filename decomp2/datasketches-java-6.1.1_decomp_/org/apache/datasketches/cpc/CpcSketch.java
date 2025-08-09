package org.apache.datasketches.cpc;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.hash.MurmurHash3;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;
import org.apache.datasketches.thetacommon.ThetaUtil;

public final class CpcSketch {
   private static final double[] kxpByteLookup = new double[256];
   public static final int DEFAULT_LG_K = 11;
   final long seed;
   final int lgK;
   long numCoupons;
   boolean mergeFlag;
   int fiCol;
   int windowOffset;
   byte[] slidingWindow;
   PairTable pairTable;
   double kxp;
   double hipEstAccum;
   private static final int empiricalSizeMaxLgK = 19;
   private static final int[] empiricalMaxBytes = new int[]{24, 36, 56, 100, 180, 344, 660, 1292, 2540, 5020, 9968, 19836, 39532, 78880, 157516, 314656};
   private static final double empiricalMaxSizeFactor = 0.6;
   private static final int maxPreambleSizeBytes = 40;

   public CpcSketch() {
      this(11, 9001L);
   }

   public CpcSketch(int lgK) {
      this(lgK, 9001L);
   }

   public CpcSketch(int lgK, long seed) {
      CpcUtil.checkLgK(lgK);
      this.lgK = (byte)lgK;
      this.seed = seed;
      this.kxp = (double)(1 << lgK);
      this.reset();
   }

   CpcSketch copy() {
      CpcSketch copy = new CpcSketch(this.lgK, this.seed);
      copy.numCoupons = this.numCoupons;
      copy.mergeFlag = this.mergeFlag;
      copy.fiCol = this.fiCol;
      copy.windowOffset = this.windowOffset;
      copy.slidingWindow = this.slidingWindow == null ? null : (byte[])this.slidingWindow.clone();
      copy.pairTable = this.pairTable == null ? null : this.pairTable.copy();
      copy.kxp = this.kxp;
      copy.hipEstAccum = this.hipEstAccum;
      return copy;
   }

   public double getEstimate() {
      return this.mergeFlag ? IconEstimator.getIconEstimate(this.lgK, this.numCoupons) : this.hipEstAccum;
   }

   public static Family getFamily() {
      return Family.CPC;
   }

   public int getLgK() {
      return this.lgK;
   }

   public double getLowerBound(int kappa) {
      return this.mergeFlag ? CpcConfidence.getIconConfidenceLB(this.lgK, this.numCoupons, kappa) : CpcConfidence.getHipConfidenceLB(this.lgK, this.numCoupons, this.hipEstAccum, kappa);
   }

   public static int getMaxSerializedBytes(int lgK) {
      CpcUtil.checkLgK(lgK);
      if (lgK <= 19) {
         return empiricalMaxBytes[lgK - 4] + 40;
      } else {
         int k = 1 << lgK;
         return (int)(0.6 * (double)k) + 40;
      }
   }

   public double getUpperBound(int kappa) {
      return this.mergeFlag ? CpcConfidence.getIconConfidenceUB(this.lgK, this.numCoupons, kappa) : CpcConfidence.getHipConfidenceUB(this.lgK, this.numCoupons, this.hipEstAccum, kappa);
   }

   public static CpcSketch heapify(Memory mem) {
      return heapify(mem, 9001L);
   }

   public static CpcSketch heapify(byte[] byteArray) {
      return heapify(byteArray, 9001L);
   }

   public static CpcSketch heapify(Memory mem, long seed) {
      CompressedState state = CompressedState.importFromMemory(mem);
      return uncompress(state, seed);
   }

   public static CpcSketch heapify(byte[] byteArray, long seed) {
      Memory mem = Memory.wrap(byteArray);
      return heapify(mem, seed);
   }

   public boolean isEmpty() {
      return this.numCoupons == 0L;
   }

   public final void reset() {
      this.numCoupons = 0L;
      this.mergeFlag = false;
      this.fiCol = 0;
      this.windowOffset = 0;
      this.slidingWindow = null;
      this.pairTable = null;
      this.kxp = (double)(1 << this.lgK);
      this.hipEstAccum = (double)0.0F;
   }

   public byte[] toByteArray() {
      CompressedState state = CompressedState.compress(this);
      long cap = state.getRequiredSerializedBytes();
      WritableMemory wmem = WritableMemory.allocate((int)cap);
      state.exportToMemory(wmem);
      return (byte[])wmem.getArray();
   }

   public void update(long datum) {
      long[] data = new long[]{datum};
      long[] arr = MurmurHash3.hash(data, this.seed);
      this.hashUpdate(arr[0], arr[1]);
   }

   public void update(double datum) {
      double d = datum == (double)0.0F ? (double)0.0F : datum;
      long[] data = new long[]{Double.doubleToLongBits(d)};
      long[] arr = MurmurHash3.hash(data, this.seed);
      this.hashUpdate(arr[0], arr[1]);
   }

   public void update(String datum) {
      if (datum != null && !datum.isEmpty()) {
         byte[] data = datum.getBytes(StandardCharsets.UTF_8);
         long[] arr = MurmurHash3.hash(data, this.seed);
         this.hashUpdate(arr[0], arr[1]);
      }
   }

   public void update(byte[] data) {
      if (data != null && data.length != 0) {
         long[] arr = MurmurHash3.hash(data, this.seed);
         this.hashUpdate(arr[0], arr[1]);
      }
   }

   public void update(ByteBuffer data) {
      if (data != null && data.hasRemaining()) {
         long[] arr = MurmurHash3.hash(data, this.seed);
         this.hashUpdate(arr[0], arr[1]);
      }
   }

   public void update(char[] data) {
      if (data != null && data.length != 0) {
         long[] arr = MurmurHash3.hash(data, this.seed);
         this.hashUpdate(arr[0], arr[1]);
      }
   }

   public void update(int[] data) {
      if (data != null && data.length != 0) {
         long[] arr = MurmurHash3.hash(data, this.seed);
         this.hashUpdate(arr[0], arr[1]);
      }
   }

   public void update(long[] data) {
      if (data != null && data.length != 0) {
         long[] arr = MurmurHash3.hash(data, this.seed);
         this.hashUpdate(arr[0], arr[1]);
      }
   }

   public boolean validate() {
      long[] bitMatrix = CpcUtil.bitMatrixOfSketch(this);
      long matrixCoupons = CpcUtil.countBitsSetInMatrix(bitMatrix);
      return matrixCoupons == this.numCoupons;
   }

   Flavor getFlavor() {
      return CpcUtil.determineFlavor(this.lgK, this.numCoupons);
   }

   Format getFormat() {
      Flavor f = this.getFlavor();
      int ordinal;
      if (f != Flavor.HYBRID && f != Flavor.SPARSE) {
         ordinal = (this.slidingWindow != null ? 4 : 0) | (this.pairTable != null && this.pairTable.getNumPairs() > 0 ? 2 : 0) | (this.mergeFlag ? 0 : 1);
      } else {
         ordinal = 2 | (this.mergeFlag ? 0 : 1);
      }

      return Format.ordinalToFormat(ordinal);
   }

   private static void promoteEmptyToSparse(CpcSketch sketch) {
      assert sketch.numCoupons == 0L;

      assert sketch.pairTable == null;

      sketch.pairTable = new PairTable(2, 6 + sketch.lgK);
   }

   private static void promoteSparseToWindowed(CpcSketch sketch) {
      int lgK = sketch.lgK;
      int k = 1 << lgK;
      long c32 = sketch.numCoupons << 5;

      assert c32 == (long)(3 * k) || lgK == 4 && c32 > (long)(3 * k);

      byte[] window = new byte[k];
      PairTable newTable = new PairTable(2, 6 + lgK);
      PairTable oldTable = sketch.pairTable;
      int[] oldSlots = oldTable.getSlotsArr();
      int oldNumSlots = 1 << oldTable.getLgSizeInts();

      assert sketch.windowOffset == 0;

      for(int i = 0; i < oldNumSlots; ++i) {
         int rowCol = oldSlots[i];
         if (rowCol != -1) {
            int col = rowCol & 63;
            if (col < 8) {
               int row = rowCol >>> 6;
               window[row] = (byte)(window[row] | 1 << col);
            } else {
               boolean isNovel = PairTable.maybeInsert(newTable, rowCol);

               assert isNovel;
            }
         }
      }

      assert sketch.slidingWindow == null;

      sketch.slidingWindow = window;
      sketch.pairTable = newTable;
   }

   static void refreshKXP(CpcSketch sketch, long[] bitMatrix) {
      int k = 1 << sketch.lgK;
      double[] byteSums = new double[8];
      Arrays.fill(byteSums, (double)0.0F);

      for(int i = 0; i < k; ++i) {
         long row = bitMatrix[i];

         for(int j = 0; j < 8; ++j) {
            int byteIdx = (int)(row & 255L);
            byteSums[j] += kxpByteLookup[byteIdx];
            row >>>= 8;
         }
      }

      double total = (double)0.0F;

      double factor;
      for(int j = 7; j-- > 0; total += factor * byteSums[j]) {
         factor = Util.invPow2(8 * j);
      }

      sketch.kxp = total;
   }

   private static void modifyOffset(CpcSketch sketch, int newOffset) {
      assert newOffset >= 0 && newOffset <= 56;

      assert newOffset == sketch.windowOffset + 1;

      assert newOffset == CpcUtil.determineCorrectOffset(sketch.lgK, sketch.numCoupons);

      assert sketch.slidingWindow != null;

      assert sketch.pairTable != null;

      int k = 1 << sketch.lgK;
      long[] bitMatrix = CpcUtil.bitMatrixOfSketch(sketch);
      if ((newOffset & 7) == 0) {
         refreshKXP(sketch, bitMatrix);
      }

      sketch.pairTable.clear();
      PairTable table = sketch.pairTable;
      byte[] window = sketch.slidingWindow;
      long maskForClearingWindow = ~(255L << newOffset);
      long maskForFlippingEarlyZone = (1L << newOffset) - 1L;
      long allSurprisesORed = 0L;

      for(int i = 0; i < k; ++i) {
         long pattern = bitMatrix[i];
         window[i] = (byte)((int)(pattern >>> newOffset & 255L));
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

      sketch.windowOffset = newOffset;
      sketch.fiCol = Long.numberOfTrailingZeros(allSurprisesORed);
      if (sketch.fiCol > newOffset) {
         sketch.fiCol = newOffset;
      }

   }

   private static void updateHIP(CpcSketch sketch, int rowCol) {
      int k = 1 << sketch.lgK;
      int col = rowCol & 63;
      double oneOverP = (double)k / sketch.kxp;
      sketch.hipEstAccum += oneOverP;
      sketch.kxp -= Util.invPow2(col + 1);
   }

   private static void updateSparse(CpcSketch sketch, int rowCol) {
      int k = 1 << sketch.lgK;
      long c32pre = sketch.numCoupons << 5;

      assert c32pre < 3L * (long)k;

      assert sketch.pairTable != null;

      boolean isNovel = PairTable.maybeInsert(sketch.pairTable, rowCol);
      if (isNovel) {
         ++sketch.numCoupons;
         updateHIP(sketch, rowCol);
         long c32post = sketch.numCoupons << 5;
         if (c32post >= 3L * (long)k) {
            promoteSparseToWindowed(sketch);
         }
      }

   }

   private static void updateWindowed(CpcSketch sketch, int rowCol) {
      assert sketch.windowOffset >= 0 && sketch.windowOffset <= 56;

      int k = 1 << sketch.lgK;
      long c32pre = sketch.numCoupons << 5;

      assert c32pre >= 3L * (long)k;

      long c8pre = sketch.numCoupons << 3;
      int w8pre = sketch.windowOffset << 3;

      assert c8pre < (27L + (long)w8pre) * (long)k;

      boolean isNovel = false;
      int col = rowCol & 63;
      if (col < sketch.windowOffset) {
         isNovel = PairTable.maybeDelete(sketch.pairTable, rowCol);
      } else if (col < sketch.windowOffset + 8) {
         assert col >= sketch.windowOffset;

         int row = rowCol >>> 6;
         byte oldBits = sketch.slidingWindow[row];
         byte newBits = (byte)(oldBits | 1 << col - sketch.windowOffset);
         if (newBits != oldBits) {
            sketch.slidingWindow[row] = newBits;
            isNovel = true;
         }
      } else {
         assert col >= sketch.windowOffset + 8;

         isNovel = PairTable.maybeInsert(sketch.pairTable, rowCol);
      }

      if (isNovel) {
         ++sketch.numCoupons;
         updateHIP(sketch, rowCol);
         long c8post = sketch.numCoupons << 3;
         if (c8post >= (27L + (long)w8pre) * (long)k) {
            modifyOffset(sketch, sketch.windowOffset + 1);

            assert sketch.windowOffset >= 1 && sketch.windowOffset <= 56;

            int w8post = sketch.windowOffset << 3;

            assert c8post < (27L + (long)w8post) * (long)k;
         }
      }

   }

   static CpcSketch uncompress(CompressedState source, long seed) {
      ThetaUtil.checkSeedHashes(ThetaUtil.computeSeedHash(seed), source.seedHash);
      CpcSketch sketch = new CpcSketch(source.lgK, seed);
      sketch.numCoupons = source.numCoupons;
      sketch.windowOffset = source.getWindowOffset();
      sketch.fiCol = source.fiCol;
      sketch.mergeFlag = source.mergeFlag;
      sketch.kxp = source.kxp;
      sketch.hipEstAccum = source.hipEstAccum;
      sketch.slidingWindow = null;
      sketch.pairTable = null;
      CpcCompression.uncompress(source, sketch);
      return sketch;
   }

   void hashUpdate(long hash0, long hash1) {
      int col = Long.numberOfLeadingZeros(hash1);
      if (col >= this.fiCol) {
         if (col > 63) {
            col = 63;
         }

         long c = this.numCoupons;
         if (c == 0L) {
            promoteEmptyToSparse(this);
         }

         long k = 1L << this.lgK;
         int row = (int)(hash0 & k - 1L);
         int rowCol = row << 6 | col;
         if (rowCol == -1) {
            rowCol ^= 64;
         }

         if (c << 5 < 3L * k) {
            updateSparse(this, rowCol);
         } else {
            updateWindowed(this, rowCol);
         }

      }
   }

   void rowColUpdate(int rowCol) {
      int col = rowCol & 63;
      if (col >= this.fiCol) {
         long c = this.numCoupons;
         if (c == 0L) {
            promoteEmptyToSparse(this);
         }

         long k = 1L << this.lgK;
         if (c << 5 < 3L * k) {
            updateSparse(this, rowCol);
         } else {
            updateWindowed(this, rowCol);
         }

      }
   }

   public String toString() {
      return this.toString(false);
   }

   public String toString(boolean detail) {
      int numPairs = this.pairTable == null ? 0 : this.pairTable.getNumPairs();
      int seedHash = Short.toUnsignedInt(ThetaUtil.computeSeedHash(this.seed));
      double errConst = this.mergeFlag ? Math.log((double)2.0F) : Math.sqrt(Math.log((double)2.0F) / (double)2.0F);
      double rse = errConst / Math.sqrt((double)(1 << this.lgK));
      StringBuilder sb = new StringBuilder();
      sb.append("### CPD SKETCH - PREAMBLE:").append(Util.LS);
      sb.append("  Flavor         : ").append(this.getFlavor()).append(Util.LS);
      sb.append("  LgK            : ").append(this.lgK).append(Util.LS);
      sb.append("  Merge Flag     : ").append(this.mergeFlag).append(Util.LS);
      sb.append("  Error Const    : ").append(errConst).append(Util.LS);
      sb.append("  RSE            : ").append(rse).append(Util.LS);
      sb.append("  Seed Hash      : ").append(Integer.toHexString(seedHash)).append(" | ").append(seedHash).append(Util.LS);
      sb.append("  Num Coupons    : ").append(this.numCoupons).append(Util.LS);
      sb.append("  Num Pairs (SV) : ").append(numPairs).append(Util.LS);
      sb.append("  First Inter Col: ").append(this.fiCol).append(Util.LS);
      sb.append("  Valid Window   : ").append(this.slidingWindow != null).append(Util.LS);
      sb.append("  Valid PairTable: ").append(this.pairTable != null).append(Util.LS);
      sb.append("  Window Offset  : ").append(this.windowOffset).append(Util.LS);
      sb.append("  KxP            : ").append(this.kxp).append(Util.LS);
      sb.append("  HIP Accum      : ").append(this.hipEstAccum).append(Util.LS);
      if (detail) {
         sb.append(Util.LS).append("### CPC SKETCH - DATA").append(Util.LS);
         if (this.pairTable != null) {
            sb.append(this.pairTable.toString(true));
         }

         if (this.slidingWindow != null) {
            sb.append("SlidingWindow  : ").append(Util.LS);
            sb.append("    Index Bits (lsb ->)").append(Util.LS);

            for(int i = 0; i < this.slidingWindow.length; ++i) {
               String bits = Util.zeroPad(Integer.toBinaryString(this.slidingWindow[i] & 255), 8);
               sb.append(String.format("%9d %8s" + Util.LS, i, bits));
            }
         }
      }

      sb.append("### END CPC SKETCH");
      return sb.toString();
   }

   public static String toString(byte[] byteArr, boolean detail) {
      return PreambleUtil.toString(byteArr, detail);
   }

   public static String toString(Memory mem, boolean detail) {
      return PreambleUtil.toString(mem, detail);
   }

   private static void fillKxpByteLookup() {
      for(int b = 0; b < 256; ++b) {
         double sum = (double)0.0F;

         for(int col = 0; col < 8; ++col) {
            int bit = b >>> col & 1;
            if (bit == 0) {
               sum += Util.invPow2(col + 1);
            }
         }

         kxpByteLookup[b] = sum;
      }

   }

   static {
      fillKxpByteLookup();
   }
}
