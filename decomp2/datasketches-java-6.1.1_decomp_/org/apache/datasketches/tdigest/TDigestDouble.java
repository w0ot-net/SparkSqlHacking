package org.apache.datasketches.tdigest;

import java.nio.ByteOrder;
import java.util.Arrays;
import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.SketchesStateException;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.memory.Buffer;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableBuffer;
import org.apache.datasketches.memory.WritableMemory;

public final class TDigestDouble {
   public static final short DEFAULT_K = 200;
   private boolean reverseMerge_;
   private final short k_;
   private double minValue_;
   private double maxValue_;
   private int centroidsCapacity_;
   private int numCentroids_;
   private double[] centroidMeans_;
   private long[] centroidWeights_;
   private long centroidsWeight_;
   private int numBuffered_;
   private double[] bufferValues_;
   private static final int BUFFER_MULTIPLIER = 4;
   private static final byte PREAMBLE_LONGS_EMPTY_OR_SINGLE = 1;
   private static final byte PREAMBLE_LONGS_MULTIPLE = 2;
   private static final byte SERIAL_VERSION = 1;
   private static final int COMPAT_DOUBLE = 1;
   private static final int COMPAT_FLOAT = 2;

   public TDigestDouble() {
      this((short)200);
   }

   public TDigestDouble(short k) {
      this(false, k, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, (double[])null, (long[])null, 0L, (double[])null);
   }

   public short getK() {
      return this.k_;
   }

   public void update(double value) {
      if (!Double.isNaN(value)) {
         if (this.numBuffered_ == this.centroidsCapacity_ * 4) {
            this.compress();
         }

         this.bufferValues_[this.numBuffered_] = value;
         ++this.numBuffered_;
         this.minValue_ = Math.min(this.minValue_, value);
         this.maxValue_ = Math.max(this.maxValue_, value);
      }
   }

   public void merge(TDigestDouble other) {
      if (!other.isEmpty()) {
         int num = this.numCentroids_ + this.numBuffered_ + other.numCentroids_ + other.numBuffered_;
         double[] values = new double[num];
         long[] weights = new long[num];
         System.arraycopy(this.bufferValues_, 0, values, 0, this.numBuffered_);
         Arrays.fill(weights, 0, this.numBuffered_, 1L);
         System.arraycopy(other.bufferValues_, 0, values, this.numBuffered_, other.numBuffered_);
         Arrays.fill(weights, this.numBuffered_, this.numBuffered_ + other.numBuffered_, 1L);
         System.arraycopy(other.centroidMeans_, 0, values, this.numBuffered_ + other.numBuffered_, other.numCentroids_);
         System.arraycopy(other.centroidWeights_, 0, weights, this.numBuffered_ + other.numBuffered_, other.numCentroids_);
         this.merge(values, weights, (long)this.numBuffered_ + other.getTotalWeight(), this.numBuffered_ + other.numBuffered_ + other.numCentroids_);
      }
   }

   public void compress() {
      if (this.numBuffered_ != 0) {
         int num = this.numBuffered_ + this.numCentroids_;
         double[] values = new double[num];
         long[] weights = new long[num];
         System.arraycopy(this.bufferValues_, 0, values, 0, this.numBuffered_);
         Arrays.fill(weights, 0, this.numBuffered_, 1L);
         this.merge(values, weights, (long)this.numBuffered_, this.numBuffered_);
      }
   }

   public boolean isEmpty() {
      return this.numCentroids_ == 0 && this.numBuffered_ == 0;
   }

   public double getMinValue() {
      if (this.isEmpty()) {
         throw new SketchesStateException("The sketch must not be empty for this operation. ");
      } else {
         return this.minValue_;
      }
   }

   public double getMaxValue() {
      if (this.isEmpty()) {
         throw new SketchesStateException("The sketch must not be empty for this operation. ");
      } else {
         return this.maxValue_;
      }
   }

   public long getTotalWeight() {
      return this.centroidsWeight_ + (long)this.numBuffered_;
   }

