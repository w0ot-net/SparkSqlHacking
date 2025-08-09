package com.google.common.hash;

import com.google.common.annotations.Beta;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.math.DoubleMath;
import com.google.common.math.LongMath;
import com.google.common.primitives.SignedBytes;
import com.google.common.primitives.UnsignedBytes;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.RoundingMode;
import java.util.stream.Collector;
import java.util.stream.Collector.Characteristics;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@Beta
public final class BloomFilter implements Predicate, Serializable {
   private final BloomFilterStrategies.LockFreeBitArray bits;
   private final int numHashFunctions;
   private final Funnel funnel;
   private final Strategy strategy;
   private static final double LOG_TWO = Math.log((double)2.0F);
   private static final double SQUARED_LOG_TWO;
   private static final long serialVersionUID = -889275714L;

   private BloomFilter(BloomFilterStrategies.LockFreeBitArray bits, int numHashFunctions, Funnel funnel, Strategy strategy) {
      Preconditions.checkArgument(numHashFunctions > 0, "numHashFunctions (%s) must be > 0", numHashFunctions);
      Preconditions.checkArgument(numHashFunctions <= 255, "numHashFunctions (%s) must be <= 255", numHashFunctions);
      this.bits = (BloomFilterStrategies.LockFreeBitArray)Preconditions.checkNotNull(bits);
      this.numHashFunctions = numHashFunctions;
      this.funnel = (Funnel)Preconditions.checkNotNull(funnel);
      this.strategy = (Strategy)Preconditions.checkNotNull(strategy);
   }

   public BloomFilter copy() {
      return new BloomFilter(this.bits.copy(), this.numHashFunctions, this.funnel, this.strategy);
   }

   public boolean mightContain(@ParametricNullness Object object) {
      return this.strategy.mightContain(object, this.funnel, this.numHashFunctions, this.bits);
   }

   /** @deprecated */
   @Deprecated
   public boolean apply(@ParametricNullness Object input) {
      return this.mightContain(input);
   }

   @CanIgnoreReturnValue
   public boolean put(@ParametricNullness Object object) {
      return this.strategy.put(object, this.funnel, this.numHashFunctions, this.bits);
   }

   public double expectedFpp() {
      return Math.pow((double)this.bits.bitCount() / (double)this.bitSize(), (double)this.numHashFunctions);
   }

   public long approximateElementCount() {
      long bitSize = this.bits.bitSize();
      long bitCount = this.bits.bitCount();
      double fractionOfBitsSet = (double)bitCount / (double)bitSize;
      return DoubleMath.roundToLong(-Math.log1p(-fractionOfBitsSet) * (double)bitSize / (double)this.numHashFunctions, RoundingMode.HALF_UP);
   }

   @VisibleForTesting
   long bitSize() {
      return this.bits.bitSize();
   }

   public boolean isCompatible(BloomFilter that) {
      Preconditions.checkNotNull(that);
      return this != that && this.numHashFunctions == that.numHashFunctions && this.bitSize() == that.bitSize() && this.strategy.equals(that.strategy) && this.funnel.equals(that.funnel);
   }

   public void putAll(BloomFilter that) {
      Preconditions.checkNotNull(that);
      Preconditions.checkArgument(this != that, "Cannot combine a BloomFilter with itself.");
      Preconditions.checkArgument(this.numHashFunctions == that.numHashFunctions, "BloomFilters must have the same number of hash functions (%s != %s)", this.numHashFunctions, that.numHashFunctions);
      Preconditions.checkArgument(this.bitSize() == that.bitSize(), "BloomFilters must have the same size underlying bit arrays (%s != %s)", this.bitSize(), that.bitSize());
      Preconditions.checkArgument(this.strategy.equals(that.strategy), "BloomFilters must have equal strategies (%s != %s)", this.strategy, that.strategy);
      Preconditions.checkArgument(this.funnel.equals(that.funnel), "BloomFilters must have equal funnels (%s != %s)", this.funnel, that.funnel);
      this.bits.putAll(that.bits);
   }

   public boolean equals(@CheckForNull Object object) {
      if (object == this) {
         return true;
      } else if (!(object instanceof BloomFilter)) {
         return false;
      } else {
         BloomFilter<?> that = (BloomFilter)object;
         return this.numHashFunctions == that.numHashFunctions && this.funnel.equals(that.funnel) && this.bits.equals(that.bits) && this.strategy.equals(that.strategy);
      }
   }

   public int hashCode() {
      return Objects.hashCode(this.numHashFunctions, this.funnel, this.strategy, this.bits);
   }

   public static Collector toBloomFilter(Funnel funnel, long expectedInsertions) {
      return toBloomFilter(funnel, expectedInsertions, 0.03);
   }

   public static Collector toBloomFilter(Funnel funnel, long expectedInsertions, double fpp) {
      Preconditions.checkNotNull(funnel);
      Preconditions.checkArgument(expectedInsertions >= 0L, "Expected insertions (%s) must be >= 0", expectedInsertions);
      Preconditions.checkArgument(fpp > (double)0.0F, "False positive probability (%s) must be > 0.0", (Object)fpp);
      Preconditions.checkArgument(fpp < (double)1.0F, "False positive probability (%s) must be < 1.0", (Object)fpp);
      return Collector.of(() -> create(funnel, expectedInsertions, fpp), BloomFilter::put, (bf1, bf2) -> {
         bf1.putAll(bf2);
         return bf1;
      }, Characteristics.UNORDERED, Characteristics.CONCURRENT);
   }

