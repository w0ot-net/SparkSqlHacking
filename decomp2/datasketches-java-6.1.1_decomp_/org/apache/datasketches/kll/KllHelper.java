package org.apache.datasketches.kll;

import java.nio.ByteOrder;
import java.util.Arrays;
import org.apache.datasketches.common.ArrayOfItemsSerDe;
import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.memory.WritableBuffer;
import org.apache.datasketches.memory.WritableMemory;

final class KllHelper {
   static final double EPS_DELTA_THRESHOLD = 1.0E-6;
   static final double MIN_EPS = 4.7634E-5;
   static final double PMF_COEF = 2.446;
   static final double PMF_EXP = 0.9433;
   static final double CDF_COEF = 2.296;
   static final double CDF_EXP = 0.9723;
   static long[] powersOfThree = new long[]{1L, 3L, 9L, 27L, 81L, 243L, 729L, 2187L, 6561L, 19683L, 59049L, 177147L, 531441L, 1594323L, 4782969L, 14348907L, 43046721L, 129140163L, 387420489L, 1162261467L, 3486784401L, 10460353203L, 31381059609L, 94143178827L, 282429536481L, 847288609443L, 2541865828329L, 7625597484987L, 22876792454961L, 68630377364883L, 205891132094649L};
   private static final boolean enablePrinting = false;

   static void checkK(int k, int m) {
      if (k < m || k > 65535) {
         throw new SketchesArgumentException("K must be >= " + m + " and <= " + '\uffff' + ": " + k);
      }
   }

   static void checkM(int m) {
      if (m < 2 || m > 8 || (m & 1) == 1) {
         throw new SketchesArgumentException("M must be >= 2, <= 8 and even: " + m);
      }
   }

   static int computeTotalItemCapacity(int k, int m, int numLevels) {
      long total = 0L;

      for(int level = 0; level < numLevels; ++level) {
         total += (long)levelCapacity(k, numLevels, level, m);
      }

      return (int)total;
   }

   public static long convertToCumulative(long[] array) {
      long subtotal = 0L;

      for(int i = 0; i < array.length; ++i) {
         long newSubtotal = subtotal + array[i];
         subtotal = array[i] = newSubtotal;
      }

      return subtotal;
   }

   static int[] createLevelsArray(long weight) {
      int numLevels = 64 - Long.numberOfLeadingZeros(weight);
      if (numLevels > 61) {
         throw new SketchesArgumentException("The requested weight must not exceed 2^61");
      } else {
         int[] levelsArr = new int[numLevels + 1];
         int itemsArrIndex = 0;
         levelsArr[0] = itemsArrIndex;

         for(int level = 0; level < numLevels; ++level) {
            levelsArr[level + 1] = itemsArrIndex += Util.bitAt(weight, level);
         }

         return levelsArr;
      }
   }

   static int currentLevelSizeItems(int level, int numLevels, int[] levels) {
      return level >= numLevels ? 0 : levels[level + 1] - levels[level];
   }

   static LevelStats getFinalSketchStatsAtNumLevels(int k, int m, int numLevels, boolean printSketchStructure) {
      int cumItems = 0;
      long cumN = 0L;
      if (printSketchStructure) {
         println("SKETCH STRUCTURE:");
         println("Given K        : " + k);
         println("Given M        : " + m);
         println("Given NumLevels: " + numLevels);
         printf("%6s %8s %12s %18s %18s" + Util.LS, "Level", "Items", "CumItems", "N at Level", "CumN");
      }

      for(int level = 0; level < numLevels; ++level) {
         int items = levelCapacity(k, numLevels, level, m);
         long n = (long)items << level;
         LevelStats lvlStats = new LevelStats(n, numLevels, items);
         cumItems += lvlStats.numItems;
         cumN += lvlStats.n;
         if (printSketchStructure) {
            printf("%6d %,8d %,12d %,18d %,18d" + Util.LS, level, lvlStats.numItems, cumItems, lvlStats.n, cumN);
         }
      }

      return new LevelStats(cumN, numLevels, cumItems);
   }