   public double getRank(double value) {
      if (this.isEmpty()) {
         throw new SketchesStateException("The sketch must not be empty for this operation. ");
      } else if (Double.isNaN(value)) {
         throw new SketchesArgumentException("Operation is undefined for Nan");
      } else if (value < this.minValue_) {
         return (double)0.0F;
      } else if (value > this.maxValue_) {
         return (double)1.0F;
      } else if (this.numCentroids_ + this.numBuffered_ == 1) {
         return (double)0.5F;
      } else {
         this.compress();
         double firstMean = this.centroidMeans_[0];
         if (value < firstMean) {
            if (firstMean - this.minValue_ > (double)0.0F) {
               return value == this.minValue_ ? (double)0.5F / (double)this.centroidsWeight_ : (double)1.0F + (value - this.minValue_) / (firstMean - this.minValue_) * ((double)this.centroidWeights_[0] / (double)2.0F - (double)1.0F);
            } else {
               return (double)0.0F;
            }
         } else {
            double lastMean = this.centroidMeans_[this.numCentroids_ - 1];
            if (value > lastMean) {
               if (this.maxValue_ - lastMean > (double)0.0F) {
                  return value == this.maxValue_ ? (double)1.0F - (double)0.5F / (double)this.centroidsWeight_ : (double)1.0F - ((double)1.0F + (this.maxValue_ - value) / (this.maxValue_ - lastMean) * ((double)this.centroidWeights_[this.numCentroids_ - 1] / (double)2.0F - (double)1.0F)) / (double)this.centroidsWeight_;
               } else {
                  return (double)1.0F;
               }
            } else {
               int lower = BinarySearch.lowerBound(this.centroidMeans_, 0, this.numCentroids_, value);
               if (lower == this.numCentroids_) {
                  throw new SketchesStateException("lower == end in getRank()");
               } else {
                  int upper = BinarySearch.upperBound(this.centroidMeans_, lower, this.numCentroids_, value);
                  if (upper == 0) {
                     throw new SketchesStateException("upper == begin in getRank()");
                  } else {
                     if (value < this.centroidMeans_[lower]) {
                        --lower;
                     }

                     if (upper == this.numCentroids_ || !(this.centroidMeans_[upper - 1] < value)) {
                        --upper;
                     }

                     double weightBelow = (double)0.0F;

                     int i;
                     for(i = 0; i != lower; weightBelow += (double)this.centroidWeights_[i++]) {
                     }

                     weightBelow += (double)this.centroidWeights_[lower] / (double)2.0F;

                     double weightDelta;
                     for(weightDelta = (double)0.0F; i != upper; weightDelta += (double)this.centroidWeights_[i++]) {
                     }

                     weightDelta -= (double)this.centroidWeights_[lower] / (double)2.0F;
                     weightDelta += (double)this.centroidWeights_[upper] / (double)2.0F;
                     return this.centroidMeans_[upper] - this.centroidMeans_[lower] > (double)0.0F ? (weightBelow + weightDelta * (value - this.centroidMeans_[lower]) / (this.centroidMeans_[upper] - this.centroidMeans_[lower])) / (double)this.centroidsWeight_ : (weightBelow + weightDelta / (double)2.0F) / (double)this.centroidsWeight_;
                  }
               }
            }
         }
      }
   }

   public double getQuantile(double rank) {
      if (this.isEmpty()) {
         throw new SketchesStateException("The sketch must not be empty for this operation. ");
      } else if (Double.isNaN(rank)) {
         throw new SketchesArgumentException("Operation is undefined for Nan");
      } else if (!(rank < (double)0.0F) && !(rank > (double)1.0F)) {
         this.compress();
         if (this.numCentroids_ == 1) {
            return this.centroidMeans_[0];
         } else {
            double weight = rank * (double)this.centroidsWeight_;
            if (weight < (double)1.0F) {
               return this.minValue_;
            } else if (weight > (double)this.centroidsWeight_ - (double)1.0F) {
               return this.maxValue_;
            } else {
               double firstWeight = (double)this.centroidWeights_[0];
               if (firstWeight > (double)1.0F && weight < firstWeight / (double)2.0F) {
                  return this.minValue_ + (weight - (double)1.0F) / (firstWeight / (double)2.0F - (double)1.0F) * (this.centroidMeans_[0] - this.minValue_);
               } else {
                  double lastWeight = (double)this.centroidWeights_[this.numCentroids_ - 1];
                  if (lastWeight > (double)1.0F && (double)this.centroidsWeight_ - weight <= lastWeight / (double)2.0F) {
                     return this.maxValue_ + ((double)this.centroidsWeight_ - weight - (double)1.0F) / (lastWeight / (double)2.0F - (double)1.0F) * (this.maxValue_ - this.centroidMeans_[this.numCentroids_ - 1]);
                  } else {
                     double weightSoFar = firstWeight / (double)2.0F;

                     for(int i = 0; i < this.numCentroids_ - 1; ++i) {
                        double dw = (double)(this.centroidWeights_[i] + this.centroidWeights_[i + 1]) / (double)2.0F;
                        if (weightSoFar + dw > weight) {
                           double leftWeight = (double)0.0F;
                           if (this.centroidWeights_[i] == 1L) {
                              if (weight - weightSoFar < (double)0.5F) {
                                 return this.centroidMeans_[i];
                              }

                              leftWeight = (double)0.5F;
                           }

                           double rightWeight = (double)0.0F;
                           if (this.centroidWeights_[i + 1] == 1L) {
                              if (weightSoFar + dw - weight <= (double)0.5F) {
                                 return this.centroidMeans_[i + 1];
                              }

                              rightWeight = (double)0.5F;
                           }

                           double w1 = weight - weightSoFar - leftWeight;
                           double w2 = weightSoFar + dw - weight - rightWeight;
                           return weightedAverage(this.centroidMeans_[i], w1, this.centroidMeans_[i + 1], w2);
                        }

                        weightSoFar += dw;
                     }

                     double w1 = weight - (double)this.centroidsWeight_ - (double)this.centroidWeights_[this.numCentroids_ - 1] / (double)2.0F;
                     double w2 = (double)this.centroidWeights_[this.numCentroids_ - 1] / (double)2.0F - w1;
                     return weightedAverage((double)this.centroidWeights_[this.numCentroids_ - 1], w1, this.maxValue_, w2);
                  }
               }
            }
         }
      } else {
         throw new SketchesArgumentException("Normalized rank must be within [0, 1]");
      }
   }

