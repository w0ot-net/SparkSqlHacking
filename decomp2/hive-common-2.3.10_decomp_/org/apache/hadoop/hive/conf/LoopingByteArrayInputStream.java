package org.apache.hadoop.hive.conf;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class LoopingByteArrayInputStream extends InputStream {
   private final byte[] buf;
   private final ThreadLocal threadLocalByteArrayInputStream = new ThreadLocal() {
      protected ByteArrayInputStream initialValue() {
         return null;
      }
   };

   public LoopingByteArrayInputStream(byte[] buf) {
      this.buf = buf;
   }

   private ByteArrayInputStream getByteArrayInputStream() {
      ByteArrayInputStream bais = (ByteArrayInputStream)this.threadLocalByteArrayInputStream.get();
      if (bais == null) {
         bais = new ByteArrayInputStream(this.buf);
         this.threadLocalByteArrayInputStream.set(bais);
      }

      return bais;
   }

   public synchronized int available() {
      return this.getByteArrayInputStream().available();
   }

   public void mark(int arg0) {
      this.getByteArrayInputStream().mark(arg0);
   }

   public boolean markSupported() {
      return this.getByteArrayInputStream().markSupported();
   }

   public synchronized int read() {
      return this.getByteArrayInputStream().read();
   }

   public synchronized int read(byte[] arg0, int arg1, int arg2) {
      return this.getByteArrayInputStream().read(arg0, arg1, arg2);
   }

   public synchronized void reset() {
      this.getByteArrayInputStream().reset();
   }

   public synchronized long skip(long arg0) {
      return this.getByteArrayInputStream().skip(arg0);
   }

   public int read(byte[] arg0) throws IOException {
      return this.getByteArrayInputStream().read(arg0);
   }

   public void close() throws IOException {
      this.getByteArrayInputStream().reset();
      this.getByteArrayInputStream().close();
   }
}