   static GrowthStats getGrowthSchemeForGivenN(int k, int m, long n, KllSketch.SketchType sketchType, boolean printGrowthScheme) {
      if (sketchType == KllSketch.SketchType.ITEMS_SKETCH) {
         throw new SketchesArgumentException("Unsupported operation for this Sketch Type. ");
      } else {
         GrowthStats gStats = new GrowthStats();
         gStats.numLevels = 0;
         gStats.k = k;
         gStats.m = m;
         gStats.givenN = n;
         gStats.sketchType = sketchType;
         if (printGrowthScheme) {
            println("GROWTH SCHEME:");
            println("Given SketchType: " + gStats.sketchType.toString());
            println("Given K         : " + gStats.k);
            println("Given M         : " + gStats.m);
            println("Given N         : " + gStats.givenN);
            printf("%10s %10s %20s %13s %15s" + Util.LS, "NumLevels", "MaxItems", "MaxN", "CompactBytes", "UpdatableBytes");
         }

         int typeBytes = sketchType.getBytes();

         LevelStats lvlStats;
         do {
            ++gStats.numLevels;
            lvlStats = getFinalSketchStatsAtNumLevels(gStats.k, gStats.m, gStats.numLevels, false);
            gStats.maxItems = lvlStats.numItems;
            gStats.maxN = lvlStats.n;
            gStats.compactBytes = gStats.maxItems * typeBytes + gStats.numLevels * 4 + 2 * typeBytes + 20;
            gStats.updatableBytes = gStats.compactBytes + 4;
            if (printGrowthScheme) {
               printf("%10d %,10d %,20d %,13d %,15d" + Util.LS, gStats.numLevels, gStats.maxItems, gStats.maxN, gStats.compactBytes, gStats.updatableBytes);
            }
         } while(lvlStats.n < n);

         return gStats;
      }
   }

   static int getKFromEpsilon(double epsilon, boolean pmf) {
      double eps = Math.max(epsilon, 4.7634E-5);
      double kdbl = pmf ? Math.exp(Math.log(2.446 / eps) / 0.9433) : Math.exp(Math.log(2.296 / eps) / 0.9723);
      double krnd = (double)Math.round(kdbl);
      double del = Math.abs(krnd - kdbl);
      int k = (int)(del < 1.0E-6 ? krnd : Math.ceil(kdbl));
      return Math.max(2, Math.min(65535, k));
   }

   static double getNormalizedRankError(int k, boolean pmf) {
      return pmf ? 2.446 / Math.pow((double)k, 0.9433) : 2.296 / Math.pow((double)k, 0.9723);
   }

   static int getNumRetainedAboveLevelZero(int numLevels, int[] levels) {
      return levels[numLevels] - levels[1];
   }

   static int levelCapacity(int k, int numLevels, int level, int m) {
      assert k <= 536870912 : "The given k is > 2^29.";

      assert numLevels >= 1 && numLevels <= 61 : "The given numLevels is < 1 or > 61";

      assert level >= 0 && level < numLevels : "The given level is < 0 or >= numLevels.";

      int depth = numLevels - level - 1;
      return (int)Math.max((long)m, intCapAux(k, depth));
   }

   static WritableMemory memorySpaceMgmt(KllSketch sketch, int newLevelsArrLen, int newItemsArrLen) {
      KllSketch.SketchType sketchType = sketch.sketchType;
      if (sketchType == KllSketch.SketchType.ITEMS_SKETCH) {
         throw new SketchesArgumentException("Unsupported operation for this Sketch Type. ");
      } else {
         WritableMemory wmem = sketch.getWritableMemory();
         if (wmem == null) {
            return null;
         } else {
            int typeBytes = sketchType.getBytes();
            int requiredSketchBytes = 20 + newLevelsArrLen * 4 + 2 * typeBytes + newItemsArrLen * typeBytes;
            WritableMemory newWmem;
            if ((long)requiredSketchBytes > wmem.getCapacity()) {
               newWmem = sketch.getMemoryRequestServer().request(wmem, (long)requiredSketchBytes);
               wmem.copyTo(0L, newWmem, 0L, 20L);
            } else {
               newWmem = wmem;
            }

            assert (long)requiredSketchBytes <= newWmem.getCapacity();

            return newWmem;
         }
      }
   }

