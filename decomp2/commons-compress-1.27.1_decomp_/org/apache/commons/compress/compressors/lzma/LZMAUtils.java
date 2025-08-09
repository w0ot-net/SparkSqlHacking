package org.apache.commons.compress.compressors.lzma;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.compress.compressors.FileNameUtil;
import org.apache.commons.compress.utils.OsgiUtils;

public class LZMAUtils {
   private static final FileNameUtil fileNameUtil;
   private static final byte[] HEADER_MAGIC = new byte[]{93, 0, 0};
   private static volatile CachedAvailability cachedLZMAAvailability;

   static CachedAvailability getCachedLZMAAvailability() {
      return cachedLZMAAvailability;
   }

   /** @deprecated */
   @Deprecated
   public static String getCompressedFilename(String fileName) {
      return fileNameUtil.getCompressedFileName(fileName);
   }

   public static String getCompressedFileName(String fileName) {
      return fileNameUtil.getCompressedFileName(fileName);
   }

   /** @deprecated */
   @Deprecated
   public static String getUncompressedFilename(String fileName) {
      return fileNameUtil.getUncompressedFileName(fileName);
   }

   public static String getUncompressedFileName(String fileName) {
      return fileNameUtil.getUncompressedFileName(fileName);
   }

   private static boolean internalIsLZMACompressionAvailable() {
      try {
         LZMACompressorInputStream.matches((byte[])null, 0);
         return true;
      } catch (NoClassDefFoundError var1) {
         return false;
      }
   }

   /** @deprecated */
   @Deprecated
   public static boolean isCompressedFilename(String fileName) {
      return fileNameUtil.isCompressedFileName(fileName);
   }

   public static boolean isCompressedFileName(String fileName) {
      return fileNameUtil.isCompressedFileName(fileName);
   }

   public static boolean isLZMACompressionAvailable() {
      CachedAvailability cachedResult = cachedLZMAAvailability;
      if (cachedResult != LZMAUtils.CachedAvailability.DONT_CACHE) {
         return cachedResult == LZMAUtils.CachedAvailability.CACHED_AVAILABLE;
      } else {
         return internalIsLZMACompressionAvailable();
      }
   }

   public static boolean matches(byte[] signature, int length) {
      if (length < HEADER_MAGIC.length) {
         return false;
      } else {
         for(int i = 0; i < HEADER_MAGIC.length; ++i) {
            if (signature[i] != HEADER_MAGIC[i]) {
               return false;
            }
         }

         return true;
      }
   }

   public static void setCacheLZMAAvailablity(boolean doCache) {
      if (!doCache) {
         cachedLZMAAvailability = LZMAUtils.CachedAvailability.DONT_CACHE;
      } else if (cachedLZMAAvailability == LZMAUtils.CachedAvailability.DONT_CACHE) {
         boolean hasLzma = internalIsLZMACompressionAvailable();
         cachedLZMAAvailability = hasLzma ? LZMAUtils.CachedAvailability.CACHED_AVAILABLE : LZMAUtils.CachedAvailability.CACHED_UNAVAILABLE;
      }

   }

   private LZMAUtils() {
   }

   static {
      Map<String, String> uncompressSuffix = new HashMap();
      uncompressSuffix.put(".lzma", "");
      uncompressSuffix.put("-lzma", "");
      fileNameUtil = new FileNameUtil(uncompressSuffix, ".lzma");
      cachedLZMAAvailability = LZMAUtils.CachedAvailability.DONT_CACHE;
      setCacheLZMAAvailablity(!OsgiUtils.isRunningInOsgiEnvironment());
   }

   static enum CachedAvailability {
      DONT_CACHE,
      CACHED_AVAILABLE,
      CACHED_UNAVAILABLE;

      // $FF: synthetic method
      private static CachedAvailability[] $values() {
         return new CachedAvailability[]{DONT_CACHE, CACHED_AVAILABLE, CACHED_UNAVAILABLE};
      }
   }
}
