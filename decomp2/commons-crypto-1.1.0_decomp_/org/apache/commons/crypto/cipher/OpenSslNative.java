package org.apache.commons.crypto.cipher;

import java.nio.ByteBuffer;

class OpenSslNative {
   private OpenSslNative() {
   }

   public static native void initIDs();

   public static native long initContext(int var0, int var1);

   public static native long init(long var0, int var2, int var3, int var4, byte[] var5, byte[] var6);

   public static native int update(long var0, ByteBuffer var2, int var3, int var4, ByteBuffer var5, int var6, int var7);

   public static native int updateByteArray(long var0, byte[] var2, int var3, int var4, byte[] var5, int var6, int var7);

   public static native int updateByteArrayByteBuffer(long var0, byte[] var2, int var3, int var4, ByteBuffer var5, int var6, int var7);

   public static native int doFinal(long var0, ByteBuffer var2, int var3, int var4);

   public static native int doFinalByteArray(long var0, byte[] var2, int var3, int var4);

   public static native int ctrl(long var0, int var2, int var3, byte[] var4);

   public static native void clean(long var0);
}
