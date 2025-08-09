package org.tukaani.xz;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class XZInputStream extends InputStream {
   private final ArrayCache arrayCache;
   private final int memoryLimit;
   private InputStream in;
   private SingleXZInputStream xzIn;
   private final boolean verifyCheck;
   private boolean endReached;
   private IOException exception;
   private final byte[] tempBuf;

   public XZInputStream(InputStream in) throws IOException {
      this(in, -1);
   }

   public XZInputStream(InputStream in, ArrayCache arrayCache) throws IOException {
      this(in, -1, arrayCache);
   }

   public XZInputStream(InputStream in, int memoryLimit) throws IOException {
      this(in, memoryLimit, true);
   }

   public XZInputStream(InputStream in, int memoryLimit, ArrayCache arrayCache) throws IOException {
      this(in, memoryLimit, true, arrayCache);
   }

   public XZInputStream(InputStream in, int memoryLimit, boolean verifyCheck) throws IOException {
      this(in, memoryLimit, verifyCheck, ArrayCache.getDefaultCache());
   }

   public XZInputStream(InputStream in, int memoryLimit, boolean verifyCheck, ArrayCache arrayCache) throws IOException {
      this.endReached = false;
      this.exception = null;
      this.tempBuf = new byte[1];
      this.arrayCache = arrayCache;
      this.in = in;
      this.memoryLimit = memoryLimit;
      this.verifyCheck = verifyCheck;
      this.xzIn = new SingleXZInputStream(in, memoryLimit, verifyCheck, arrayCache);
   }

   public int read() throws IOException {
      return this.read(this.tempBuf, 0, 1) == -1 ? -1 : this.tempBuf[0] & 255;
   }

   public int read(byte[] buf, int off, int len) throws IOException {
      if (off >= 0 && len >= 0 && off + len >= 0 && off + len <= buf.length) {
         if (len == 0) {
            return 0;
         } else if (this.in == null) {
            throw new XZIOException("Stream closed");
         } else if (this.exception != null) {
            throw this.exception;
         } else if (this.endReached) {
            return -1;
         } else {
            int size = 0;

            try {
               while(len > 0) {
                  if (this.xzIn == null) {
                     this.prepareNextStream();
                     if (this.endReached) {
                        return size == 0 ? -1 : size;
                     }
                  }

                  int ret = this.xzIn.read(buf, off, len);
                  if (ret > 0) {
                     size += ret;
                     off += ret;
                     len -= ret;
                  } else if (ret == -1) {
                     this.xzIn = null;
                  }
               }
            } catch (IOException e) {
               this.exception = e;
               if (size == 0) {
                  throw e;
               }
            }

            return size;
         }
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   private void prepareNextStream() throws IOException {
      DataInputStream inData = new DataInputStream(this.in);
      byte[] buf = new byte[12];

      do {
         int ret = inData.read(buf, 0, 1);
         if (ret == -1) {
            this.endReached = true;
            return;
         }

         inData.readFully(buf, 1, 3);
      } while(buf[0] == 0 && buf[1] == 0 && buf[2] == 0 && buf[3] == 0);

      inData.readFully(buf, 4, 8);

      try {
         this.xzIn = new SingleXZInputStream(this.in, this.memoryLimit, this.verifyCheck, buf, this.arrayCache);
      } catch (XZFormatException var4) {
         throw new CorruptedInputException("Garbage after a valid XZ Stream");
      }
   }

   public int available() throws IOException {
      if (this.in == null) {
         throw new XZIOException("Stream closed");
      } else if (this.exception != null) {
         throw this.exception;
      } else {
         return this.xzIn == null ? 0 : this.xzIn.available();
      }
   }

   public void close() throws IOException {
      this.close(true);
   }

   public void close(boolean closeInput) throws IOException {
      if (this.in != null) {
         if (this.xzIn != null) {
            this.xzIn.close(false);
            this.xzIn = null;
         }

         try {
            if (closeInput) {
               this.in.close();
            }
         } finally {
            this.in = null;
         }
      }

   }
}