   private static String outputDataDetail(KllSketch sketch) {
      int[] levelsArr = sketch.getLevelsArray(KllSketch.SketchStructure.UPDATABLE);
      int numLevels = sketch.getNumLevels();
      int k = sketch.getK();
      int m = sketch.getM();
      StringBuilder sb = new StringBuilder();
      sb.append(Util.LS + "### KLL ItemsArray & LevelsArray Detail:").append(Util.LS);
      sb.append("Index, Value").append(Util.LS);
      if (levelsArr[0] > 0) {
         String gbg = " Free Space, Size = " + levelsArr[0];

         for(int i = 0; i < levelsArr[0]; ++i) {
            sb.append("    ").append(i + ", ").append(sketch.getItemAsString(i));
            if (i == 0) {
               sb.append(gbg);
            }

            sb.append(Util.LS);
         }
      }

      int level;
      for(level = 0; level < numLevels; ++level) {
         int fromIndex = levelsArr[level];
         int toIndex = levelsArr[level + 1];
         String lvlData = "";
         if (fromIndex < toIndex) {
            lvlData = " Level[" + level + "]=" + levelsArr[level] + ", Cap=" + levelCapacity(k, numLevels, level, m) + ", Size=" + currentLevelSizeItems(level, numLevels, levelsArr) + ", Wt=" + (1 << level) + Util.LS;
         }

         for(int i = fromIndex; i < toIndex; ++i) {
            sb.append("    ").append(i + ", ").append(sketch.getItemAsString(i));
            if (i == fromIndex) {
               sb.append(lvlData);
            } else {
               sb.append(Util.LS);
            }
         }
      }

      sb.append("   ----------Level[" + level + "]=" + levelsArr[level] + ": ItemsArray[].length");
      sb.append(Util.LS);
      sb.append("### End ItemsArray & LevelsArray Detail").append(Util.LS);
      return sb.toString();
   }

   private static String outputLevels(int k, int m, int numLevels, int[] levelsArr) {
      StringBuilder sb = new StringBuilder();
      sb.append(Util.LS + "### KLL Levels Array:").append(Util.LS).append(" Level, Offset: Nominal Capacity, Actual Capacity").append(Util.LS);

      int level;
      for(level = 0; level < numLevels; ++level) {
         sb.append("     ").append(level).append(", ").append(levelsArr[level]).append(": ").append(levelCapacity(k, numLevels, level, m)).append(", ").append(currentLevelSizeItems(level, numLevels, levelsArr)).append(Util.LS);
      }

      sb.append("     ").append(level).append(", ").append(levelsArr[level]).append(": ----ItemsArray[].length").append(Util.LS);
      sb.append("### End Levels Array").append(Util.LS);
      return sb.toString();
   }

   static long sumTheSampleWeights(int num_levels, int[] levels) {
      long total = 0L;
      long weight = 1L;

      for(int i = 0; i < num_levels; ++i) {
         total += weight * (long)(levels[i + 1] - levels[i]);
         weight *= 2L;
      }

      return total;
   }

   static byte[] toByteArray(KllSketch srcSk, boolean updatable) {
      boolean myUpdatable = srcSk.sketchType == KllSketch.SketchType.ITEMS_SKETCH ? false : updatable;
      long srcN = srcSk.getN();
      KllSketch.SketchStructure tgtStructure;
      if (myUpdatable) {
         tgtStructure = KllSketch.SketchStructure.UPDATABLE;
      } else if (srcN == 0L) {
         tgtStructure = KllSketch.SketchStructure.COMPACT_EMPTY;
      } else if (srcN == 1L) {
         tgtStructure = KllSketch.SketchStructure.COMPACT_SINGLE;
      } else {
         tgtStructure = KllSketch.SketchStructure.COMPACT_FULL;
      }

      int totalBytes = srcSk.currentSerializedSizeBytes(myUpdatable);
      byte[] bytesOut = new byte[totalBytes];
      WritableBuffer wbuf = WritableMemory.writableWrap(bytesOut).asWritableBuffer(ByteOrder.LITTLE_ENDIAN);
      byte preInts = (byte)tgtStructure.getPreInts();
      byte serVer = (byte)tgtStructure.getSerVer();
      byte famId = (byte)Family.KLL.getID();
      byte flags = (byte)((srcSk.isEmpty() ? 1 : 0) | (srcSk.isLevelZeroSorted() ? 2 : 0) | (srcSk.getN() == 1L ? 4 : 0));
      short k = (short)srcSk.getK();
      byte m = (byte)srcSk.getM();
      wbuf.putByte(preInts);
      wbuf.putByte(serVer);
      wbuf.putByte(famId);
      wbuf.putByte(flags);
      wbuf.putShort(k);
      wbuf.putByte(m);
      wbuf.incrementPosition(1L);
      if (tgtStructure == KllSketch.SketchStructure.COMPACT_EMPTY) {
         return bytesOut;
      } else if (tgtStructure == KllSketch.SketchStructure.COMPACT_SINGLE) {
         byte[] siByteArr = srcSk.getSingleItemByteArr();
         int len = siByteArr.length;
         wbuf.putByteArray(siByteArr, 0, len);
         wbuf.incrementPosition((long)(-len));
         return bytesOut;
      } else {
         long n = srcSk.getN();
         short minK = (short)srcSk.getMinK();
         byte numLevels = (byte)srcSk.getNumLevels();
         int[] lvlsArr = srcSk.getLevelsArray(tgtStructure);
         byte[] minMaxByteArr = srcSk.getMinMaxByteArr();
         byte[] itemsByteArr = tgtStructure == KllSketch.SketchStructure.COMPACT_FULL ? srcSk.getRetainedItemsByteArr() : srcSk.getTotalItemsByteArr();
         wbuf.putLong(n);
         wbuf.putShort(minK);
         wbuf.putByte(numLevels);
         wbuf.incrementPosition(1L);
         wbuf.putIntArray(lvlsArr, 0, lvlsArr.length);
         wbuf.putByteArray(minMaxByteArr, 0, minMaxByteArr.length);
         wbuf.putByteArray(itemsByteArr, 0, itemsByteArr.length);
         return bytesOut;
      }
   }

