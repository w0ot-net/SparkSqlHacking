package shaded.parquet.org.apache.thrift;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;

public class TByteArrayOutputStream extends ByteArrayOutputStream {
   private final int initialSize;

   public TByteArrayOutputStream(int size) {
      super(size);
      this.initialSize = size;
   }

   public TByteArrayOutputStream() {
      this(32);
   }

   public byte[] get() {
      return this.buf;
   }

   public void reset() {
      super.reset();
      if (this.buf.length > this.initialSize) {
         this.buf = new byte[this.initialSize];
      }

   }

   public int len() {
      return this.count;
   }

   public String toString(Charset charset) {
      return new String(this.buf, 0, this.count, charset);
   }
}