   int getSerializedSizeBytes() {
      this.compress();
      return this.getPreambleLongs() * 8 + (this.isEmpty() ? 0 : (this.isSingleValue() ? 8 : 16 + 16 * this.numCentroids_));
   }

   public byte[] toByteArray() {
      this.compress();
      byte[] bytes = new byte[this.getSerializedSizeBytes()];
      WritableBuffer wbuf = WritableMemory.writableWrap(bytes).asWritableBuffer();
      wbuf.putByte((byte)this.getPreambleLongs());
      wbuf.putByte((byte)1);
      wbuf.putByte((byte)Family.TDIGEST.getID());
      wbuf.putShort(this.k_);
      wbuf.putByte((byte)((this.isEmpty() ? 1 << TDigestDouble.Flags.IS_EMPTY.ordinal() : 0) | (this.isSingleValue() ? 1 << TDigestDouble.Flags.IS_SINGLE_VALUE.ordinal() : 0) | (this.reverseMerge_ ? 1 << TDigestDouble.Flags.REVERSE_MERGE.ordinal() : 0)));
      wbuf.putShort((short)0);
      if (this.isEmpty()) {
         return bytes;
      } else if (this.isSingleValue()) {
         wbuf.putDouble(this.minValue_);
         return bytes;
      } else {
         wbuf.putInt(this.numCentroids_);
         wbuf.putInt(0);
         wbuf.putDouble(this.minValue_);
         wbuf.putDouble(this.maxValue_);

         for(int i = 0; i < this.numCentroids_; ++i) {
            wbuf.putDouble(this.centroidMeans_[i]);
            wbuf.putLong(this.centroidWeights_[i]);
         }

         return bytes;
      }
   }

   public static TDigestDouble heapify(Memory mem) {
      return heapify(mem, false);
   }

   public static TDigestDouble heapify(Memory mem, boolean isFloat) {
      Buffer buff = mem.asBuffer();
      byte preambleLongs = buff.getByte();
      byte serialVersion = buff.getByte();
      byte sketchType = buff.getByte();
      if (sketchType != (byte)Family.TDIGEST.getID()) {
         if (preambleLongs == 0 && serialVersion == 0 && sketchType == 0) {
            return heapifyCompat(mem);
         } else {
            throw new SketchesArgumentException("Sketch type mismatch: expected " + Family.TDIGEST.getID() + ", actual " + sketchType);
         }
      } else if (serialVersion != 1) {
         throw new SketchesArgumentException("Serial version mismatch: expected 1, actual " + serialVersion);
      } else {
         short k = buff.getShort();
         byte flagsByte = buff.getByte();
         boolean isEmpty = (flagsByte & 1 << TDigestDouble.Flags.IS_EMPTY.ordinal()) > 0;
         boolean isSingleValue = (flagsByte & 1 << TDigestDouble.Flags.IS_SINGLE_VALUE.ordinal()) > 0;
         byte expectedPreambleLongs = (byte)(!isEmpty && !isSingleValue ? 2 : 1);
         if (preambleLongs != expectedPreambleLongs) {
            throw new SketchesArgumentException("Preamble longs mismatch: expected " + expectedPreambleLongs + ", actual " + preambleLongs);
         } else {
            buff.getShort();
            if (isEmpty) {
               return new TDigestDouble(k);
            } else {
               boolean reverseMerge = (flagsByte & 1 << TDigestDouble.Flags.REVERSE_MERGE.ordinal()) > 0;
               if (isSingleValue) {
                  double value;
                  if (isFloat) {
                     value = (double)buff.getFloat();
                  } else {
                     value = buff.getDouble();
                  }

                  return new TDigestDouble(reverseMerge, k, value, value, new double[]{value}, new long[]{1L}, 1L, (double[])null);
               } else {
                  int numCentroids = buff.getInt();
                  buff.getInt();
                  double min;
                  double max;
                  if (isFloat) {
                     min = (double)buff.getFloat();
                     max = (double)buff.getFloat();
                  } else {
                     min = buff.getDouble();
                     max = buff.getDouble();
                  }

                  double[] means = new double[numCentroids];
                  long[] weights = new long[numCentroids];
                  long totalWeight = 0L;

                  for(int i = 0; i < numCentroids; ++i) {
                     means[i] = isFloat ? (double)buff.getFloat() : buff.getDouble();
                     weights[i] = isFloat ? (long)buff.getInt() : buff.getLong();
                     totalWeight += weights[i];
                  }

                  return new TDigestDouble(reverseMerge, k, min, max, means, weights, totalWeight, (double[])null);
               }
            }
         }
      }
   }