   static String toStringImpl(KllSketch sketch, boolean withLevels, boolean withLevelsAndItems, ArrayOfItemsSerDe serDe) {
      StringBuilder sb = new StringBuilder();
      int k = sketch.getK();
      int m = sketch.getM();
      int numLevels = sketch.getNumLevels();
      int[] fullLevelsArr = sketch.getLevelsArray(KllSketch.SketchStructure.UPDATABLE);
      KllSketch.SketchType sketchType = sketch.sketchType;
      boolean hasMemory = sketch.hasMemory();
      long n = sketch.getN();
      String epsPct = String.format("%.3f%%", sketch.getNormalizedRankError(false) * (double)100.0F);
      String epsPMFPct = String.format("%.3f%%", sketch.getNormalizedRankError(true) * (double)100.0F);
      boolean compact = sketch.isCompactMemoryFormat();
      String directStr = hasMemory ? "Direct" : "";
      String compactStr = compact ? "Compact" : "";
      String readOnlyStr = sketch.isReadOnly() ? "true" + "(" + (compact ? "Format" : "Memory") + ")" : "false";
      String skTypeStr = sketchType.getName();
      String className = "Kll" + directStr + compactStr + skTypeStr;
      sb.append(Util.LS + "### ").append(className).append(" Summary:").append(Util.LS);
      sb.append("   K                      : ").append(k).append(Util.LS);
      sb.append("   Dynamic min K          : ").append(sketch.getMinK()).append(Util.LS);
      sb.append("   M                      : ").append(m).append(Util.LS);
      sb.append("   N                      : ").append(n).append(Util.LS);
      sb.append("   Epsilon                : ").append(epsPct).append(Util.LS);
      sb.append("   Epsilon PMF            : ").append(epsPMFPct).append(Util.LS);
      sb.append("   Empty                  : ").append(sketch.isEmpty()).append(Util.LS);
      sb.append("   Estimation Mode        : ").append(sketch.isEstimationMode()).append(Util.LS);
      sb.append("   Levels                 : ").append(numLevels).append(Util.LS);
      sb.append("   Level 0 Sorted         : ").append(sketch.isLevelZeroSorted()).append(Util.LS);
      sb.append("   Capacity Items         : ").append(fullLevelsArr[numLevels]).append(Util.LS);
      sb.append("   Retained Items         : ").append(sketch.getNumRetained()).append(Util.LS);
      sb.append("   Free Space             : ").append(sketch.levelsArr[0]).append(Util.LS);
      sb.append("   ReadOnly               : ").append(readOnlyStr).append(Util.LS);
      if (sketchType != KllSketch.SketchType.ITEMS_SKETCH) {
         sb.append("   Updatable Storage Bytes: ").append(sketch.currentSerializedSizeBytes(true)).append(Util.LS);
      }

      sb.append("   Compact Storage Bytes  : ").append(sketch.currentSerializedSizeBytes(false)).append(Util.LS);
      String emptyStr = sketchType == KllSketch.SketchType.ITEMS_SKETCH ? "Null" : "NaN";
      sb.append("   Min Item               : ").append(sketch.isEmpty() ? emptyStr : sketch.getMinItemAsString()).append(Util.LS);
      sb.append("   Max Item               : ").append(sketch.isEmpty() ? emptyStr : sketch.getMaxItemAsString()).append(Util.LS);
      sb.append("### End sketch summary").append(Util.LS);
      if (withLevels) {
         sb.append(outputLevels(k, m, numLevels, fullLevelsArr));
      }

      if (withLevelsAndItems) {
         sb.append(outputDataDetail(sketch));
      }

      return sb.toString();
   }

