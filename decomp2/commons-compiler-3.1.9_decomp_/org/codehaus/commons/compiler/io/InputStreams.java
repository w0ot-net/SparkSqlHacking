package org.codehaus.commons.compiler.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class InputStreams {
   private InputStreams() {
   }

   public static byte[] readAll(InputStream is) throws IOException {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      copy(is, baos);
      return baos.toByteArray();
   }

   public static void copy(InputStream is, OutputStream os) throws IOException {
      byte[] buffer = new byte[8192];

      while(true) {
         int n = is.read(buffer);
         if (n == -1) {
            return;
         }

         os.write(buffer, 0, n);
      }
   }
}
