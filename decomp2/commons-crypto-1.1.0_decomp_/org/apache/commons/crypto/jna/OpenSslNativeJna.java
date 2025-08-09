package org.apache.commons.crypto.jna;

import com.sun.jna.Function;
import com.sun.jna.NativeLibrary;
import com.sun.jna.NativeLong;
import com.sun.jna.ptr.PointerByReference;
import java.nio.ByteBuffer;

class OpenSslNativeJna {
   static final int OPENSSL_INIT_ENGINE_RDRAND = 512;
   static final int OOSL_JNA_ENCRYPT_MODE = 1;
   static final int OOSL_JNA_DECRYPT_MODE = 0;
   static final boolean INIT_OK;
   static final Throwable INIT_ERROR;
   public static final long VERSION;
   public static final long VERSION_1_0_X = 268435456L;
   public static final long VERSION_1_1_X = 269484032L;

   public static PointerByReference ENGINE_by_id(String string) {
      return VERSION == 269484032L ? OpenSsl11XNativeJna.ENGINE_by_id(string) : OpenSsl10XNativeJna.ENGINE_by_id(string);
   }

   public static void ENGINE_finish(PointerByReference rdrandEngine) {
      if (VERSION == 269484032L) {
         OpenSsl11XNativeJna.ENGINE_finish(rdrandEngine);
      } else {
         OpenSsl10XNativeJna.ENGINE_finish(rdrandEngine);
      }

   }

   public static void ENGINE_free(PointerByReference rdrandEngine) {
      if (VERSION == 269484032L) {
         OpenSsl11XNativeJna.ENGINE_free(rdrandEngine);
      } else {
         OpenSsl10XNativeJna.ENGINE_free(rdrandEngine);
      }

   }

   public static int ENGINE_init(PointerByReference rdrandEngine) {
      return VERSION == 269484032L ? OpenSsl11XNativeJna.ENGINE_init(rdrandEngine) : OpenSsl10XNativeJna.ENGINE_init(rdrandEngine);
   }

   public static int ENGINE_set_default(PointerByReference rdrandEngine, int eNGINE_METHOD_RAND) {
      return VERSION == 269484032L ? OpenSsl11XNativeJna.ENGINE_set_default(rdrandEngine, eNGINE_METHOD_RAND) : OpenSsl10XNativeJna.ENGINE_set_default(rdrandEngine, eNGINE_METHOD_RAND);
   }

   public static String ERR_error_string(NativeLong err, Object object) {
      return VERSION == 269484032L ? OpenSsl11XNativeJna.ERR_error_string(err, (char[])null) : OpenSsl10XNativeJna.ERR_error_string(err, (char[])null);
   }

   public static NativeLong ERR_peek_error() {
      return VERSION == 269484032L ? OpenSsl11XNativeJna.ERR_peek_error() : OpenSsl10XNativeJna.ERR_peek_error();
   }

   public static PointerByReference EVP_aes_128_cbc() {
      return VERSION == 269484032L ? OpenSsl11XNativeJna.EVP_aes_128_cbc() : OpenSsl10XNativeJna.EVP_aes_128_cbc();
   }

   public static PointerByReference EVP_aes_128_ctr() {
      return VERSION == 269484032L ? OpenSsl11XNativeJna.EVP_aes_128_ctr() : OpenSsl10XNativeJna.EVP_aes_128_ctr();
   }

   public static PointerByReference EVP_aes_192_cbc() {
      return VERSION == 269484032L ? OpenSsl11XNativeJna.EVP_aes_192_cbc() : OpenSsl10XNativeJna.EVP_aes_192_cbc();
   }

   public static PointerByReference EVP_aes_192_ctr() {
      return VERSION == 269484032L ? OpenSsl11XNativeJna.EVP_aes_192_ctr() : OpenSsl10XNativeJna.EVP_aes_192_ctr();
   }

   public static PointerByReference EVP_aes_256_cbc() {
      return VERSION == 269484032L ? OpenSsl11XNativeJna.EVP_aes_256_cbc() : OpenSsl10XNativeJna.EVP_aes_256_cbc();
   }

   public static PointerByReference EVP_aes_256_ctr() {
      return VERSION == 269484032L ? OpenSsl11XNativeJna.EVP_aes_256_ctr() : OpenSsl10XNativeJna.EVP_aes_256_ctr();
   }

   public static void EVP_CIPHER_CTX_free(PointerByReference context) {
      if (VERSION == 269484032L) {
         OpenSsl11XNativeJna.EVP_CIPHER_CTX_free(context);
      } else {
         OpenSsl10XNativeJna.EVP_CIPHER_CTX_free(context);
      }

   }

