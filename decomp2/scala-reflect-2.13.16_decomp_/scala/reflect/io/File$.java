package scala.reflect.io;

import scala.io.Codec;
import scala.io.Codec.;

public final class File$ {
   public static final File$ MODULE$ = new File$();

   public String pathSeparator() {
      return java.io.File.pathSeparator;
   }

   public String separator() {
      return java.io.File.separator;
   }

   public File apply(final Path path, final Codec codec) {
      return new File(path.jfile(), codec);
   }

   public File makeTemp(final String prefix, final String suffix, final java.io.File dir) {
      java.io.File jfile = java.io.File.createTempFile(prefix, suffix, dir);
      jfile.deleteOnExit();
      return this.apply(Path$.MODULE$.apply(jfile), .MODULE$.fallbackSystemCodec());
   }

   public String makeTemp$default$1() {
      return Path$.MODULE$.randomPrefix();
   }

   public String makeTemp$default$2() {
      return null;
   }

   public java.io.File makeTemp$default$3() {
      return null;
   }

   private File$() {
   }
}
