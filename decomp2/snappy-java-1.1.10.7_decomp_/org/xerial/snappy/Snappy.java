package org.xerial.snappy;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Properties;

public class Snappy {
   private static SnappyApi impl;

   public static void cleanUp() {
      SnappyLoader.cleanUpExtractedNativeLib();
      SnappyLoader.setSnappyApi((SnappyApi)null);
   }

   static void init() {
      try {
         impl = SnappyLoader.loadSnappyApi();
      } catch (Exception var1) {
         throw new ExceptionInInitializerError(var1);
      }
   }

   public static void arrayCopy(Object var0, int var1, int var2, Object var3, int var4) throws IOException {
      impl.arrayCopy(var0, var1, var2, var3, var4);
   }

   public static byte[] compress(byte[] var0) throws IOException {
      return rawCompress(var0, var0.length);
   }

   public static int compress(byte[] var0, int var1, int var2, byte[] var3, int var4) throws IOException {
      return rawCompress(var0, var1, var2, var3, var4);
   }

   public static int compress(ByteBuffer var0, ByteBuffer var1) throws IOException {
      if (!var0.isDirect()) {
         throw new SnappyError(SnappyErrorCode.NOT_A_DIRECT_BUFFER, "input is not a direct buffer");
      } else if (!var1.isDirect()) {
         throw new SnappyError(SnappyErrorCode.NOT_A_DIRECT_BUFFER, "destination is not a direct buffer");
      } else {
         int var2 = var0.position();
         int var3 = var0.remaining();
         int var4 = var1.position();
         int var5 = impl.rawCompress(var0, var2, var3, var1, var4);
         ((Buffer)var1).limit(var4 + var5);
         return var5;
      }
   }

   public static byte[] compress(char[] var0) throws IOException {
      int var1 = var0.length * 2;
      if (var1 < var0.length) {
         throw new SnappyError(SnappyErrorCode.TOO_LARGE_INPUT, "input array size is too large: " + var0.length);
      } else {
         return rawCompress(var0, var1);
      }
   }

   public static byte[] compress(double[] var0) throws IOException {
      int var1 = var0.length * 8;
      if (var1 < var0.length) {
         throw new SnappyError(SnappyErrorCode.TOO_LARGE_INPUT, "input array size is too large: " + var0.length);
      } else {
         return rawCompress(var0, var1);
      }
   }

   public static byte[] compress(float[] var0) throws IOException {
      int var1 = var0.length * 4;
      if (var1 < var0.length) {
         throw new SnappyError(SnappyErrorCode.TOO_LARGE_INPUT, "input array size is too large: " + var0.length);
      } else {
         return rawCompress(var0, var1);
      }
   }

   public static byte[] compress(int[] var0) throws IOException {
      int var1 = var0.length * 4;
      if (var1 < var0.length) {
         throw new SnappyError(SnappyErrorCode.TOO_LARGE_INPUT, "input array size is too large: " + var0.length);
      } else {
         return rawCompress(var0, var1);
      }
   }

   public static byte[] compress(long[] var0) throws IOException {
      int var1 = var0.length * 8;
      if (var1 < var0.length) {
         throw new SnappyError(SnappyErrorCode.TOO_LARGE_INPUT, "input array size is too large: " + var0.length);
      } else {
         return rawCompress(var0, var1);
      }
   }

   public static byte[] compress(short[] var0) throws IOException {
      int var1 = var0.length * 2;
      if (var1 < var0.length) {
         throw new SnappyError(SnappyErrorCode.TOO_LARGE_INPUT, "input array size is too large: " + var0.length);
      } else {
         return rawCompress(var0, var1);
      }
   }

   public static byte[] compress(String var0) throws IOException {
      try {
         return compress(var0, "UTF-8");
      } catch (UnsupportedEncodingException var2) {
         throw new IllegalStateException("UTF-8 encoder is not found");
      }
   }

   public static byte[] compress(String var0, String var1) throws UnsupportedEncodingException, IOException {
      byte[] var2 = var0.getBytes(var1);
      return compress(var2);
   }

   public static byte[] compress(String var0, Charset var1) throws IOException {
      byte[] var2 = var0.getBytes(var1);
      return compress(var2);
   }

