package org.apache.commons.compress.compressors.bzip2;

import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.compress.compressors.FileNameUtil;

public abstract class BZip2Utils {
   private static final FileNameUtil fileNameUtil;

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

   /** @deprecated */
   @Deprecated
   public static boolean isCompressedFilename(String fileName) {
      return fileNameUtil.isCompressedFileName(fileName);
   }

   public static boolean isCompressedFileName(String fileName) {
      return fileNameUtil.isCompressedFileName(fileName);
   }

   private BZip2Utils() {
   }

   static {
      Map<String, String> uncompressSuffix = new LinkedHashMap();
      uncompressSuffix.put(".tar.bz2", ".tar");
      uncompressSuffix.put(".tbz2", ".tar");
      uncompressSuffix.put(".tbz", ".tar");
      uncompressSuffix.put(".bz2", "");
      uncompressSuffix.put(".bz", "");
      fileNameUtil = new FileNameUtil(uncompressSuffix, ".bz2");
   }
}
