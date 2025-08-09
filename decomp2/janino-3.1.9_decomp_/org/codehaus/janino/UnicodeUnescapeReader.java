package org.codehaus.janino;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import org.codehaus.commons.nullanalysis.Nullable;

public class UnicodeUnescapeReader extends FilterReader {
   private int unreadChar = -1;
   private boolean oddPrecedingBackslashes;

   public UnicodeUnescapeReader(Reader in) {
      super(in);
   }

   public int read() throws IOException {
      int c;
      if (this.unreadChar == -1) {
         c = this.in.read();
      } else {
         c = this.unreadChar;
         this.unreadChar = -1;
      }

      if (c == 92 && !this.oddPrecedingBackslashes) {
         c = this.in.read();
         if (c != 117) {
            this.unreadChar = c;
            this.oddPrecedingBackslashes = true;
            return 92;
         } else {
            do {
               c = this.in.read();
               if (c == -1) {
                  throw new UnicodeUnescapeException("Incomplete escape sequence");
               }
            } while(c == 117);

            char[] ca = new char[4];
            ca[0] = (char)c;
            if (this.in.read(ca, 1, 3) != 3) {
               throw new UnicodeUnescapeException("Incomplete escape sequence");
            } else {
               try {
                  return Integer.parseInt(new String(ca), 16);
               } catch (NumberFormatException ex) {
                  throw new UnicodeUnescapeException("Invalid escape sequence \"\\u" + new String(ca) + "\"", ex);
               }
            }
         }
      } else {
         this.oddPrecedingBackslashes = false;
         return c;
      }
   }

   public int read(@Nullable char[] cbuf, int off, int len) throws IOException {
      assert cbuf != null;

      if (len == 0) {
         return 0;
      } else {
         int res = 0;

         do {
            int c = this.read();
            if (c == -1) {
               break;
            }

            cbuf[off++] = (char)c;
            ++res;
         } while(res < len);

         return res == 0 ? -1 : res;
      }
   }

   public static void main(String[] args) throws IOException {
      Reader r = new UnicodeUnescapeReader(new StringReader(args[0]));

      while(true) {
         int c = r.read();
         if (c == -1) {
            System.out.println();
            return;
         }

         System.out.print((char)c);
      }
   }
}
