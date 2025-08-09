package breeze.io;

import java.io.File;

public final class RandomAccessFile$ {
   public static final RandomAccessFile$ MODULE$ = new RandomAccessFile$();

   public String $lessinit$greater$default$2() {
      return "r";
   }

   public ByteConverter $lessinit$greater$default$3(final File file, final String arg0) {
      return ByteConverterBigEndian$.MODULE$;
   }

   private RandomAccessFile$() {
   }
}