   private static TDigestDouble heapifyCompat(Memory mem) {
      Buffer buff = mem.asBuffer(ByteOrder.BIG_ENDIAN);
      int type = buff.getInt();
      if (type != 1 && type != 2) {
         throw new SketchesArgumentException("unexpected compatibility type " + type);
      } else if (type == 1) {
         double min = buff.getDouble();
         double max = buff.getDouble();
         short k = (short)((int)buff.getDouble());
         int numCentroids = buff.getInt();
         double[] means = new double[numCentroids];
         long[] weights = new long[numCentroids];
         long totalWeight = 0L;

         for(int i = 0; i < numCentroids; ++i) {
            weights[i] = (long)buff.getDouble();
            means[i] = buff.getDouble();
            totalWeight += weights[i];
         }

         return new TDigestDouble(false, k, min, max, means, weights, totalWeight, (double[])null);
      } else {
         double min = buff.getDouble();
         double max = buff.getDouble();
         short k = (short)((int)buff.getFloat());
         buff.getInt();
         int numCentroids = buff.getShort();
         double[] means = new double[numCentroids];
         long[] weights = new long[numCentroids];
         long totalWeight = 0L;

         for(int i = 0; i < numCentroids; ++i) {
            weights[i] = (long)buff.getFloat();
            means[i] = (double)buff.getFloat();
            totalWeight += weights[i];
         }

         return new TDigestDouble(false, k, min, max, means, weights, totalWeight, (double[])null);
      }
   }

   public String toString() {
      return this.toString(false);
   }

   public String toString(boolean printCentroids) {
      StringBuilder sb = new StringBuilder();
      sb.append("MergingDigest").append(Util.LS).append(" Compression: ").append(this.k_).append(Util.LS).append(" Centroids: ").append(this.numCentroids_).append(Util.LS).append(" Buffered: ").append(this.numBuffered_).append(Util.LS).append(" Centroids Capacity: ").append(this.centroidsCapacity_).append(Util.LS).append(" Buffer Capacity: ").append(this.centroidsCapacity_ * 4).append(Util.LS).append("Centroids Weight: ").append(this.centroidsWeight_).append(Util.LS).append(" Total Weight: ").append(this.getTotalWeight()).append(Util.LS).append(" Reverse Merge: ").append(this.reverseMerge_).append(Util.LS);
      if (!this.isEmpty()) {
         sb.append(" Min: ").append(this.minValue_).append(Util.LS).append(" Max: ").append(this.maxValue_).append(Util.LS);
      }

      if (printCentroids) {
         if (this.numCentroids_ > 0) {
            sb.append("Centroids:").append(Util.LS);

            for(int i = 0; i < this.numCentroids_; ++i) {
               sb.append(i).append(": ").append(this.centroidMeans_[i]).append(", ").append(this.centroidWeights_[i]).append(Util.LS);
            }
         }

         if (this.numBuffered_ > 0) {
            sb.append("Buffer:").append(Util.LS);

            for(int i = 0; i < this.numBuffered_; ++i) {
               sb.append(i).append(": ").append(this.bufferValues_[i]).append(Util.LS);
            }
         }
      }

      return sb.toString();
   }

