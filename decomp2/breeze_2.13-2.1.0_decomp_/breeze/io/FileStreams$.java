package breeze.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public final class FileStreams$ {
   public static final FileStreams$ MODULE$ = new FileStreams$();
   private static final int BUFFER_SIZE = 16384;

   public int BUFFER_SIZE() {
      return BUFFER_SIZE;
   }

   public InputStream input(final File path) {
      FileInputStream fis = new FileInputStream(path);

      try {
         return path.getName().endsWith(".gz") ? new BufferedInputStream(new GZIPInputStream(fis, this.BUFFER_SIZE()), this.BUFFER_SIZE()) : new BufferedInputStream(fis, this.BUFFER_SIZE());
      } catch (Throwable var4) {
         fis.close();
         throw var4;
      }
   }

   public OutputStream output(final File path) {
      FileOutputStream fos = new FileOutputStream(path);

      try {
         return path.getName().endsWith(".gz") ? new BufferedOutputStream(new GZIPOutputStream(fos, this.BUFFER_SIZE()), this.BUFFER_SIZE()) : new BufferedOutputStream(fos, this.BUFFER_SIZE());
      } catch (Throwable var4) {
         fos.close();
         throw var4;
      }
   }

   private FileStreams$() {
   }
}
