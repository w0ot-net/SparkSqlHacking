package scala.reflect.io;

import java.net.URL;
import scala.io.Codec.;

public final class AbstractFile$ {
   public static final AbstractFile$ MODULE$ = new AbstractFile$();

   public AbstractFile getFile(final String path) {
      return this.getFile(File$.MODULE$.apply(Path$.MODULE$.apply(path), .MODULE$.fallbackSystemCodec()));
   }

   public AbstractFile getFile(final Path path) {
      return this.getFile(path.toFile());
   }

   public AbstractFile getFile(final File file) {
      return !file.isDirectory() ? new PlainFile(file) : null;
   }

   public AbstractFile getDirectory(final Path path) {
      return this.getDirectory(path.toFile());
   }

   public AbstractFile getDirectory(final File file) {
      if (file.isDirectory()) {
         return new PlainFile(file);
      } else {
         return file.isFile() && Path$.MODULE$.isExtensionJarOrZip(file.jfile()) ? ZipArchive$.MODULE$.fromFile(file) : null;
      }
   }

   public AbstractFile getURL(final URL url) {
      String var10000 = url.getProtocol();
      String var2 = "file";
      if (var10000 != null) {
         if (var10000.equals(var2)) {
            java.io.File f = new java.io.File(url.toURI());
            if (f.isDirectory()) {
               return this.getDirectory(Path$.MODULE$.apply(f));
            }

            return this.getFile(Path$.MODULE$.apply(f));
         }
      }

      return null;
   }

   public AbstractFile getResources(final URL url) {
      ZipArchive$ var10000 = ZipArchive$.MODULE$;
      return new ManifestResources(url);
   }

   private AbstractFile$() {
   }
}
