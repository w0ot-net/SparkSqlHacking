package io.netty.util;

import io.netty.util.internal.ReferenceCountUpdater;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public abstract class AbstractReferenceCounted implements ReferenceCounted {
   private static final long REFCNT_FIELD_OFFSET = ReferenceCountUpdater.getUnsafeOffset(AbstractReferenceCounted.class, "refCnt");
   private static final AtomicIntegerFieldUpdater AIF_UPDATER = AtomicIntegerFieldUpdater.newUpdater(AbstractReferenceCounted.class, "refCnt");
   private static final ReferenceCountUpdater updater = new ReferenceCountUpdater() {
      protected AtomicIntegerFieldUpdater updater() {
         return AbstractReferenceCounted.AIF_UPDATER;
      }

      protected long unsafeOffset() {
         return AbstractReferenceCounted.REFCNT_FIELD_OFFSET;
      }
   };
   private volatile int refCnt;

   public AbstractReferenceCounted() {
      this.refCnt = updater.initialValue();
   }

   public int refCnt() {
      return updater.refCnt(this);
   }

   protected final void setRefCnt(int refCnt) {
      updater.setRefCnt(this, refCnt);
   }

   public ReferenceCounted retain() {
      return updater.retain(this);
   }

   public ReferenceCounted retain(int increment) {
      return updater.retain(this, increment);
   }

   public ReferenceCounted touch() {
      return this.touch((Object)null);
   }

   public boolean release() {
      return this.handleRelease(updater.release(this));
   }

   public boolean release(int decrement) {
      return this.handleRelease(updater.release(this, decrement));
   }

   private boolean handleRelease(boolean result) {
      if (result) {
         this.deallocate();
      }

      return result;
   }

   protected abstract void deallocate();
}
