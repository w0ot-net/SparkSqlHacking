package org.glassfish.jaxb.core.marshaller;

import java.io.IOException;
import java.io.Writer;

public class MinimumEscapeHandler implements CharacterEscapeHandler {
   public static final CharacterEscapeHandler theInstance = new MinimumEscapeHandler();

   private MinimumEscapeHandler() {
   }

   public void escape(char[] ch, int start, int length, boolean isAttVal, Writer out) throws IOException {
      int limit = start + length;

      for(int i = start; i < limit; ++i) {
         char c = ch[i];
         if (c == '&' || c == '<' || c == '>' || c == '\r' || c == '\n' && isAttVal || c == '"' && isAttVal) {
            if (i != start) {
               out.write(ch, start, i - start);
            }

            start = i + 1;
            switch (ch[i]) {
               case '\n':
               case '\r':
                  out.write("&#");
                  out.write(Integer.toString(c));
                  out.write(59);
                  break;
               case '"':
                  out.write("&quot;");
                  break;
               case '&':
                  out.write("&amp;");
                  break;
               case '<':
                  out.write("&lt;");
                  break;
               case '>':
                  out.write("&gt;");
                  break;
               default:
                  throw new IllegalArgumentException("Cannot escape: '" + c + "'");
            }
         }
      }

      if (start != limit) {
         out.write(ch, start, limit - start);
      }

   }
}