   private TDigestDouble(boolean reverseMerge, short k, double min, double max, double[] means, long[] weights, long weight, double[] buffer) {
      this.reverseMerge_ = reverseMerge;
      this.k_ = k;
      this.minValue_ = min;
      this.maxValue_ = max;
      if (k < 10) {
         throw new SketchesArgumentException("k must be at least 10");
      } else {
         int fudge = k < 30 ? 30 : 10;
         this.centroidsCapacity_ = this.k_ * 2 + fudge;
         this.centroidMeans_ = new double[this.centroidsCapacity_];
         this.centroidWeights_ = new long[this.centroidsCapacity_];
         this.bufferValues_ = new double[this.centroidsCapacity_ * 4];
         this.numCentroids_ = 0;
         this.numBuffered_ = 0;
         this.centroidsWeight_ = weight;
         if (means != null && weights != null) {
            System.arraycopy(means, 0, this.centroidMeans_, 0, means.length);
            System.arraycopy(weights, 0, this.centroidWeights_, 0, weights.length);
            this.numCentroids_ = means.length;
         }

         if (buffer != null) {
            System.arraycopy(buffer, 0, this.bufferValues_, 0, buffer.length);
            this.numBuffered_ = buffer.length;
         }

      }
   }

   private void merge(double[] values, long[] weights, long weight, int num) {
      System.arraycopy(this.centroidMeans_, 0, values, num, this.numCentroids_);
      System.arraycopy(this.centroidWeights_, 0, weights, num, this.numCentroids_);
      num += this.numCentroids_;
      this.centroidsWeight_ += weight;
      this.numCentroids_ = 0;
      Sort.stableSort(values, weights, num);
      if (this.reverseMerge_) {
         Sort.reverse(values, num);
         Sort.reverse(weights, num);
      }

      this.centroidMeans_[0] = values[0];
      this.centroidWeights_[0] = weights[0];
      ++this.numCentroids_;
      int current = 1;

      for(double weightSoFar = (double)0.0F; current != num; ++current) {
         double proposedWeight = (double)(this.centroidWeights_[this.numCentroids_ - 1] + weights[current]);
         boolean addThis = false;
         if (current != 1 && current != num - 1) {
            double q0 = weightSoFar / (double)this.centroidsWeight_;
            double q2 = (weightSoFar + proposedWeight) / (double)this.centroidsWeight_;
            double normalizer = TDigestDouble.ScaleFunction.normalizer((double)(this.k_ * 2), (double)this.centroidsWeight_);
            addThis = proposedWeight <= (double)this.centroidsWeight_ * Math.min(TDigestDouble.ScaleFunction.max(q0, normalizer), TDigestDouble.ScaleFunction.max(q2, normalizer));
         }

         if (addThis) {
            long[] var10000 = this.centroidWeights_;
            int var10001 = this.numCentroids_ - 1;
            var10000[var10001] += weights[current];
            double[] var19 = this.centroidMeans_;
            var10001 = this.numCentroids_ - 1;
            var19[var10001] += (values[current] - this.centroidMeans_[this.numCentroids_ - 1]) * (double)weights[current] / (double)this.centroidWeights_[this.numCentroids_ - 1];
         } else {
            weightSoFar += (double)this.centroidWeights_[this.numCentroids_ - 1];
            this.centroidMeans_[this.numCentroids_] = values[current];
            this.centroidWeights_[this.numCentroids_] = weights[current];
            ++this.numCentroids_;
         }
      }

      if (this.reverseMerge_) {
         Sort.reverse(this.centroidMeans_, this.numCentroids_);
         Sort.reverse(this.centroidWeights_, this.numCentroids_);
      }

      this.numBuffered_ = 0;
      this.reverseMerge_ = !this.reverseMerge_;
      this.minValue_ = Math.min(this.minValue_, this.centroidMeans_[0]);
      this.maxValue_ = Math.max(this.maxValue_, this.centroidMeans_[this.numCentroids_ - 1]);
   }

   private boolean isSingleValue() {
      return this.getTotalWeight() == 1L;
   }

   private int getPreambleLongs() {
      return !this.isEmpty() && !this.isSingleValue() ? 2 : 1;
   }

   private static double weightedAverage(double x1, double w1, double x2, double w2) {
      return (x1 * w1 + x2 * w2) / (w1 + w2);
   }

   private static enum Flags {
      IS_EMPTY,
      IS_SINGLE_VALUE,
      REVERSE_MERGE;
   }

   private static final class ScaleFunction {
      static double max(double q, double normalizer) {
         return q * ((double)1.0F - q) / normalizer;
      }

      static double normalizer(double compression, double n) {
         return compression / z(compression, n);
      }

      static double z(double compression, double n) {
         return (double)4.0F * Math.log(n / compression) + (double)24.0F;
      }
   }
}
