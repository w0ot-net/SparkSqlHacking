package com.clearspring.analytics.stream.cardinality;

import com.clearspring.analytics.util.ExternalizableUtil;
import com.clearspring.analytics.util.IBuilder;
import java.io.ByteArrayInputStream;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CountThenEstimate implements ICardinality, Externalizable {
   protected static final byte LC = 1;
   protected static final byte AC = 2;
   protected static final byte HLC = 3;
   protected static final byte LLC = 4;
   protected static final byte HLPC = 5;
   protected int tippingPoint;
   protected boolean tipped;
   protected IBuilder builder;
   protected ICardinality estimator;
   protected Set counter;

   public CountThenEstimate() {
      this(1000, AdaptiveCounting.Builder.obyCount(1000000000L));
   }

   public CountThenEstimate(int tippingPoint, IBuilder builder) {
      this.tipped = false;
      this.tippingPoint = tippingPoint;
      this.builder = builder;
      this.counter = new HashSet();
   }

   public CountThenEstimate(byte[] bytes) throws IOException, ClassNotFoundException {
      this.tipped = false;
      this.readExternal(new ObjectInputStream(new ByteArrayInputStream(bytes)));
      if (!this.tipped && this.builder.sizeof() <= bytes.length) {
         this.tip();
      }

   }

   public long cardinality() {
      return this.tipped ? this.estimator.cardinality() : (long)this.counter.size();
   }

   public boolean offerHashed(long hashedLong) {
      throw new UnsupportedOperationException();
   }

   public boolean offerHashed(int hashedInt) {
      throw new UnsupportedOperationException();
   }

   public boolean offer(Object o) {
      boolean modified = false;
      if (this.tipped) {
         modified = this.estimator.offer(o);
      } else if (this.counter.add(o)) {
         modified = true;
         if (this.counter.size() > this.tippingPoint) {
            this.tip();
         }
      }

      return modified;
   }

   public int sizeof() {
      return this.tipped ? this.estimator.sizeof() : -1;
   }

   private void tip() {
      this.estimator = (ICardinality)this.builder.build();

      for(Object o : this.counter) {
         this.estimator.offer(o);
      }

      this.counter = null;
      this.builder = null;
      this.tipped = true;
   }

   public boolean tipped() {
      return this.tipped;
   }

   public byte[] getBytes() throws IOException {
      return ExternalizableUtil.toBytes(this);
   }

   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
      this.tipped = in.readBoolean();
      if (this.tipped) {
         byte type = in.readByte();
         byte[] bytes = new byte[in.readInt()];
         in.readFully(bytes);
         switch (type) {
            case 1:
               this.estimator = new LinearCounting(bytes);
               break;
            case 2:
               this.estimator = new AdaptiveCounting(bytes);
               break;
            case 3:
               this.estimator = HyperLogLog.Builder.build(bytes);
               break;
            case 4:
               this.estimator = new LogLog(bytes);
               break;
            case 5:
               this.estimator = HyperLogLogPlus.Builder.build(bytes);
               break;
            default:
               throw new IOException("Unrecognized estimator type: " + type);
         }
      } else {
         this.tippingPoint = in.readInt();
         this.builder = (IBuilder)in.readObject();
         int count = in.readInt();

         assert count <= this.tippingPoint : String.format("Invalid serialization: count (%d) > tippingPoint (%d)", count, this.tippingPoint);

         this.counter = new HashSet(count);

         for(int i = 0; i < count; ++i) {
            this.counter.add(in.readObject());
         }
      }

   }

   public void writeExternal(ObjectOutput out) throws IOException {
      out.writeBoolean(this.tipped);
      if (this.tipped) {
         if (this.estimator instanceof LinearCounting) {
            out.writeByte(1);
         } else if (this.estimator instanceof AdaptiveCounting) {
            out.writeByte(2);
         } else if (this.estimator instanceof HyperLogLog) {
            out.writeByte(3);
         } else if (this.estimator instanceof HyperLogLogPlus) {
            out.writeByte(5);
         } else {
            if (!(this.estimator instanceof LogLog)) {
               throw new IOException("Estimator unsupported for serialization: " + this.estimator.getClass().getName());
            }

            out.writeByte(4);
         }

         byte[] bytes = this.estimator.getBytes();
         out.writeInt(bytes.length);
         out.write(bytes);
      } else {
         out.writeInt(this.tippingPoint);
         out.writeObject(this.builder);
         out.writeInt(this.counter.size());

         for(Object o : this.counter) {
            out.writeObject(o);
         }
      }

   }

   public ICardinality merge(ICardinality... estimators) throws CardinalityMergeException {
      if (estimators == null) {
         return mergeEstimators(this);
      } else {
         CountThenEstimate[] all = (CountThenEstimate[])Arrays.copyOf(estimators, estimators.length + 1, CountThenEstimate[].class);
         all[all.length - 1] = this;
         return mergeEstimators(all);
      }
   }

   public static CountThenEstimate mergeEstimators(CountThenEstimate... estimators) throws CardinalityMergeException {
      CountThenEstimate merged = null;
      int numEstimators = estimators == null ? 0 : estimators.length;
      if (numEstimators > 0) {
         List<ICardinality> tipped = new ArrayList(numEstimators);
         List<CountThenEstimate> untipped = new ArrayList(numEstimators);

         for(CountThenEstimate estimator : estimators) {
            if (estimator.tipped) {
               tipped.add(estimator.estimator);
            } else {
               untipped.add(estimator);
            }
         }

         if (untipped.size() <= 0) {
            merged = new CountThenEstimate(0, new LinearCounting.Builder(1));
            merged.tip();
            merged.estimator = (ICardinality)tipped.remove(0);
         } else {
            merged = new CountThenEstimate(((CountThenEstimate)untipped.get(0)).tippingPoint, ((CountThenEstimate)untipped.get(0)).builder);

            for(CountThenEstimate cte : untipped) {
               for(Object o : cte.counter) {
                  merged.offer(o);
               }
            }
         }

         if (!tipped.isEmpty()) {
            if (!merged.tipped) {
               merged.tip();
            }

            merged.estimator = merged.estimator.merge((ICardinality[])tipped.toArray(new ICardinality[tipped.size()]));
         }
      }

      return merged;
   }

   protected static class CountThenEstimateMergeException extends CardinalityMergeException {
      public CountThenEstimateMergeException(String message) {
         super(message);
      }
   }
}
