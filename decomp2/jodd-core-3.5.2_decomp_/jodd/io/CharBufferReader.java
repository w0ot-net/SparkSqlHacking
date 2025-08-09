package jodd.io;

import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;

public class CharBufferReader extends Reader {
   private final CharBuffer charBuffer;

   public CharBufferReader(CharBuffer charBuffer) {
      this.charBuffer = charBuffer.duplicate();
   }

   public int read(char[] chars, int offset, int length) throws IOException {
      int read = Math.min(this.charBuffer.remaining(), length);
      this.charBuffer.get(chars, offset, read);
      return read;
   }

   public int read() throws IOException {
      return this.charBuffer.position() < this.charBuffer.limit() ? this.charBuffer.get() : -1;
   }

   public void close() throws IOException {
   }
}
