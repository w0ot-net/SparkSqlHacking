package org.apache.curator.framework.recipes.atomic;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.shaded.com.google.common.annotations.VisibleForTesting;
import org.apache.curator.shaded.com.google.common.base.Preconditions;

public class DistributedAtomicInteger implements DistributedAtomicNumber {
   private final DistributedAtomicValue value;

   public DistributedAtomicInteger(CuratorFramework client, String counterPath, RetryPolicy retryPolicy) {
      this(client, counterPath, retryPolicy, (PromotedToLock)null);
   }

   public DistributedAtomicInteger(CuratorFramework client, String counterPath, RetryPolicy retryPolicy, PromotedToLock promotedToLock) {
      this.value = new DistributedAtomicValue(client, counterPath, retryPolicy, promotedToLock);
   }

   public AtomicValue get() throws Exception {
      return new AtomicInteger(this.value.get());
   }

   public void forceSet(Integer newValue) throws Exception {
      this.value.forceSet(this.valueToBytes(newValue));
   }

   public AtomicValue compareAndSet(Integer expectedValue, Integer newValue) throws Exception {
      return new AtomicInteger(this.value.compareAndSet(this.valueToBytes(expectedValue), this.valueToBytes(newValue)));
   }

   public AtomicValue trySet(Integer newValue) throws Exception {
      return new AtomicInteger(this.value.trySet(this.valueToBytes(newValue)));
   }

   public boolean initialize(Integer initialize) throws Exception {
      return this.value.initialize(this.valueToBytes(initialize));
   }

   public AtomicValue increment() throws Exception {
      return this.worker(1);
   }

   public AtomicValue decrement() throws Exception {
      return this.worker(-1);
   }

   public AtomicValue add(Integer delta) throws Exception {
      return this.worker(delta);
   }

   public AtomicValue subtract(Integer delta) throws Exception {
      return this.worker(-1 * delta);
   }

   @VisibleForTesting
   byte[] valueToBytes(Integer newValue) {
      Preconditions.checkNotNull(newValue, "newValue cannot be null");
      byte[] newData = new byte[4];
      ByteBuffer wrapper = ByteBuffer.wrap(newData);
      wrapper.putInt(newValue);
      return newData;
   }

   @VisibleForTesting
   int bytesToValue(byte[] data) {
      if (data != null && data.length != 0) {
         ByteBuffer wrapper = ByteBuffer.wrap(data);

         try {
            return wrapper.getInt();
         } catch (BufferUnderflowException var4) {
            throw this.value.createCorruptionException(data);
         } catch (BufferOverflowException var5) {
            throw this.value.createCorruptionException(data);
         }
      } else {
         return 0;
      }
   }

   private AtomicValue worker(final Integer addAmount) throws Exception {
      Preconditions.checkNotNull(addAmount, "addAmount cannot be null");
      MakeValue makeValue = new MakeValue() {
         public byte[] makeFrom(byte[] previous) {
            int previousValue = previous != null ? DistributedAtomicInteger.this.bytesToValue(previous) : 0;
            int newValue = previousValue + addAmount;
            return DistributedAtomicInteger.this.valueToBytes(newValue);
         }
      };
      AtomicValue<byte[]> result = this.value.trySet(makeValue);
      return new AtomicInteger(result);
   }

   private class AtomicInteger implements AtomicValue {
      private AtomicValue bytes;

      private AtomicInteger(AtomicValue bytes) {
         this.bytes = bytes;
      }

      public boolean succeeded() {
         return this.bytes.succeeded();
      }

      public Integer preValue() {
         return DistributedAtomicInteger.this.bytesToValue((byte[])this.bytes.preValue());
      }

      public Integer postValue() {
         return DistributedAtomicInteger.this.bytesToValue((byte[])this.bytes.postValue());
      }

      public AtomicStats getStats() {
         return this.bytes.getStats();
      }
   }
}
