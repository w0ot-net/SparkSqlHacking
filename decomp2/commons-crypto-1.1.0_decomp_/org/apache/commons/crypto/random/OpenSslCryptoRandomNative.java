package org.apache.commons.crypto.random;

class OpenSslCryptoRandomNative {
   private OpenSslCryptoRandomNative() {
   }

   public static native void initSR();

   public static native boolean nextRandBytes(byte[] var0);
}
