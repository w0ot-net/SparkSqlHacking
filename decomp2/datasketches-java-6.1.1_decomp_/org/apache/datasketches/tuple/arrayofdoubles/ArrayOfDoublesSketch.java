package org.apache.datasketches.tuple.arrayofdoubles;

import org.apache.datasketches.common.Util;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;
import org.apache.datasketches.thetacommon.BinomialBoundsN;
import org.apache.datasketches.tuple.SerializerDeserializer;

public abstract class ArrayOfDoublesSketch {
   static final int SIZE_OF_KEY_BYTES = 8;
   static final int SIZE_OF_VALUE_BYTES = 8;
   static final int PREAMBLE_LONGS_BYTE = 0;
   static final int SERIAL_VERSION_BYTE = 1;
   static final int FAMILY_ID_BYTE = 2;
   static final int SKETCH_TYPE_BYTE = 3;
   static final int FLAGS_BYTE = 4;
   static final int NUM_VALUES_BYTE = 5;
   static final int SEED_HASH_SHORT = 6;
   static final int THETA_LONG = 8;
   final int numValues_;
   long thetaLong_;
   boolean isEmpty_ = true;

   ArrayOfDoublesSketch(int numValues) {
      this.numValues_ = numValues;
   }

   public static ArrayOfDoublesSketch heapify(Memory mem) {
      return heapify(mem, 9001L);
   }

   public static ArrayOfDoublesSketch heapify(Memory mem, long seed) {
      SerializerDeserializer.SketchType sketchType = SerializerDeserializer.getSketchType(mem);
      return (ArrayOfDoublesSketch)(sketchType == SerializerDeserializer.SketchType.ArrayOfDoublesQuickSelectSketch ? new HeapArrayOfDoublesQuickSelectSketch(mem, seed) : new HeapArrayOfDoublesCompactSketch(mem, seed));
   }

   public static ArrayOfDoublesSketch wrap(Memory mem) {
      return wrap(mem, 9001L);
   }

   public static ArrayOfDoublesSketch wrap(Memory mem, long seed) {
      SerializerDeserializer.SketchType sketchType = SerializerDeserializer.getSketchType(mem);
      return (ArrayOfDoublesSketch)(sketchType == SerializerDeserializer.SketchType.ArrayOfDoublesQuickSelectSketch ? new DirectArrayOfDoublesQuickSelectSketchR(mem, seed) : new DirectArrayOfDoublesCompactSketch(mem, seed));
   }

   public double getEstimate() {
      return !this.isEstimationMode() ? (double)this.getRetainedEntries() : (double)this.getRetainedEntries() / this.getTheta();
   }

   public double getUpperBound(int numStdDev) {
      return !this.isEstimationMode() ? (double)this.getRetainedEntries() : BinomialBoundsN.getUpperBound((long)this.getRetainedEntries(), this.getTheta(), numStdDev, this.isEmpty_);
   }

   public double getLowerBound(int numStdDev) {
      return !this.isEstimationMode() ? (double)this.getRetainedEntries() : BinomialBoundsN.getLowerBound((long)this.getRetainedEntries(), this.getTheta(), numStdDev, this.isEmpty_);
   }

   public abstract boolean hasMemory();

   abstract Memory getMemory();

   public boolean isEmpty() {
      return this.isEmpty_;
   }

   public int getNumValues() {
      return this.numValues_;
   }

   public boolean isEstimationMode() {
      return this.thetaLong_ < Long.MAX_VALUE && !this.isEmpty();
   }

   public double getTheta() {
      return (double)this.getThetaLong() / (double)Long.MAX_VALUE;
   }

   public abstract int getRetainedEntries();

   public abstract int getMaxBytes();

   public abstract int getCurrentBytes();

   public abstract byte[] toByteArray();

   public abstract double[][] getValues();

   abstract double[] getValuesAsOneDimension();

   abstract long[] getKeys();

   long getThetaLong() {
      return this.isEmpty() ? Long.MAX_VALUE : this.thetaLong_;
   }

   abstract short getSeedHash();

   public abstract ArrayOfDoublesSketchIterator iterator();

   public ArrayOfDoublesCompactSketch compact() {
      return this.compact((WritableMemory)null);
   }

   public abstract ArrayOfDoublesCompactSketch compact(WritableMemory var1);

   public String toString() {
      int seedHash = Short.toUnsignedInt(this.getSeedHash());
      StringBuilder sb = new StringBuilder();
      sb.append("### ").append(this.getClass().getSimpleName()).append(" SUMMARY: ").append(Util.LS);
      sb.append("   Estimate                : ").append(this.getEstimate()).append(Util.LS);
      sb.append("   Upper Bound, 95% conf   : ").append(this.getUpperBound(2)).append(Util.LS);
      sb.append("   Lower Bound, 95% conf   : ").append(this.getLowerBound(2)).append(Util.LS);
      sb.append("   Theta (double)          : ").append(this.getTheta()).append(Util.LS);
      sb.append("   Theta (long)            : ").append(this.getThetaLong()).append(Util.LS);
      sb.append("   EstMode?                : ").append(this.isEstimationMode()).append(Util.LS);
      sb.append("   Empty?                  : ").append(this.isEmpty()).append(Util.LS);
      sb.append("   Retained Entries        : ").append(this.getRetainedEntries()).append(Util.LS);
      if (this instanceof ArrayOfDoublesUpdatableSketch) {
         ArrayOfDoublesUpdatableSketch updatable = (ArrayOfDoublesUpdatableSketch)this;
         sb.append("   Nominal Entries (k)     : ").append(updatable.getNominalEntries()).append(Util.LS);
         sb.append("   Current Capacity        : ").append(updatable.getCurrentCapacity()).append(Util.LS);
         sb.append("   Resize Factor           : ").append(updatable.getResizeFactor().getValue()).append(Util.LS);
         sb.append("   Sampling Probability (p): ").append(updatable.getSamplingProbability()).append(Util.LS);
      }

      sb.append("   Seed Hash               : ").append(Integer.toHexString(seedHash)).append(" | ").append(seedHash).append(Util.LS);
      sb.append("### END SKETCH SUMMARY").append(Util.LS);
      return sb.toString();
   }

   static enum Flags {
      IS_BIG_ENDIAN,
      IS_IN_SAMPLING_MODE,
      IS_EMPTY,
      HAS_ENTRIES;
   }
}
