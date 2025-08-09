package jodd.io;

import java.io.IOException;
import java.io.Writer;
import jodd.util.buffer.FastCharBuffer;

public class FastCharArrayWriter extends Writer {
   private final FastCharBuffer buffer;

   public FastCharArrayWriter() {
      this(1024);
   }

   public FastCharArrayWriter(int size) {
      this.buffer = new FastCharBuffer(size);
   }

   public void write(char[] b, int off, int len) {
      this.buffer.append(b, off, len);
   }

   public void write(int b) {
      this.buffer.append((char)b);
   }

   public void write(String s, int off, int len) {
      this.write(s.toCharArray(), off, len);
   }

   public int size() {
      return this.buffer.size();
   }

   public void close() {
   }

   public void flush() {
   }

   public void reset() {
      this.buffer.clear();
   }

   public void writeTo(Writer out) throws IOException {
      int index = this.buffer.index();

      for(int i = 0; i < index; ++i) {
         char[] buf = this.buffer.array(i);
         out.write(buf);
      }

      out.write(this.buffer.array(index), 0, this.buffer.offset());
   }

   public char[] toCharArray() {
      return this.buffer.toArray();
   }

   public String toString() {
      return new String(this.toCharArray());
   }
}
