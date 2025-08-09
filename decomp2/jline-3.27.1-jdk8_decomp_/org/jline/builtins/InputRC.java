package org.jline.builtins;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import org.jline.reader.LineReader;

public final class InputRC {
   public static void configure(LineReader reader, URL url) throws IOException {
      org.jline.reader.impl.InputRC.configure(reader, url);
   }

   public static void configure(LineReader reader, InputStream is) throws IOException {
      org.jline.reader.impl.InputRC.configure(reader, is);
   }

   public static void configure(LineReader reader, Reader r) throws IOException {
      org.jline.reader.impl.InputRC.configure(reader, r);
   }
}
