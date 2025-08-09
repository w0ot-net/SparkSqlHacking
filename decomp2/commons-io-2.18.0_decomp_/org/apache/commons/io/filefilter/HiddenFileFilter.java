package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class HiddenFileFilter extends AbstractFileFilter implements Serializable {
   public static final IOFileFilter HIDDEN = new HiddenFileFilter();
   private static final long serialVersionUID = 8930842316112759062L;
   public static final IOFileFilter VISIBLE;

   protected HiddenFileFilter() {
   }

   public boolean accept(File file) {
      return file == null || file.isHidden();
   }

   public FileVisitResult accept(Path file, BasicFileAttributes attributes) {
      return this.get(() -> this.toFileVisitResult(file == null || Files.isHidden(file)));
   }

   static {
      VISIBLE = HIDDEN.negate();
   }
}
