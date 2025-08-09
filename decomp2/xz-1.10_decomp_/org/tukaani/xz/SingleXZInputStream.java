package org.tukaani.xz;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.tukaani.xz.check.Check;
import org.tukaani.xz.common.DecoderUtil;
import org.tukaani.xz.common.StreamFlags;
import org.tukaani.xz.index.IndexHash;

public class SingleXZInputStream extends InputStream {
   private InputStream in;
   private final ArrayCache arrayCache;
   private final int memoryLimit;
   private final StreamFlags streamHeaderFlags;
   private final Check check;
   private final boolean verifyCheck;
   private BlockInputStream blockDecoder;
   private final IndexHash indexHash;
   private boolean endReached;
   private IOException exception;
   private final byte[] tempBuf;

   private static byte[] readStreamHeader(InputStream in) throws IOException {
      byte[] streamHeader = new byte[12];
      (new DataInputStream(in)).readFully(streamHeader);
      return streamHeader;
   }

   public SingleXZInputStream(InputStream in) throws IOException {
      this(in, -1);
   }

   public SingleXZInputStream(InputStream in, ArrayCache arrayCache) throws IOException {
      this(in, -1, arrayCache);
   }

   public SingleXZInputStream(InputStream in, int memoryLimit) throws IOException {
      this(in, memoryLimit, true);
   }

   public SingleXZInputStream(InputStream in, int memoryLimit, ArrayCache arrayCache) throws IOException {
      this(in, memoryLimit, true, arrayCache);
   }

   public SingleXZInputStream(InputStream in, int memoryLimit, boolean verifyCheck) throws IOException {
      this(in, memoryLimit, verifyCheck, ArrayCache.getDefaultCache());
   }

   public SingleXZInputStream(InputStream in, int memoryLimit, boolean verifyCheck, ArrayCache arrayCache) throws IOException {
      this(in, memoryLimit, verifyCheck, readStreamHeader(in), arrayCache);
   }

   SingleXZInputStream(InputStream in, int memoryLimit, boolean verifyCheck, byte[] streamHeader, ArrayCache arrayCache) throws IOException {
      this.blockDecoder = null;
      this.indexHash = new IndexHash();
      this.endReached = false;
      this.exception = null;
      this.tempBuf = new byte[1];
      this.arrayCache = arrayCache;
      this.in = in;
      this.memoryLimit = memoryLimit;
      this.verifyCheck = verifyCheck;
      this.streamHeaderFlags = DecoderUtil.decodeStreamHeader(streamHeader);
      this.check = Check.getInstance(this.streamHeaderFlags.checkType);
   }

   public int getCheckType() {
      return this.streamHeaderFlags.checkType;
   }

   public String getCheckName() {
      return this.check.getName();
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
                  if (this.blockDecoder == null) {
                     try {
                        this.blockDecoder = new BlockInputStream(this.in, this.check, this.verifyCheck, this.memoryLimit, -1L, -1L, this.arrayCache);
                     } catch (IndexIndicatorException var6) {
                        this.indexHash.validate(this.in);
                        this.validateStreamFooter();
                        this.endReached = true;
                        return size > 0 ? size : -1;
                     }
                  }

                  int ret = this.blockDecoder.read(buf, off, len);
                  if (ret > 0) {
                     size += ret;
                     off += ret;
                     len -= ret;
                  } else if (ret == -1) {
                     this.indexHash.add(this.blockDecoder.getUnpaddedSize(), this.blockDecoder.getUncompressedSize());
                     this.blockDecoder = null;
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

   private void validateStreamFooter() throws IOException {
      byte[] buf = new byte[12];
      (new DataInputStream(this.in)).readFully(buf);
      StreamFlags streamFooterFlags = DecoderUtil.decodeStreamFooter(buf);
      if (!DecoderUtil.areStreamFlagsEqual(this.streamHeaderFlags, streamFooterFlags) || this.indexHash.getIndexSize() != streamFooterFlags.backwardSize) {
         throw new CorruptedInputException("XZ Stream Footer does not match Stream Header");
      }
   }

   public int available() throws IOException {
      if (this.in == null) {
         throw new XZIOException("Stream closed");
      } else if (this.exception != null) {
         throw this.exception;
      } else {
         return this.blockDecoder == null ? 0 : this.blockDecoder.available();
      }
   }

   public void close() throws IOException {
      this.close(true);
   }

   public void close(boolean closeInput) throws IOException {
      if (this.in != null) {
         if (this.blockDecoder != null) {
            this.blockDecoder.close();
            this.blockDecoder = null;
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
