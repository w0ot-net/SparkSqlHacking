package org.apache.derby.impl.load;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

class ImportFileInputStream extends InputStream {
   private RandomAccessFile raf = null;
   private long currentPosition = 0L;
   private long fileLength = 0L;

   ImportFileInputStream(RandomAccessFile var1) throws IOException {
      this.raf = var1;
      this.fileLength = var1.length();
   }

   void seek(long var1) throws IOException {
      this.raf.seek(var1);
      this.currentPosition = var1;
   }

   public int read() throws IOException {
      return this.raf.read();
   }

   public int read(byte[] var1, int var2, int var3) throws IOException {
      return this.raf.read(var1, var2, var3);
   }

   public int available() throws IOException {
      return (int)(this.fileLength - this.currentPosition);
   }

   public void close() throws IOException {
      if (this.raf != null) {
         this.raf.close();
      }

   }
}
