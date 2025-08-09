package jodd.io;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DebugOutputStream extends FilterOutputStream {
   protected boolean passThrough = true;

   public DebugOutputStream() {
      super(System.out);
   }

   public DebugOutputStream(OutputStream out) {
      super(out);
   }

   public DebugOutputStream(boolean passThrough) {
      super(System.out);
      this.passThrough = passThrough;
   }

   public DebugOutputStream(OutputStream out, boolean passThrough) {
      super(out);
      this.passThrough = passThrough;
   }

   public void close() throws IOException {
      super.close();
   }

   public void flush() throws IOException {
      super.flush();
   }

   public void write(int b) throws IOException {
      if (this.passThrough) {
         super.write(b);
      }

      this.dumpByte(b);
      System.out.println();
   }

   public void write(byte[] b) throws IOException {
      super.write(b);
   }

   public void write(byte[] b, int off, int len) throws IOException {
      if (this.passThrough) {
         super.write(b, off, len);
      }

      int i = off;
      int count = len;

      while(count-- > 0) {
         this.dumpByte(b[i++]);
      }

      System.out.println();
   }

   protected void dumpByte(int b) {
      if (this.passThrough) {
         System.out.print('\t');
      }

      if (b < 0) {
         b += 128;
      }

      if (b < 16) {
         System.out.print('0');
      }

      System.out.print(' ');
      System.out.print(Integer.toHexString(b).toUpperCase());
   }
}
