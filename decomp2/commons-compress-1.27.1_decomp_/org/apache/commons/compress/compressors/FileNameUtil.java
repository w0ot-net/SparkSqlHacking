package org.apache.commons.compress.compressors;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class FileNameUtil {
   private final Map compressSuffix = new HashMap();
   private final Map uncompressSuffix;
   private final int longestCompressedSuffix;
   private final int shortestCompressedSuffix;
   private final int longestUncompressedSuffix;
   private final int shortestUncompressedSuffix;
   private final String defaultExtension;

   public FileNameUtil(Map uncompressSuffix, String defaultExtension) {
      this.uncompressSuffix = Collections.unmodifiableMap(uncompressSuffix);
      int lc = Integer.MIN_VALUE;
      int sc = Integer.MAX_VALUE;
      int lu = Integer.MIN_VALUE;
      int su = Integer.MAX_VALUE;

      for(Map.Entry ent : uncompressSuffix.entrySet()) {
         int cl = ((String)ent.getKey()).length();
         if (cl > lc) {
            lc = cl;
         }

         if (cl < sc) {
            sc = cl;
         }

         String u = (String)ent.getValue();
         int ul = u.length();
         if (ul > 0) {
            this.compressSuffix.computeIfAbsent(u, (k) -> (String)ent.getKey());
            if (ul > lu) {
               lu = ul;
            }

            if (ul < su) {
               su = ul;
            }
         }
      }

      this.longestCompressedSuffix = lc;
      this.longestUncompressedSuffix = lu;
      this.shortestCompressedSuffix = sc;
      this.shortestUncompressedSuffix = su;
      this.defaultExtension = defaultExtension;
   }

   /** @deprecated */
   @Deprecated
   public String getCompressedFilename(String fileName) {
      return this.getCompressedFileName(fileName);
   }

   public String getCompressedFileName(String fileName) {
      String lower = fileName.toLowerCase(Locale.ROOT);
      int n = lower.length();

      for(int i = this.shortestUncompressedSuffix; i <= this.longestUncompressedSuffix && i < n; ++i) {
         String suffix = (String)this.compressSuffix.get(lower.substring(n - i));
         if (suffix != null) {
            return fileName.substring(0, n - i) + suffix;
         }
      }

      return fileName + this.defaultExtension;
   }

   /** @deprecated */
   @Deprecated
   public String getUncompressedFilename(String fileName) {
      return this.getUncompressedFileName(fileName);
   }

   public String getUncompressedFileName(String fileName) {
      String lower = fileName.toLowerCase(Locale.ROOT);
      int n = lower.length();

      for(int i = this.shortestCompressedSuffix; i <= this.longestCompressedSuffix && i < n; ++i) {
         String suffix = (String)this.uncompressSuffix.get(lower.substring(n - i));
         if (suffix != null) {
            return fileName.substring(0, n - i) + suffix;
         }
      }

      return fileName;
   }

   /** @deprecated */
   @Deprecated
   public boolean isCompressedFilename(String fileName) {
      return this.isCompressedFileName(fileName);
   }

   public boolean isCompressedFileName(String fileName) {
      String lower = fileName.toLowerCase(Locale.ROOT);
      int n = lower.length();

      for(int i = this.shortestCompressedSuffix; i <= this.longestCompressedSuffix && i < n; ++i) {
         if (this.uncompressSuffix.containsKey(lower.substring(n - i))) {
            return true;
         }
      }

      return false;
   }
}
