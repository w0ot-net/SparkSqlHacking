package io.fabric8.kubernetes.client.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;

public class IOHelpers {
   private IOHelpers() {
      throw new IllegalStateException("Utility class");
   }

   public static String readFully(InputStream in, Charset charset) throws IOException {
      Reader r = new BufferedReader(new InputStreamReader(in, charset));
      return readFully(r);
   }

   public static String readFully(InputStream in) throws IOException {
      return readFully(in, Charset.defaultCharset());
   }

   public static String readFully(Reader r) throws IOException {
      StringWriter w = new StringWriter();

      String var2;
      try {
         copy(r, w);
         var2 = w.toString();
      } catch (Throwable var5) {
         try {
            w.close();
         } catch (Throwable var4) {
            var5.addSuppressed(var4);
         }

         throw var5;
      }

      w.close();
      return var2;
   }

   private static void copy(Reader reader, Writer writer) throws IOException {
      char[] buffer = new char[8192];

      while(true) {
         int len = reader.read(buffer);
         if (len <= 0) {
            writer.flush();
            return;
         }

         writer.write(buffer, 0, len);
      }
   }
}
