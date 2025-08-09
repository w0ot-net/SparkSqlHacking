package org.rocksdb;

public class RocksMemEnv extends Env {
   public RocksMemEnv(Env var1) {
      super(createMemEnv(var1.nativeHandle_));
   }

   private static native long createMemEnv(long var0);

   protected final void disposeInternal(long var1) {
      disposeInternalJni(var1);
   }

   private static native void disposeInternalJni(long var0);
}
