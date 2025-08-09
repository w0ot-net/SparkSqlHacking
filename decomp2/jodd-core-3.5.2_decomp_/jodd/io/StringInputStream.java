package jodd.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import jodd.util.CharUtil;

public class StringInputStream extends InputStream implements Serializable {
   protected final String string;
   protected final Mode mode;
   protected int index;
   protected int charOffset;
   protected int available;

   public StringInputStream(String string, Mode mode) {
      this.string = string;
      this.mode = mode;
      this.available = string.length();
      if (mode == StringInputStream.Mode.ALL) {
         this.available <<= 1;
      }

   }

   public int read() throws IOException {
      if (this.available == 0) {
         return -1;
      } else {
         --this.available;
         char c = this.string.charAt(this.index);
         switch (this.mode) {
            case ALL:
               if (this.charOffset == 0) {
                  this.charOffset = 1;
                  return (c & '\uff00') >> 8;
               }

               this.charOffset = 0;
               ++this.index;
               return c & 255;
            case STRIP:
               ++this.index;
               return c & 255;
            case ASCII:
               ++this.index;
               return CharUtil.toAscii(c);
            default:
               return -1;
         }
      }
   }

   public int available() throws IOException {
      return this.available;
   }

   public static enum Mode {
      ALL,
      STRIP,
      ASCII;
   }
}
