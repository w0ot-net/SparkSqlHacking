package org.tukaani.xz;

import java.io.IOException;
import java.io.InputStream;

public class DeltaInputStream extends InputStream {
   public static final int DISTANCE_MIN = 1;
   public static final int DISTANCE_MAX = 256;
   private InputStream in;
   private final org.tukaani.xz.delta.DeltaDecoder delta;
   private IOException exception = null;
   private final byte[] tempBuf = new byte[1];

   public DeltaInputStream(InputStream in, int distance) {
      if (in == null) {
         throw new NullPointerException();
      } else {
         this.in = in;
         this.delta = new org.tukaani.xz.delta.DeltaDecoder(distance);
      }
   }

   public int read() throws IOException {
      return this.read(this.tempBuf, 0, 1) == -1 ? -1 : this.tempBuf[0] & 255;
   }

   public int read(byte[] buf, int off, int len) throws IOException {
      if (len == 0) {
         return 0;
      } else if (this.in == null) {
         throw new XZIOException("Stream closed");
      } else if (this.exception != null) {
         throw this.exception;
      } else {
         int size;
         try {
            size = this.in.read(buf, off, len);
         } catch (IOException e) {
            this.exception = e;
            throw e;
         }

         if (size == -1) {
            return -1;
         } else {
            this.delta.decode(buf, off, size);
            return size;
         }
      }
   }

   public int available() throws IOException {
      if (this.in == null) {
         throw new XZIOException("Stream closed");
      } else if (this.exception != null) {
         throw this.exception;
      } else {
         return this.in.available();
      }
   }

   public void close() throws IOException {
      if (this.in != null) {
         try {
            this.in.close();
         } finally {
            this.in = null;
         }
      }

   }
}