   public static String getNativeLibraryVersion() {
      URL var0 = SnappyLoader.class.getResource("/org/xerial/snappy/VERSION");
      String var1 = "unknown";

      try {
         if (var0 != null) {
            InputStream var2 = null;

            try {
               Properties var3 = new Properties();
               var2 = var0.openStream();
               var3.load(var2);
               var1 = var3.getProperty("version", var1);
               if (var1.equals("unknown")) {
                  var1 = var3.getProperty("SNAPPY_VERSION", var1);
               }

               var1 = var1.trim().replaceAll("[^0-9\\.]", "");
            } finally {
               if (var2 != null) {
                  var2.close();
               }

            }
         }
      } catch (IOException var8) {
         var8.printStackTrace();
      }

      return var1;
   }

   public static boolean isValidCompressedBuffer(byte[] var0, int var1, int var2) throws IOException {
      if (var0 == null) {
         throw new NullPointerException("input is null");
      } else {
         return impl.isValidCompressedBuffer((Object)var0, var1, var2);
      }
   }

   public static boolean isValidCompressedBuffer(byte[] var0) throws IOException {
      return isValidCompressedBuffer(var0, 0, var0.length);
   }

   public static boolean isValidCompressedBuffer(ByteBuffer var0) throws IOException {
      return impl.isValidCompressedBuffer(var0, var0.position(), var0.remaining());
   }

   public static boolean isValidCompressedBuffer(long var0, long var2, long var4) throws IOException {
      return impl.isValidCompressedBuffer(var0, var2, var4);
   }

   public static int maxCompressedLength(int var0) {
      return impl.maxCompressedLength(var0);
   }

   public static long rawCompress(long var0, long var2, long var4) throws IOException {
      return impl.rawCompress(var0, var2, var4);
   }

   public static long rawUncompress(long var0, long var2, long var4) throws IOException {
      return impl.rawUncompress(var0, var2, var4);
   }

   public static byte[] rawCompress(Object var0, int var1) throws IOException {
      byte[] var2 = new byte[maxCompressedLength(var1)];
      int var3 = impl.rawCompress((Object)var0, 0, var1, (Object)var2, 0);
      byte[] var4 = new byte[var3];
      System.arraycopy(var2, 0, var4, 0, var3);
      return var4;
   }

   public static int rawCompress(Object var0, int var1, int var2, byte[] var3, int var4) throws IOException {
      if (var0 != null && var3 != null) {
         int var5 = impl.rawCompress((Object)var0, var1, var2, (Object)var3, var4);
         return var5;
      } else {
         throw new NullPointerException("input or output is null");
      }
   }

   public static int rawUncompress(byte[] var0, int var1, int var2, Object var3, int var4) throws IOException {
      if (var0 != null && var3 != null) {
         return impl.rawUncompress((Object)var0, var1, var2, (Object)var3, var4);
      } else {
         throw new NullPointerException("input or output is null");
      }
   }

   public static byte[] uncompress(byte[] var0) throws IOException {
      byte[] var1 = new byte[uncompressedLength(var0)];
      uncompress(var0, 0, var0.length, var1, 0);
      return var1;
   }

   public static int uncompress(byte[] var0, int var1, int var2, byte[] var3, int var4) throws IOException {
      return rawUncompress(var0, var1, var2, var3, var4);
   }

   public static int uncompress(ByteBuffer var0, ByteBuffer var1) throws IOException {
      if (!var0.isDirect()) {
         throw new SnappyError(SnappyErrorCode.NOT_A_DIRECT_BUFFER, "input is not a direct buffer");
      } else if (!var1.isDirect()) {
         throw new SnappyError(SnappyErrorCode.NOT_A_DIRECT_BUFFER, "destination is not a direct buffer");
      } else {
         int var2 = var0.position();
         int var3 = var0.remaining();
         int var4 = var1.position();
         int var5 = impl.rawUncompress(var0, var2, var3, var1, var4);
         var1.limit(var4 + var5);
         return var5;
      }
   }

   public static char[] uncompressCharArray(byte[] var0) throws IOException {
      return uncompressCharArray(var0, 0, var0.length);
   }

   public static char[] uncompressCharArray(byte[] var0, int var1, int var2) throws IOException {
      int var3 = uncompressedLength(var0, var1, var2);
      char[] var4 = new char[var3 / 2];
      impl.rawUncompress((Object)var0, var1, var2, (Object)var4, 0);
      return var4;
   }

   public static double[] uncompressDoubleArray(byte[] var0) throws IOException {
      return uncompressDoubleArray(var0, 0, var0.length);
   }