   static int ubOnNumLevels(long n) {
      return 1 + Long.numberOfTrailingZeros(Util.floorPowerOf2(n));
   }

   static void addEmptyTopLevelToCompletelyFullSketch(KllSketch sketch) {
      KllSketch.SketchType sketchType = sketch.sketchType;
      int[] myCurLevelsArr = sketch.getLevelsArray(sketch.sketchStructure);
      int myCurNumLevels = sketch.getNumLevels();
      int myCurTotalItemsCapacity = myCurLevelsArr[myCurNumLevels];
      double[] myCurDoubleItemsArr = null;
      double[] myNewDoubleItemsArr = null;
      double minDouble = Double.NaN;
      double maxDouble = Double.NaN;
      float[] myCurFloatItemsArr = null;
      float[] myNewFloatItemsArr = null;
      float minFloat = Float.NaN;
      float maxFloat = Float.NaN;
      long[] myCurLongItemsArr = null;
      long[] myNewLongItemsArr = null;
      long minLong = Long.MAX_VALUE;
      long maxLong = Long.MIN_VALUE;
      Object[] myCurItemsArr = null;
      Object[] myNewItemsArr = null;
      Object minItem = null;
      Object maxItem = null;
      if (sketchType == KllSketch.SketchType.DOUBLES_SKETCH) {
         KllDoublesSketch dblSk = (KllDoublesSketch)sketch;
         myCurDoubleItemsArr = dblSk.getDoubleItemsArray();
         minDouble = dblSk.getMinItem();
         maxDouble = dblSk.getMaxItem();

         assert myCurDoubleItemsArr.length == myCurTotalItemsCapacity;
      } else if (sketchType == KllSketch.SketchType.FLOATS_SKETCH) {
         KllFloatsSketch fltSk = (KllFloatsSketch)sketch;
         myCurFloatItemsArr = fltSk.getFloatItemsArray();
         minFloat = fltSk.getMinItem();
         maxFloat = fltSk.getMaxItem();

         assert myCurFloatItemsArr.length == myCurTotalItemsCapacity;
      } else if (sketchType == KllSketch.SketchType.LONGS_SKETCH) {
         KllLongsSketch lngSk = (KllLongsSketch)sketch;
         myCurLongItemsArr = lngSk.getLongItemsArray();
         minLong = lngSk.getMinItem();
         maxLong = lngSk.getMaxItem();

         assert myCurLongItemsArr.length == myCurTotalItemsCapacity;
      } else {
         KllItemsSketch<?> itmSk = (KllItemsSketch)sketch;
         myCurItemsArr = itmSk.getTotalItemsArray();
         minItem = itmSk.getMinItem();
         maxItem = itmSk.getMaxItem();
      }

      assert myCurLevelsArr[0] == 0;

      int deltaItemsCap = levelCapacity(sketch.getK(), myCurNumLevels + 1, 0, sketch.getM());
      int myNewTotalItemsCapacity = myCurTotalItemsCapacity + deltaItemsCap;
      boolean growLevelsArr = myCurLevelsArr.length < myCurNumLevels + 2;
      int myNewNumLevels;
      int[] myNewLevelsArr;
      if (growLevelsArr) {
         myNewLevelsArr = Arrays.copyOf(myCurLevelsArr, myCurNumLevels + 2);

         assert myNewLevelsArr.length == myCurLevelsArr.length + 1;

         myNewNumLevels = myCurNumLevels + 1;
         sketch.incNumLevels();
      } else {
         myNewLevelsArr = myCurLevelsArr;
         myNewNumLevels = myCurNumLevels;
      }

      for(int level = 0; level <= myNewNumLevels - 1; ++level) {
         myNewLevelsArr[level] += deltaItemsCap;
      }

      myNewLevelsArr[myNewNumLevels] = myNewTotalItemsCapacity;
      if (sketchType == KllSketch.SketchType.DOUBLES_SKETCH) {
         myNewDoubleItemsArr = new double[myNewTotalItemsCapacity];
         System.arraycopy(myCurDoubleItemsArr, 0, myNewDoubleItemsArr, deltaItemsCap, myCurTotalItemsCapacity);
      } else if (sketchType == KllSketch.SketchType.FLOATS_SKETCH) {
         myNewFloatItemsArr = new float[myNewTotalItemsCapacity];
         System.arraycopy(myCurFloatItemsArr, 0, myNewFloatItemsArr, deltaItemsCap, myCurTotalItemsCapacity);
      } else if (sketchType == KllSketch.SketchType.LONGS_SKETCH) {
         myNewLongItemsArr = new long[myNewTotalItemsCapacity];
         System.arraycopy(myCurLongItemsArr, 0, myNewLongItemsArr, deltaItemsCap, myCurTotalItemsCapacity);
      } else {
         myNewItemsArr = new Object[myNewTotalItemsCapacity];
         System.arraycopy(myCurItemsArr, 0, myNewItemsArr, deltaItemsCap, myCurTotalItemsCapacity);
      }

      if (sketch.getWritableMemory() != null) {
         WritableMemory wmem = memorySpaceMgmt(sketch, myNewLevelsArr.length, myNewTotalItemsCapacity);
         sketch.setWritableMemory(wmem);
      }

      sketch.setNumLevels(myNewNumLevels);
      sketch.setLevelsArray(myNewLevelsArr);
      if (sketchType == KllSketch.SketchType.DOUBLES_SKETCH) {
         KllDoublesSketch dblSk = (KllDoublesSketch)sketch;
         dblSk.setMinItem(minDouble);
         dblSk.setMaxItem(maxDouble);
         dblSk.setDoubleItemsArray(myNewDoubleItemsArr);
      } else if (sketchType == KllSketch.SketchType.FLOATS_SKETCH) {
         KllFloatsSketch fltSk = (KllFloatsSketch)sketch;
         fltSk.setMinItem(minFloat);
         fltSk.setMaxItem(maxFloat);
         fltSk.setFloatItemsArray(myNewFloatItemsArr);
      } else if (sketchType == KllSketch.SketchType.LONGS_SKETCH) {
         KllLongsSketch lngSk = (KllLongsSketch)sketch;
         lngSk.setMinItem(minLong);
         lngSk.setMaxItem(maxLong);
         lngSk.setLongItemsArray(myNewLongItemsArr);
      } else {
         KllItemsSketch<?> itmSk = (KllItemsSketch)sketch;
         itmSk.setMinItem(minItem);
         itmSk.setMaxItem(maxItem);
         itmSk.setItemsArray(myNewItemsArr);
      }

   }