   public static BloomFilter create(Funnel funnel, int expectedInsertions, double fpp) {
      return create(funnel, (long)expectedInsertions, fpp);
   }

   public static BloomFilter create(Funnel funnel, long expectedInsertions, double fpp) {
      return create(funnel, expectedInsertions, fpp, BloomFilterStrategies.MURMUR128_MITZ_64);
   }

   @VisibleForTesting
   static BloomFilter create(Funnel funnel, long expectedInsertions, double fpp, Strategy strategy) {
      Preconditions.checkNotNull(funnel);
      Preconditions.checkArgument(expectedInsertions >= 0L, "Expected insertions (%s) must be >= 0", expectedInsertions);
      Preconditions.checkArgument(fpp > (double)0.0F, "False positive probability (%s) must be > 0.0", (Object)fpp);
      Preconditions.checkArgument(fpp < (double)1.0F, "False positive probability (%s) must be < 1.0", (Object)fpp);
      Preconditions.checkNotNull(strategy);
      if (expectedInsertions == 0L) {
         expectedInsertions = 1L;
      }

      long numBits = optimalNumOfBits(expectedInsertions, fpp);
      int numHashFunctions = optimalNumOfHashFunctions(fpp);

      try {
         return new BloomFilter(new BloomFilterStrategies.LockFreeBitArray(numBits), numHashFunctions, funnel, strategy);
      } catch (IllegalArgumentException e) {
         throw new IllegalArgumentException("Could not create BloomFilter of " + numBits + " bits", e);
      }
   }

   public static BloomFilter create(Funnel funnel, int expectedInsertions) {
      return create(funnel, (long)expectedInsertions);
   }

   public static BloomFilter create(Funnel funnel, long expectedInsertions) {
      return create(funnel, expectedInsertions, 0.03);
   }

   @VisibleForTesting
   static int optimalNumOfHashFunctions(double p) {
      return Math.max(1, (int)Math.round(-Math.log(p) / LOG_TWO));
   }

   @VisibleForTesting
   static long optimalNumOfBits(long n, double p) {
      if (p == (double)0.0F) {
         p = Double.MIN_VALUE;
      }

      return (long)((double)(-n) * Math.log(p) / SQUARED_LOG_TWO);
   }

   private Object writeReplace() {
      return new SerialForm(this);
   }

   private void readObject(ObjectInputStream stream) throws InvalidObjectException {
      throw new InvalidObjectException("Use SerializedForm");
   }

   public void writeTo(OutputStream out) throws IOException {
      DataOutputStream dout = new DataOutputStream(out);
      dout.writeByte(SignedBytes.checkedCast((long)this.strategy.ordinal()));
      dout.writeByte(UnsignedBytes.checkedCast((long)this.numHashFunctions));
      dout.writeInt(this.bits.data.length());

      for(int i = 0; i < this.bits.data.length(); ++i) {
         dout.writeLong(this.bits.data.get(i));
      }

   }

   public static BloomFilter readFrom(InputStream in, Funnel funnel) throws IOException {
      Preconditions.checkNotNull(in, "InputStream");
      Preconditions.checkNotNull(funnel, "Funnel");
      int strategyOrdinal = -1;
      int numHashFunctions = -1;
      int dataLength = -1;

      try {
         DataInputStream din = new DataInputStream(in);
         strategyOrdinal = din.readByte();
         numHashFunctions = UnsignedBytes.toInt(din.readByte());
         dataLength = din.readInt();
         Strategy strategy = BloomFilterStrategies.values()[strategyOrdinal];
         BloomFilterStrategies.LockFreeBitArray dataArray = new BloomFilterStrategies.LockFreeBitArray(LongMath.checkedMultiply((long)dataLength, 64L));

         for(int i = 0; i < dataLength; ++i) {
            dataArray.putData(i, din.readLong());
         }

         return new BloomFilter(dataArray, numHashFunctions, funnel, strategy);
      } catch (IOException e) {
         throw e;
      } catch (Exception e) {
         String message = "Unable to deserialize BloomFilter from InputStream. strategyOrdinal: " + strategyOrdinal + " numHashFunctions: " + numHashFunctions + " dataLength: " + dataLength;
         throw new IOException(message, e);
      }
   }

   static {
      SQUARED_LOG_TWO = LOG_TWO * LOG_TWO;
   }

   private static class SerialForm implements Serializable {
      final long[] data;
      final int numHashFunctions;
      final Funnel funnel;
      final Strategy strategy;
      private static final long serialVersionUID = 1L;

      SerialForm(BloomFilter bf) {
         this.data = BloomFilterStrategies.LockFreeBitArray.toPlainArray(bf.bits.data);
         this.numHashFunctions = bf.numHashFunctions;
         this.funnel = bf.funnel;
         this.strategy = bf.strategy;
      }

      Object readResolve() {
         return new BloomFilter(new BloomFilterStrategies.LockFreeBitArray(this.data), this.numHashFunctions, this.funnel, this.strategy);
      }
   }

   interface Strategy extends Serializable {
      boolean put(@ParametricNullness Object object, Funnel funnel, int numHashFunctions, BloomFilterStrategies.LockFreeBitArray bits);

      boolean mightContain(@ParametricNullness Object object, Funnel funnel, int numHashFunctions, BloomFilterStrategies.LockFreeBitArray bits);

      int ordinal();
   }
}
