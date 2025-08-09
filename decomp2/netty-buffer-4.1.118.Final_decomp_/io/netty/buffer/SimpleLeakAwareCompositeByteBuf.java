package io.netty.buffer;

import io.netty.util.ResourceLeakTracker;
import io.netty.util.internal.ObjectUtil;
import java.nio.ByteOrder;

class SimpleLeakAwareCompositeByteBuf extends WrappedCompositeByteBuf {
   final ResourceLeakTracker leak;

   SimpleLeakAwareCompositeByteBuf(CompositeByteBuf wrapped, ResourceLeakTracker leak) {
      super(wrapped);
      this.leak = (ResourceLeakTracker)ObjectUtil.checkNotNull(leak, "leak");
   }

   public boolean release() {
      ByteBuf unwrapped = this.unwrap();
      if (super.release()) {
         this.closeLeak(unwrapped);
         return true;
      } else {
         return false;
      }
   }

   public boolean release(int decrement) {
      ByteBuf unwrapped = this.unwrap();
      if (super.release(decrement)) {
         this.closeLeak(unwrapped);
         return true;
      } else {
         return false;
      }
   }

   private void closeLeak(ByteBuf trackedByteBuf) {
      boolean closed = this.leak.close(trackedByteBuf);

      assert closed;

   }

   public ByteBuf order(ByteOrder endianness) {
      return (ByteBuf)(this.order() == endianness ? this : this.newLeakAwareByteBuf(super.order(endianness)));
   }

   public ByteBuf slice() {
      return this.newLeakAwareByteBuf(super.slice());
   }

   public ByteBuf retainedSlice() {
      return this.newLeakAwareByteBuf(super.retainedSlice());
   }

   public ByteBuf slice(int index, int length) {
      return this.newLeakAwareByteBuf(super.slice(index, length));
   }

   public ByteBuf retainedSlice(int index, int length) {
      return this.newLeakAwareByteBuf(super.retainedSlice(index, length));
   }

   public ByteBuf duplicate() {
      return this.newLeakAwareByteBuf(super.duplicate());
   }

   public ByteBuf retainedDuplicate() {
      return this.newLeakAwareByteBuf(super.retainedDuplicate());
   }

   public ByteBuf readSlice(int length) {
      return this.newLeakAwareByteBuf(super.readSlice(length));
   }

   public ByteBuf readRetainedSlice(int length) {
      return this.newLeakAwareByteBuf(super.readRetainedSlice(length));
   }

   public ByteBuf asReadOnly() {
      return this.newLeakAwareByteBuf(super.asReadOnly());
   }

   private SimpleLeakAwareByteBuf newLeakAwareByteBuf(ByteBuf wrapped) {
      return this.newLeakAwareByteBuf(wrapped, this.unwrap(), this.leak);
   }

   protected SimpleLeakAwareByteBuf newLeakAwareByteBuf(ByteBuf wrapped, ByteBuf trackedByteBuf, ResourceLeakTracker leakTracker) {
      return new SimpleLeakAwareByteBuf(wrapped, trackedByteBuf, leakTracker);
   }
}
