package org.apache.logging.log4j.core.appender.rolling;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.File;
import java.util.Objects;
import org.apache.logging.log4j.core.appender.rolling.action.Action;
import org.apache.logging.log4j.core.appender.rolling.action.CommonsCompressAction;
import org.apache.logging.log4j.core.appender.rolling.action.GzCompressAction;
import org.apache.logging.log4j.core.appender.rolling.action.ZipCompressAction;

public enum FileExtension {
   ZIP(".zip") {
      public Action createCompressAction(final String renameTo, final String compressedName, final boolean deleteSource, final int compressionLevel) {
         return new ZipCompressAction(this.source(renameTo), this.target(compressedName), deleteSource, compressionLevel);
      }
   },
   GZ(".gz") {
      public Action createCompressAction(final String renameTo, final String compressedName, final boolean deleteSource, final int compressionLevel) {
         return new GzCompressAction(this.source(renameTo), this.target(compressedName), deleteSource, compressionLevel);
      }
   },
   BZIP2(".bz2") {
      public Action createCompressAction(final String renameTo, final String compressedName, final boolean deleteSource, final int compressionLevel) {
         return new CommonsCompressAction("bzip2", this.source(renameTo), this.target(compressedName), deleteSource);
      }
   },
   DEFLATE(".deflate") {
      public Action createCompressAction(final String renameTo, final String compressedName, final boolean deleteSource, final int compressionLevel) {
         return new CommonsCompressAction("deflate", this.source(renameTo), this.target(compressedName), deleteSource);
      }
   },
   PACK200(".pack200") {
      public Action createCompressAction(final String renameTo, final String compressedName, final boolean deleteSource, final int compressionLevel) {
         return new CommonsCompressAction("pack200", this.source(renameTo), this.target(compressedName), deleteSource);
      }
   },
   XZ(".xz") {
      public Action createCompressAction(final String renameTo, final String compressedName, final boolean deleteSource, final int compressionLevel) {
         return new CommonsCompressAction("xz", this.source(renameTo), this.target(compressedName), deleteSource);
      }
   },
   ZSTD(".zst") {
      public Action createCompressAction(final String renameTo, final String compressedName, final boolean deleteSource, final int compressionLevel) {
         return new CommonsCompressAction("zstd", this.source(renameTo), this.target(compressedName), deleteSource);
      }
   };

   private final String extension;

   public static FileExtension lookup(final String fileExtension) {
      for(FileExtension ext : values()) {
         if (ext.isExtensionFor(fileExtension)) {
            return ext;
         }
      }

      return null;
   }

   public static FileExtension lookupForFile(final String fileName) {
      for(FileExtension ext : values()) {
         if (fileName.endsWith(ext.extension)) {
            return ext;
         }
      }

      return null;
   }

   private FileExtension(final String extension) {
      Objects.requireNonNull(extension, "extension");
      this.extension = extension;
   }

   public abstract Action createCompressAction(String renameTo, String compressedName, boolean deleteSource, int compressionLevel);

   public String getExtension() {
      return this.extension;
   }

   boolean isExtensionFor(final String s) {
      return s.endsWith(this.extension);
   }

   int length() {
      return this.extension.length();
   }

   @SuppressFBWarnings(
      value = {"PATH_TRAVERSAL_IN"},
      justification = "The name of the accessed files is based on a configuration value."
   )
   File source(final String fileName) {
      return new File(fileName);
   }

   @SuppressFBWarnings(
      value = {"PATH_TRAVERSAL_IN"},
      justification = "The name of the accessed files is based on a configuration value."
   )
   File target(final String fileName) {
      return new File(fileName);
   }

   // $FF: synthetic method
   private static FileExtension[] $values() {
      return new FileExtension[]{ZIP, GZ, BZIP2, DEFLATE, PACK200, XZ, ZSTD};
   }
}
