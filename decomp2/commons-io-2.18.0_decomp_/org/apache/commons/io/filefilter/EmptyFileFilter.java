package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.stream.Stream;
import org.apache.commons.io.IOUtils;

public class EmptyFileFilter extends AbstractFileFilter implements Serializable {
   public static final IOFileFilter EMPTY = new EmptyFileFilter();
   public static final IOFileFilter NOT_EMPTY;
   private static final long serialVersionUID = 3631422087512832211L;

   protected EmptyFileFilter() {
   }

   public boolean accept(File file) {
      if (file == null) {
         return true;
      } else if (file.isDirectory()) {
         File[] files = file.listFiles();
         return IOUtils.length((Object[])files) == 0;
      } else {
         return file.length() == 0L;
      }
   }

   public FileVisitResult accept(Path file, BasicFileAttributes attributes) {
      return file == null ? this.toFileVisitResult(true) : this.get(() -> {
         if (Files.isDirectory(file, new LinkOption[0])) {
            Stream<Path> stream = Files.list(file);

            FileVisitResult var3;
            try {
               var3 = this.toFileVisitResult(!stream.findFirst().isPresent());
            } catch (Throwable var6) {
               if (stream != null) {
                  try {
                     stream.close();
                  } catch (Throwable var5) {
                     var6.addSuppressed(var5);
                  }
               }

               throw var6;
            }

            if (stream != null) {
               stream.close();
            }

            return var3;
         } else {
            return this.toFileVisitResult(Files.size(file) == 0L);
         }
      });
   }

   static {
      NOT_EMPTY = EMPTY.negate();
   }
}
