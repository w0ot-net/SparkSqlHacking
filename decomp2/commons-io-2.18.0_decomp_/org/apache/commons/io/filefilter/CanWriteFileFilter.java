package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class CanWriteFileFilter extends AbstractFileFilter implements Serializable {
   public static final IOFileFilter CAN_WRITE = new CanWriteFileFilter();
   public static final IOFileFilter CANNOT_WRITE;
   private static final long serialVersionUID = 5132005214688990379L;

   protected CanWriteFileFilter() {
   }

   public boolean accept(File file) {
      return file != null && file.canWrite();
   }

   public FileVisitResult accept(Path file, BasicFileAttributes attributes) {
      return this.toFileVisitResult(file != null && Files.isWritable(file));
   }

   static {
      CANNOT_WRITE = CAN_WRITE.negate();
   }
}
