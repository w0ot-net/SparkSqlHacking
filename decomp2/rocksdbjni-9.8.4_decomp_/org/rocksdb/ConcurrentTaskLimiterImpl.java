package org.rocksdb;

public class ConcurrentTaskLimiterImpl extends ConcurrentTaskLimiter {
   public ConcurrentTaskLimiterImpl(String var1, int var2) {
      super(newConcurrentTaskLimiterImpl0(var1, var2));
   }

   public String name() {
      assert this.isOwningHandle();

      return name(this.nativeHandle_);
   }

   public ConcurrentTaskLimiter setMaxOutstandingTask(int var1) {
      assert this.isOwningHandle();

      setMaxOutstandingTask(this.nativeHandle_, var1);
      return this;
   }

   public ConcurrentTaskLimiter resetMaxOutstandingTask() {
      assert this.isOwningHandle();

      resetMaxOutstandingTask(this.nativeHandle_);
      return this;
   }

   public int outstandingTask() {
      assert this.isOwningHandle();

      return outstandingTask(this.nativeHandle_);
   }

   private static native long newConcurrentTaskLimiterImpl0(String var0, int var1);

   private static native String name(long var0);

   private static native void setMaxOutstandingTask(long var0, int var2);

   private static native void resetMaxOutstandingTask(long var0);

   private static native int outstandingTask(long var0);

   protected final void disposeInternal(long var1) {
      disposeInternalJni(var1);
   }

   private static native void disposeInternalJni(long var0);
}
