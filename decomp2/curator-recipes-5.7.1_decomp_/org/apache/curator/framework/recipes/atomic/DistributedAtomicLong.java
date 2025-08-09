package org.apache.curator.framework.recipes.atomic;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.shaded.com.google.common.annotations.VisibleForTesting;
import org.apache.curator.shaded.com.google.common.base.Preconditions;

public class DistributedAtomicLong implements DistributedAtomicNumber {
   private final DistributedAtomicValue value;

   public DistributedAtomicLong(CuratorFramework client, String counterPath, RetryPolicy retryPolicy) {
      this(client, counterPath, retryPolicy, (PromotedToLock)null);
   }

   public DistributedAtomicLong(CuratorFramework client, String counterPath, RetryPolicy retryPolicy, PromotedToLock promotedToLock) {
      this.value = new DistributedAtomicValue(client, counterPath, retryPolicy, promotedToLock);
   }

   public AtomicValue get() throws Exception {
      return new AtomicLong(this.value.get());
   }

   public void forceSet(Long newValue) throws Exception {
      this.value.forceSet(this.valueToBytes(newValue));
   }

   public AtomicValue compareAndSet(Long expectedValue, Long newValue) throws Exception {
      return new AtomicLong(this.value.compareAndSet(this.valueToBytes(expectedValue), this.valueToBytes(newValue)));
   }

   public AtomicValue trySet(Long newValue) throws Exception {
      return new AtomicLong(this.value.trySet(this.valueToBytes(newValue)));
   }

   public boolean initialize(Long initialize) throws Exception {
      return this.value.initialize(this.valueToBytes(initialize));
   }

   public AtomicValue increment() throws Exception {
      return this.worker(1L);
   }

   public AtomicValue decrement() throws Exception {
      return this.worker(-1L);
   }

   public AtomicValue add(Long delta) throws Exception {
      return this.worker(delta);
   }

   public AtomicValue subtract(Long delta) throws Exception {
      return this.worker(-1L * delta);
   }

   @VisibleForTesting
   byte[] valueToBytes(Long newValue) {
      Preconditions.checkNotNull(newValue, "newValue cannot be null");
      byte[] newData = new byte[8];
      ByteBuffer wrapper = ByteBuffer.wrap(newData);
      wrapper.putLong(newValue);
      return newData;
   }

   @VisibleForTesting
   long bytesToValue(byte[] data) {
      if (data != null && data.length != 0) {
         ByteBuffer wrapper = ByteBuffer.wrap(data);

         try {
            return wrapper.getLong();
         } catch (BufferUnderflowException var4) {
            throw this.value.createCorruptionException(data);
         } catch (BufferOverflowException var5) {
            throw this.value.createCorruptionException(data);
         }
      } else {
         return 0L;
      }
   }

   private AtomicValue worker(final Long addAmount) throws Exception {
      Preconditions.checkNotNull(addAmount, "addAmount cannot be null");
      MakeValue makeValue = new MakeValue() {
         public byte[] makeFrom(byte[] previous) {
            long previousValue = previous != null ? DistributedAtomicLong.this.bytesToValue(previous) : 0L;
            long newValue = previousValue + addAmount;
            return DistributedAtomicLong.this.valueToBytes(newValue);
         }
      };
      AtomicValue<byte[]> result = this.value.trySet(makeValue);
      return new AtomicLong(result);
   }

   private class AtomicLong implements AtomicValue {
      private AtomicValue bytes;

      private AtomicLong(AtomicValue bytes) {
         this.bytes = bytes;
      }

      public boolean succeeded() {
         return this.bytes.succeeded();
      }

      public Long preValue() {
         return DistributedAtomicLong.this.bytesToValue((byte[])this.bytes.preValue());
      }

      public Long postValue() {
         return DistributedAtomicLong.this.bytesToValue((byte[])this.bytes.postValue());
      }

      public AtomicStats getStats() {
         return this.bytes.getStats();
      }
   }
}
