package jodd.io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import jodd.JoddCore;
import jodd.util.CharUtil;

public class StringOutputStream extends OutputStream implements Serializable {
   protected final StringBuilder sb;
   protected final String encoding;

   public StringOutputStream() {
      this(JoddCore.encoding);
   }

   public StringOutputStream(String encoding) {
      this.sb = new StringBuilder();
      this.encoding = encoding;
   }

   public String toString() {
      return this.sb.toString();
   }

   public void close() {
      this.sb.setLength(0);
   }

   public void write(byte[] b) throws IOException {
      this.sb.append(CharUtil.toCharArray(b, this.encoding));
   }

   public void write(byte[] b, int off, int len) throws IOException {
      if (off >= 0 && len >= 0 && off + len <= b.length) {
         byte[] bytes = new byte[len];

         for(int i = 0; i < len; ++i) {
            bytes[i] = b[off];
            ++off;
         }

         this.sb.append(CharUtil.toCharArray(bytes, this.encoding));
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public void write(int b) {
      this.sb.append((char)b);
   }
}