   static int findLevelToCompact(int k, int m, int numLevels, int[] levels) {
      for(int level = 0; $assertionsDisabled || level < numLevels; ++level) {
         int pop = levels[level + 1] - levels[level];
         int cap = levelCapacity(k, numLevels, level, m);
         if (pop >= cap) {
            return level;
         }
      }

      throw new AssertionError();
   }

   static long intCapAux(int k, int depth) {
      if (depth <= 30) {
         return intCapAuxAux((long)k, depth);
      } else {
         int half = depth / 2;
         int rest = depth - half;
         long tmp = intCapAuxAux((long)k, half);
         return intCapAuxAux(tmp, rest);
      }
   }

   static long intCapAuxAux(long k, int depth) {
      long twok = k << 1;
      long tmp = (twok << depth) / powersOfThree[depth];
      long result = tmp + 1L >>> 1;

      assert result <= k;

      return result;
   }

   private static final void printf(String format, Object... args) {
   }

   private static final void println(Object o) {
   }

   static class GrowthStats {
      KllSketch.SketchType sketchType;
      int k;
      int m;
      long givenN;
      long maxN;
      int numLevels;
      int maxItems;
      int compactBytes;
      int updatableBytes;
   }

   static class LevelStats {
      long n;
      public int numLevels;
      int numItems;

      LevelStats(long n, int numLevels, int numItems) {
         this.n = n;
         this.numLevels = numLevels;
         this.numItems = numItems;
      }
   }
}
