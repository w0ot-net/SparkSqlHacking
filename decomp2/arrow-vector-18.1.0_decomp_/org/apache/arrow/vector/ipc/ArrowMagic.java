package org.apache.arrow.vector.ipc;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

class ArrowMagic {
   private static final byte[] MAGIC;
   public static final int MAGIC_LENGTH;

   private ArrowMagic() {
   }

   public static void writeMagic(WriteChannel out, boolean align) throws IOException {
      out.write(MAGIC);
      if (align) {
         out.align();
      }

   }

   public static boolean validateMagic(byte[] array) {
      return Arrays.equals(MAGIC, array);
   }

   static {
      MAGIC = "ARROW1".getBytes(StandardCharsets.UTF_8);
      MAGIC_LENGTH = MAGIC.length;
   }
}