   public static PointerByReference EVP_CIPHER_CTX_new() {
      return VERSION == 269484032L ? OpenSsl11XNativeJna.EVP_CIPHER_CTX_new() : OpenSsl10XNativeJna.EVP_CIPHER_CTX_new();
   }

   public static void EVP_CIPHER_CTX_set_padding(PointerByReference context, int padding) {
      if (VERSION == 269484032L) {
         OpenSsl11XNativeJna.EVP_CIPHER_CTX_set_padding(context, padding);
      } else {
         OpenSsl10XNativeJna.EVP_CIPHER_CTX_set_padding(context, padding);
      }

   }

   public static int EVP_CipherFinal_ex(PointerByReference context, ByteBuffer outBuffer, int[] outlen) {
      return VERSION == 269484032L ? OpenSsl11XNativeJna.EVP_CipherFinal_ex(context, outBuffer, outlen) : OpenSsl10XNativeJna.EVP_CipherFinal_ex(context, outBuffer, outlen);
   }

   public static int EVP_CipherInit_ex(PointerByReference context, PointerByReference algo, Object object, byte[] encoded, byte[] iv, int cipherMode) {
      return VERSION == 269484032L ? OpenSsl11XNativeJna.EVP_CipherInit_ex(context, algo, (PointerByReference)null, encoded, iv, cipherMode) : OpenSsl10XNativeJna.EVP_CipherInit_ex(context, algo, (PointerByReference)null, encoded, iv, cipherMode);
   }

   public static int EVP_CipherUpdate(PointerByReference context, ByteBuffer outBuffer, int[] outlen, ByteBuffer inBuffer, int remaining) {
      return VERSION == 269484032L ? OpenSsl11XNativeJna.EVP_CipherUpdate(context, outBuffer, outlen, inBuffer, remaining) : OpenSsl10XNativeJna.EVP_CipherUpdate(context, outBuffer, outlen, inBuffer, remaining);
   }

   public static int RAND_bytes(ByteBuffer buf, int length) {
      return VERSION == 269484032L ? OpenSsl11XNativeJna.RAND_bytes(buf, length) : OpenSsl10XNativeJna.RAND_bytes(buf, length);
   }

   public static PointerByReference RAND_get_rand_method() {
      return VERSION == 269484032L ? OpenSsl11XNativeJna.RAND_get_rand_method() : OpenSsl10XNativeJna.RAND_get_rand_method();
   }

   public static PointerByReference RAND_SSLeay() {
      return VERSION == 269484032L ? null : OpenSsl10XNativeJna.RAND_SSLeay();
   }

   public static String OpenSSLVersion(int i) {
      return VERSION == 269484032L ? OpenSsl11XNativeJna.OpenSSL_version(i) : OpenSsl10XNativeJna.SSLeay_version(i);
   }

   public static void ENGINE_load_rdrand() {
      if (VERSION != 269484032L) {
         OpenSsl10XNativeJna.ENGINE_load_rdrand();
      }
   }

   public static void ENGINE_cleanup() {
      if (VERSION != 269484032L) {
         OpenSsl10XNativeJna.ENGINE_cleanup();
      }
   }

   public static void EVP_CIPHER_CTX_cleanup(PointerByReference context) {
      if (VERSION != 269484032L) {
         OpenSsl10XNativeJna.EVP_CIPHER_CTX_cleanup(context);
      }
   }

   static {
      String libraryName = System.getProperty("commons.crypto." + OpenSslNativeJna.class.getSimpleName(), "crypto");
      OpenSslJna.debug("NativeLibrary.getInstance('%s')%n", libraryName);
      NativeLibrary crypto = NativeLibrary.getInstance(libraryName);
      Function version = null;

      try {
         version = crypto.getFunction("SSLeay");
      } catch (UnsatisfiedLinkError var4) {
      }

      if (version == null) {
         VERSION = 269484032L;
      } else {
         VERSION = 268435456L;
      }

      if (VERSION == 269484032L) {
         INIT_OK = OpenSsl11XNativeJna.INIT_OK;
      } else {
         INIT_OK = OpenSsl10XNativeJna.INIT_OK;
      }

      if (INIT_OK) {
         INIT_ERROR = null;
      } else if (VERSION == 269484032L) {
         INIT_ERROR = OpenSsl11XNativeJna.INIT_ERROR;
      } else {
         INIT_ERROR = OpenSsl10XNativeJna.INIT_ERROR;
      }

   }
}