   public static double[] uncompressDoubleArray(byte[] var0, int var1, int var2) throws IOException {
      int var3 = uncompressedLength(var0, var1, var2);
      double[] var4 = new double[var3 / 8];
      impl.rawUncompress((Object)var0, var1, var2, (Object)var4, 0);
      return var4;
   }

   public static int uncompressedLength(byte[] var0) throws IOException {
      return impl.uncompressedLength((Object)var0, 0, var0.length);
   }

   public static int uncompressedLength(byte[] var0, int var1, int var2) throws IOException {
      if (var0 == null) {
         throw new NullPointerException("input is null");
      } else {
         return impl.uncompressedLength((Object)var0, var1, var2);
      }
   }

   public static int uncompressedLength(ByteBuffer var0) throws IOException {
      if (!var0.isDirect()) {
         throw new SnappyError(SnappyErrorCode.NOT_A_DIRECT_BUFFER, "input is not a direct buffer");
      } else {
         return impl.uncompressedLength(var0, var0.position(), var0.remaining());
      }
   }

   public static long uncompressedLength(long var0, long var2) throws IOException {
      return impl.uncompressedLength(var0, var2);
   }

   public static float[] uncompressFloatArray(byte[] var0) throws IOException {
      return uncompressFloatArray(var0, 0, var0.length);
   }

   public static float[] uncompressFloatArray(byte[] var0, int var1, int var2) throws IOException {
      int var3 = uncompressedLength(var0, var1, var2);
      float[] var4 = new float[var3 / 4];
      impl.rawUncompress((Object)var0, var1, var2, (Object)var4, 0);
      return var4;
   }

   public static int[] uncompressIntArray(byte[] var0) throws IOException {
      return uncompressIntArray(var0, 0, var0.length);
   }

   public static int[] uncompressIntArray(byte[] var0, int var1, int var2) throws IOException {
      int var3 = uncompressedLength(var0, var1, var2);
      int[] var4 = new int[var3 / 4];
      impl.rawUncompress((Object)var0, var1, var2, (Object)var4, 0);
      return var4;
   }

   public static long[] uncompressLongArray(byte[] var0) throws IOException {
      return uncompressLongArray(var0, 0, var0.length);
   }

   public static long[] uncompressLongArray(byte[] var0, int var1, int var2) throws IOException {
      int var3 = uncompressedLength(var0, var1, var2);
      long[] var4 = new long[var3 / 8];
      impl.rawUncompress((Object)var0, var1, var2, (Object)var4, 0);
      return var4;
   }

   public static short[] uncompressShortArray(byte[] var0) throws IOException {
      return uncompressShortArray(var0, 0, var0.length);
   }

   public static short[] uncompressShortArray(byte[] var0, int var1, int var2) throws IOException {
      int var3 = uncompressedLength(var0, var1, var2);
      short[] var4 = new short[var3 / 2];
      impl.rawUncompress((Object)var0, var1, var2, (Object)var4, 0);
      return var4;
   }

   public static String uncompressString(byte[] var0) throws IOException {
      try {
         return uncompressString(var0, "UTF-8");
      } catch (UnsupportedEncodingException var2) {
         throw new IllegalStateException("UTF-8 decoder is not found");
      }
   }

   public static String uncompressString(byte[] var0, int var1, int var2) throws IOException {
      try {
         return uncompressString(var0, var1, var2, "UTF-8");
      } catch (UnsupportedEncodingException var4) {
         throw new IllegalStateException("UTF-8 decoder is not found");
      }
   }

   public static String uncompressString(byte[] var0, int var1, int var2, String var3) throws IOException, UnsupportedEncodingException {
      byte[] var4 = new byte[uncompressedLength(var0, var1, var2)];
      uncompress(var0, var1, var2, var4, 0);
      return new String(var4, var3);
   }

   public static String uncompressString(byte[] var0, int var1, int var2, Charset var3) throws IOException, UnsupportedEncodingException {
      byte[] var4 = new byte[uncompressedLength(var0, var1, var2)];
      uncompress(var0, var1, var2, var4, 0);
      return new String(var4, var3);
   }

   public static String uncompressString(byte[] var0, String var1) throws IOException, UnsupportedEncodingException {
      byte[] var2 = uncompress(var0);
      return new String(var2, var1);
   }

   public static String uncompressString(byte[] var0, Charset var1) throws IOException, UnsupportedEncodingException {
      byte[] var2 = uncompress(var0);
      return new String(var2, var1);
   }

   static {
      init();
   }
}
