package org.apache.commons.compress.utils;

import java.io.File;
import java.nio.file.Path;
import org.apache.commons.io.FilenameUtils;

public class FileNameUtils {
   public static String getBaseName(Path path) {
      if (path == null) {
         return null;
      } else {
         Path fileName = path.getFileName();
         return fileName != null ? FilenameUtils.removeExtension(fileName.toString()) : null;
      }
   }

   /** @deprecated */
   @Deprecated
   public static String getBaseName(String fileName) {
      return fileName == null ? null : FilenameUtils.removeExtension((new File(fileName)).getName());
   }

   public static String getExtension(Path path) {
      if (path == null) {
         return null;
      } else {
         Path fileName = path.getFileName();
         return fileName != null ? FilenameUtils.getExtension(fileName.toString()) : null;
      }
   }

   /** @deprecated */
   @Deprecated
   public static String getExtension(String fileName) {
      return FilenameUtils.getExtension(fileName);
   }
}
