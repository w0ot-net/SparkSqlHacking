package org.codehaus.commons.compiler.util.resource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public abstract class FileResourceCreator implements ResourceCreator {
   public final OutputStream createResource(String resourceName) throws IOException {
      File file = this.getFile(resourceName);
      File dir = file.getParentFile();
      if (dir != null && !dir.isDirectory() && !dir.mkdirs()) {
         throw new IOException("Cannot create directory for class file \"" + file + "\"");
      } else {
         return new FileOutputStream(file);
      }
   }

   public final boolean deleteResource(String resourceName) {
      return this.getFile(resourceName).delete();
   }

   protected abstract File getFile(String var1);
}
