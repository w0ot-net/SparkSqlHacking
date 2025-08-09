package org.rocksdb;

public class Slice extends AbstractSlice {
   private volatile boolean cleared;
   private volatile long internalBufferOffset;

   private Slice() {
      this.internalBufferOffset = 0L;
   }

   Slice(long var1) {
      this(var1, false);
   }

   Slice(long var1, boolean var3) {
      this.internalBufferOffset = 0L;
      this.setNativeHandle(var1, var3);
   }

   public Slice(String var1) {
      super(createNewSliceFromString(var1));
      this.internalBufferOffset = 0L;
   }

   public Slice(byte[] var1, int var2) {
      super(createNewSlice0(var1, var2));
      this.internalBufferOffset = 0L;
   }

   public Slice(byte[] var1) {
      super(createNewSlice1(var1));
      this.internalBufferOffset = 0L;
   }

   public void clear() {
      clear0(this.getNativeHandle(), !this.cleared, this.internalBufferOffset);
      this.cleared = true;
   }

   public void removePrefix(int var1) {
      removePrefix0(this.getNativeHandle(), var1);
      this.internalBufferOffset += (long)var1;
   }

   protected void disposeInternal() {
      long var1 = this.getNativeHandle();
      if (!this.cleared) {
         disposeInternalBuf(var1, this.internalBufferOffset);
      }

      super.disposeInternal(var1);
   }

   protected final native byte[] data0(long var1);

   private static native long createNewSlice0(byte[] var0, int var1);

   private static native long createNewSlice1(byte[] var0);

   private static native void clear0(long var0, boolean var2, long var3);

   private static native void removePrefix0(long var0, int var2);

   private static native void disposeInternalBuf(long var0, long var2);
}
