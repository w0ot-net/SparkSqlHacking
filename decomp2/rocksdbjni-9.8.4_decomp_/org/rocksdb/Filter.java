package org.rocksdb;

public abstract class Filter extends RocksObject {
   protected Filter(long var1) {
      super(var1);
   }

   protected void disposeInternal() {
      this.disposeInternal(this.nativeHandle_);
   }

   protected final void disposeInternal(long var1) {
      disposeInternalJni(var1);
   }

   private static native void disposeInternalJni(long var0);
}
