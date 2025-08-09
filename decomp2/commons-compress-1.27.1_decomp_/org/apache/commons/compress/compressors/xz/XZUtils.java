package org.apache.commons.compress.compressors.xz;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.compress.compressors.FileNameUtil;
import org.apache.commons.compress.utils.OsgiUtils;

public class XZUtils {
   private static final FileNameUtil fileNameUtil;
   private static final byte[] HEADER_MAGIC = new byte[]{-3, 55, 122, 88, 90, 0};
   private static volatile CachedAvailability cachedXZAvailability;

   static CachedAvailability getCachedXZAvailability() {
      return cachedXZAvailability;
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

   private static boolean internalIsXZCompressionAvailable() {
      try {
         XZCompressorInputStream.matches((byte[])null, 0);
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

   public static boolean isXZCompressionAvailable() {
      CachedAvailability cachedResult = cachedXZAvailability;
      if (cachedResult != XZUtils.CachedAvailability.DONT_CACHE) {
         return cachedResult == XZUtils.CachedAvailability.CACHED_AVAILABLE;
      } else {
         return internalIsXZCompressionAvailable();
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

   public static void setCacheXZAvailablity(boolean doCache) {
      if (!doCache) {
         cachedXZAvailability = XZUtils.CachedAvailability.DONT_CACHE;
      } else if (cachedXZAvailability == XZUtils.CachedAvailability.DONT_CACHE) {
         boolean hasXz = internalIsXZCompressionAvailable();
         cachedXZAvailability = hasXz ? XZUtils.CachedAvailability.CACHED_AVAILABLE : XZUtils.CachedAvailability.CACHED_UNAVAILABLE;
      }

   }

   private XZUtils() {
   }

   static {
      Map<String, String> uncompressSuffix = new HashMap();
      uncompressSuffix.put(".txz", ".tar");
      uncompressSuffix.put(".xz", "");
      uncompressSuffix.put("-xz", "");
      fileNameUtil = new FileNameUtil(uncompressSuffix, ".xz");
      cachedXZAvailability = XZUtils.CachedAvailability.DONT_CACHE;
      setCacheXZAvailablity(!OsgiUtils.isRunningInOsgiEnvironment());
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
