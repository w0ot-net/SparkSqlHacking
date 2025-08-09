package org.json4s.scalap.scalasig;

import java.io.IOException;
import java.io.InputStream;

public final class ByteCode$ {
   public static final ByteCode$ MODULE$ = new ByteCode$();

   public ByteCode apply(final byte[] bytes) {
      return new ByteCode(bytes, 0, bytes.length);
   }

   public ByteCode forClass(final Class clazz) {
      String name = clazz.getName();
      String subPath = (new StringBuilder(6)).append(name.substring(name.lastIndexOf(46) + 1)).append(".class").toString();
      InputStream in = clazz.getResourceAsStream(subPath);

      try {
         int rest = in.available();

         byte[] bytes;
         int res;
         for(bytes = new byte[rest]; rest > 0; rest -= res) {
            res = in.read(bytes, bytes.length - rest, rest);
            if (res == -1) {
               throw new IOException("read error");
            }
         }

         this.apply(bytes);
      } finally {
         in.close();
      }

      return in;
   }

   private ByteCode$() {
   }
}
