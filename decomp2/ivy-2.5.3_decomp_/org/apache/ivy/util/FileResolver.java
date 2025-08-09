package org.apache.ivy.util;

import java.io.File;

public interface FileResolver {
   FileResolver DEFAULT = new FileResolver() {
      public File resolveFile(String path, String filename) {
         return new File(path);
      }
   };

   File resolveFile(String var1, String var2);
}
