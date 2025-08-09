package com.clearspring.analytics.stream.cardinality;

import com.clearspring.analytics.hash.MurmurHash;
import com.clearspring.analytics.util.Bits;
import com.clearspring.analytics.util.IBuilder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.Serializable;

public class HyperLogLog implements ICardinality, Serializable {
   private final RegisterSet registerSet;
   private final int log2m;
   private final double alphaMM;

   public HyperLogLog(double rsd) {
      this(log2m(rsd));
   }

   private static int log2m(double rsd) {
      return (int)(Math.log(1.106 / rsd * (1.106 / rsd)) / Math.log((double)2.0F));
   }

   private static double rsd(int log2m) {
      return 1.106 / Math.sqrt(Math.exp((double)log2m * Math.log((double)2.0F)));
   }

   private static double logBase(double exponent, double base) {
      return Math.log(exponent) / Math.log(base);
   }

   private static int accuracyToLog2m(double accuracy) {
      return Math.toIntExact(2L * Math.round(logBase(1.04 / ((double)1.0F - accuracy), (double)2.0F)));
   }

   private static void validateLog2m(int log2m) {
      if (log2m < 0 || log2m > 30) {
         throw new IllegalArgumentException("log2m argument is " + log2m + " and is outside the range [0, 30]");
      }
   }

   public HyperLogLog(int log2m) {
      this(log2m, new RegisterSet(1 << log2m));
   }

   /** @deprecated */
   @Deprecated
   public HyperLogLog(int log2m, RegisterSet registerSet) {
      validateLog2m(log2m);
      this.registerSet = registerSet;
      this.log2m = log2m;
      int m = 1 << this.log2m;
      this.alphaMM = getAlphaMM(log2m, m);
   }

   public boolean offerHashed(long hashedValue) {
      int j = (int)(hashedValue >>> 64 - this.log2m);
      int r = Long.numberOfLeadingZeros(hashedValue << this.log2m | (long)((1 << this.log2m - 1) + 1)) + 1;
      return this.registerSet.updateIfGreater(j, r);
   }

   public boolean offerHashed(int hashedValue) {
      int j = hashedValue >>> 32 - this.log2m;
      int r = Integer.numberOfLeadingZeros(hashedValue << this.log2m | (1 << this.log2m - 1) + 1) + 1;
      return this.registerSet.updateIfGreater(j, r);
   }

   public boolean offer(Object o) {
      int x = MurmurHash.hash(o);
      return this.offerHashed(x);
   }

   public long cardinality() {
      double registerSum = (double)0.0F;
      int count = this.registerSet.count;
      double zeros = (double)0.0F;

      for(int j = 0; j < this.registerSet.count; ++j) {
         int val = this.registerSet.get(j);
         registerSum += (double)1.0F / (double)(1 << val);
         if (val == 0) {
            ++zeros;
         }
      }

      double estimate = this.alphaMM * ((double)1.0F / registerSum);
      if (estimate <= (double)2.5F * (double)count) {
         return Math.round(linearCounting(count, zeros));
      } else {
         return Math.round(estimate);
      }
   }

   public int sizeof() {
      return this.registerSet.size * 4;
   }

   public byte[] getBytes() throws IOException {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      DataOutput dos = new DataOutputStream(baos);
      this.writeBytes(dos);
      baos.close();
      return baos.toByteArray();
   }

   private void writeBytes(DataOutput serializedByteStream) throws IOException {
      serializedByteStream.writeInt(this.log2m);
      serializedByteStream.writeInt(this.registerSet.size * 4);

      for(int x : this.registerSet.readOnlyBits()) {
         serializedByteStream.writeInt(x);
      }

   }

   public void addAll(HyperLogLog other) throws CardinalityMergeException {
      if (this.sizeof() != other.sizeof()) {
         throw new HyperLogLogMergeException("Cannot merge estimators of different sizes");
      } else {
         this.registerSet.merge(other.registerSet);
      }
   }

   public ICardinality merge(ICardinality... estimators) throws CardinalityMergeException {
      HyperLogLog merged = new HyperLogLog(this.log2m, new RegisterSet(this.registerSet.count));
      merged.addAll(this);
      if (estimators == null) {
         return merged;
      } else {
         for(ICardinality estimator : estimators) {
            if (!(estimator instanceof HyperLogLog)) {
               throw new HyperLogLogMergeException("Cannot merge estimators of different class");
            }

            HyperLogLog hll = (HyperLogLog)estimator;
            merged.addAll(hll);
         }

         return merged;
      }
   }

   private Object writeReplace() {
      return new SerializationHolder(this);
   }

   protected static double getAlphaMM(int p, int m) {
      switch (p) {
         case 4:
            return 0.673 * (double)m * (double)m;
         case 5:
            return 0.697 * (double)m * (double)m;
         case 6:
            return 0.709 * (double)m * (double)m;
         default:
            return 0.7213 / ((double)1.0F + 1.079 / (double)m) * (double)m * (double)m;
      }
   }

   protected static double linearCounting(int m, double V) {
      return (double)m * Math.log((double)m / V);
   }

   private static class SerializationHolder implements Externalizable {
      HyperLogLog hyperLogLogHolder;

      public SerializationHolder(HyperLogLog hyperLogLogHolder) {
         this.hyperLogLogHolder = hyperLogLogHolder;
      }

      public SerializationHolder() {
      }

      public void writeExternal(ObjectOutput out) throws IOException {
         this.hyperLogLogHolder.writeBytes(out);
      }

      public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
         this.hyperLogLogHolder = HyperLogLog.Builder.build((DataInput)in);
      }

      private Object readResolve() {
         return this.hyperLogLogHolder;
      }
   }

   public static class Builder implements IBuilder, Serializable {
      private static final long serialVersionUID = -2567898469253021883L;
      private final double rsd;
      private transient int log2m;

      /** @deprecated */
      @Deprecated
      public Builder(double rsd) {
         this.log2m = HyperLogLog.log2m(rsd);
         HyperLogLog.validateLog2m(this.log2m);
         this.rsd = rsd;
      }

      private Builder(int log2m) {
         this.log2m = log2m;
         HyperLogLog.validateLog2m(log2m);
         this.rsd = HyperLogLog.rsd(log2m);
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         in.defaultReadObject();
         this.log2m = HyperLogLog.log2m(this.rsd);
      }

      public HyperLogLog build() {
         return new HyperLogLog(this.log2m);
      }

      public int sizeof() {
         int k = 1 << this.log2m;
         return RegisterSet.getBits(k) * 4;
      }

      public static Builder withLog2m(int log2m) {
         return new Builder(log2m);
      }

      public static Builder withRsd(double rsd) {
         return new Builder(rsd);
      }

      public static Builder withAccuracy(double accuracy) {
         return new Builder(HyperLogLog.accuracyToLog2m(accuracy));
      }

      public static HyperLogLog build(byte[] bytes) throws IOException {
         ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
         return build((DataInput)(new DataInputStream(bais)));
      }

      public static HyperLogLog build(DataInput serializedByteStream) throws IOException {
         int log2m = serializedByteStream.readInt();
         int byteArraySize = serializedByteStream.readInt();
         return new HyperLogLog(log2m, new RegisterSet(1 << log2m, Bits.getBits(serializedByteStream, byteArraySize)));
      }
   }

   protected static class HyperLogLogMergeException extends CardinalityMergeException {
      public HyperLogLogMergeException(String message) {
         super(message);
      }
   }
}
