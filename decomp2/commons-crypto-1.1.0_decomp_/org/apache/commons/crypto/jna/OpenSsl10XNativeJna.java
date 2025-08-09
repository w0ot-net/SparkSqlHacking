package org.apache.commons.crypto.jna;

import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.ptr.PointerByReference;
import java.nio.ByteBuffer;

class OpenSsl10XNativeJna {
   static final boolean INIT_OK;
   static final Throwable INIT_ERROR;

   public static native NativeLong SSLeay();

   public static native String SSLeay_version(int var0);

   public static native void ERR_load_crypto_strings();

   public static native NativeLong ERR_peek_error();

   public static native String ERR_error_string(NativeLong var0, char[] var1);

   public static native PointerByReference EVP_CIPHER_CTX_new();

   public static native void EVP_CIPHER_CTX_init(PointerByReference var0);

   public static native int EVP_CIPHER_CTX_set_padding(PointerByReference var0, int var1);

   public static native PointerByReference EVP_aes_128_cbc();

   public static native PointerByReference EVP_aes_128_ctr();

   public static native PointerByReference EVP_aes_192_cbc();

   public static native PointerByReference EVP_aes_192_ctr();

   public static native PointerByReference EVP_aes_256_cbc();

   public static native PointerByReference EVP_aes_256_ctr();

   public static native int EVP_CipherInit_ex(PointerByReference var0, PointerByReference var1, PointerByReference var2, byte[] var3, byte[] var4, int var5);

   public static native int EVP_CipherUpdate(PointerByReference var0, ByteBuffer var1, int[] var2, ByteBuffer var3, int var4);

   public static native int EVP_CipherFinal_ex(PointerByReference var0, ByteBuffer var1, int[] var2);

   public static native void EVP_CIPHER_CTX_free(PointerByReference var0);

   public static native void EVP_CIPHER_CTX_cleanup(PointerByReference var0);

   public static native PointerByReference RAND_get_rand_method();

   public static native PointerByReference RAND_SSLeay();

   public static native int RAND_bytes(ByteBuffer var0, int var1);

   public static native int ENGINE_finish(PointerByReference var0);

   public static native int ENGINE_free(PointerByReference var0);

   public static native int ENGINE_cleanup();

   public static native int ENGINE_init(PointerByReference var0);

   public static native int ENGINE_set_default(PointerByReference var0, int var1);

   public static native PointerByReference ENGINE_by_id(String var0);

   public static native void ENGINE_load_rdrand();

   static {
      boolean ok = false;
      Throwable thrown = null;

      try {
         String libName = System.getProperty("commons.crypto." + OpenSsl10XNativeJna.class.getSimpleName(), "crypto");
         OpenSslJna.debug("Native.register('%s')%n", libName);
         Native.register(libName);
         ok = true;
      } catch (Exception e) {
         thrown = e;
      } catch (UnsatisfiedLinkError e) {
         thrown = e;
      } finally {
         INIT_OK = ok;
         INIT_ERROR = thrown;
      }

   }
}
